package com.endreman0.endermechanics.tile;

import com.endreman0.endermechanics.util.EnderNodeNetwork;
import com.endreman0.endermechanics.util.IPowerHandler;
import com.endreman0.endermechanics.util.LogHelper;
import com.endreman0.endermechanics.util.Utility;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEnderNode extends TileInventory{
	protected EnderNodeNetwork network;
	protected long ticks;
	protected int range = Utility.nodeRange;
	protected int scan = 2*range + 1;//Scan time; 1 tick per layer. One layer for the layer this is in, "range" layers above, and "range" layers below.
	public TileEnderNode(){
		super(1, 1000);
		ticks = 0;
	}
	/*@Override
	public void tick(){
		if(ticks<scan && (network==null || network.nodes()<=1)) scanNetwork((int)ticks % cycle);//If it's in the scan cycle and the network is incomplete, scan.
		if(!(worldObj.getTileEntity(masterX, masterY, masterZ) instanceof TileEnderNode)) createFromTile(null);//If the master is broken, reset.
		
//		if(network!=null) power-=network.insert(ForgeDirection.UNKNOWN, power, true);//Empty as much power as possible into network
		
		ForgeDirection dir = ForgeDirection.getOrientation((int)ticks % 6);
		TileEntity tile = worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ);
		if(tile instanceof TileGenerator){
			TileGenerator generator = (TileGenerator)tile;
			if(generator.canExtract(dir.getOpposite())){
				insert(dir, generator.extract(dir.getOpposite(), Math.min(getMaxPower(dir)-getPower(dir), 1000), true), true);
				//Get the capacity for power in the network. Cap it at 1000. Extract that much power from the generator, then insert it into the network.
			}
		}else if(tile instanceof TileMachine){
			IPowerHandler machine = (TileMachine)tile;
			if(machine.canInsert(dir.getOpposite())){
				machine.insert(dir.getOpposite(), extract(dir, Math.min(machine.getMaxPower(dir.getOpposite())-machine.getPower(dir.getOpposite()), 1000), true), true);
				//Get the capacity of the machine. Cap it at 1000. Extract up to that much power from this tile. Insert that much power into the machine.
			}
		}
		
		ticks++;
	}*/
	@Override
	public void tick(){
		//Network managing
		if(network==null || network.nodes()<=1) scanNetwork((int)ticks % scan);
		if(network==null && ticks % scan==scan-1) createFromTile(this);
		
		//Power trade between network and node
		if(network!=null && power!=0) power -= network.insert(ForgeDirection.UNKNOWN, power, true);//Network supports inserting/extracting negative power.
		
		//Power extraction and insertion
		ForgeDirection dir = ForgeDirection.getOrientation((int)ticks % 6);
		if(worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ) instanceof IPowerHandler){
			IPowerHandler tile = (IPowerHandler)worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ);
			if(tile instanceof TileGenerator){
				power+=tile.extract(dir.getOpposite(), Math.min(1000, maxPower-power), true);
			}else if(tile instanceof TileMachine){
				power-=tile.insert(dir.getOpposite(), Math.min(1000, power), true);
			}
		}
		
		//Misc
		ticks++;
	}
	public void scanNetwork(int layer){
		scan: for(int i=-range;i<=range;i++){
			for(int j=-range;j<=range;j++){
				TileEntity tile = worldObj.getTileEntity(xCoord+i, yCoord+(layer-range), zCoord+j);
				if(tile instanceof TileEnderNode && !tile.equals(this)){
					createFromTile((TileEnderNode)tile);
					network.addNode(this);
					break scan;
				}
			}
		}
	}
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		if(network!=null && network.getMaster().equals(this)){
			nbt.setInteger("netPower", network.getPower(ForgeDirection.UNKNOWN));
		}else{
			nbt.setInteger("netPower", 0);
		}
		LogHelper.info("Writing netPower as " + nbt.getInteger("netPower"));
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		LogHelper.info("Reading netPower as " + nbt.getInteger("netPower"));
		power+=nbt.getInteger("netPower");
	}
	/*@Override
	public void writeToNBT(NBTTagCompound nbt){
		LogHelper.info("Node Power: " + power);
		if(network!=null && network.getMaster().equals(this)) power+=network.getPower(ForgeDirection.UNKNOWN);
		LogHelper.info("Written Power: " + power);
		super.writeToNBT(nbt);
		nbt.setInteger("masterX", masterX);
		nbt.setInteger("masterY", masterY);
		nbt.setInteger("masterZ", masterZ);
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		LogHelper.info("Reading Power as " + power);
		if(network!=null) return;
		LogHelper.info("Reading from NBTTagCompound " + nbt);
		createFromTile((TileEnderNode)worldObj.getTileEntity(nbt.getInteger("masterX"), nbt.getInteger("masterY"), nbt.getInteger("masterZ")));
		if(network!=null) network.addNode(this);
	}*/
	@Override
	public void invalidate(){
		super.invalidate();
		if(network!=null) network.removeNode(this);
	}
	public EnderNodeNetwork network(){return network;}
	public long ticks(){return ticks;}
	public EnderNodeNetwork getNetworkFromMaster(){
		if(network==null) network = new EnderNodeNetwork(this);
		return network;
	}
	private void createFromTile(TileEnderNode tile){
		if(tile==null){
			network = null;
		}else{
			network = tile.getNetworkFromMaster();
		}
		markDirty();
	}
	
	//Overrides to make this an extension of the network's inventory, starting with IInventory
	@Override public int getSizeInventory(){return network!=null ? network.getSizeInventory() : 0;}
	@Override public ItemStack getStackInSlot(int slot){return network!=null ? network.getStackInSlot(slot) : null;}
	@Override public void setInventorySlotContents(int slot, ItemStack stack){if(network!=null) network.setInventorySlotContents(slot, stack);}
	
	//IPowerHandler
	public int getNetworkPower(ForgeDirection from){return network!=null ? network.getPower(from) : 0;}
	@Override public int getMaxPower(ForgeDirection from){return network!=null ? network.getMaxPower(from) : super.getMaxPower(from);}
}

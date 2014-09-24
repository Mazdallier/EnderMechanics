package com.endreman0.endermechanics.tile;

import com.endreman0.endermechanics.util.EnderNodeNetwork;
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
	protected int cycle = Utility.nodeUpdate;
	protected int scan = 2*range + 1;//Scan time; 1 tick per layer. One layer for the layer this is in, "range" layers above, and "range" layers below.
	protected int masterX, masterY, masterZ;
	public TileEnderNode(){
		super(1, 1000);
		ticks = 0;
	}
	
	@Override
	public void tick(){
		if(ticks<scan && (network==null || network.nodes()<=1)) scanNetwork((byte)(ticks % cycle));//If it's in the scan cycle and the network is incomplete, scan.
		if(!(worldObj.getTileEntity(masterX, masterY, masterZ) instanceof TileEnderNode)) createFromTile(null);//If the master is broken, reset.
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
		if(this.network==null && layer==scan-1){//Last layer, and still nothing
			createFromTile(this);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setInteger("masterX", masterX);
		nbt.setInteger("masterY", masterY);
		nbt.setInteger("masterZ", masterZ);
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		if(network!=null) return;
		createFromTile((TileEnderNode)worldObj.getTileEntity(nbt.getInteger("masterX"), nbt.getInteger("masterY"), nbt.getInteger("masterZ")));
		if(network!=null) network.addNode(this);
	}
	@Override
	public void invalidate(){
		super.invalidate();
		if(network!=null) network.removeNode(this);
	}
	public EnderNodeNetwork getNetwork(){return network;}
	public long getTicks(){return ticks;}
	public EnderNodeNetwork getNetworkFromMaster(){
		if(network==null) network = new EnderNodeNetwork(this);
		return network;
	}
	private void createFromTile(TileEnderNode tile){
		if(tile==null){
			masterX = xCoord; masterY = yCoord; masterZ = zCoord;
			network = null;
		}else{
			masterX = tile.xCoord; masterY = tile.yCoord; masterZ = tile.zCoord;
			network = tile.getNetworkFromMaster();
		}
		markDirty();
	}
	
	//Overrides to make this an extension of the network's inventory, starting with IInventory
	@Override public int getSizeInventory(){return network!=null ? network.getSizeInventory() : 0;}
	@Override public ItemStack getStackInSlot(int slot){return network!=null ? network.getStackInSlot(slot) : null;}
	@Override public void setInventorySlotContents(int slot, ItemStack stack){if(network!=null) network.setInventorySlotContents(slot, stack);}
	
	//IPowerHandler
	@Override public int insert(ForgeDirection from, int amount, boolean actual){return network!=null ? network.insert(from, amount, actual) : 0;}
	@Override public int extract(ForgeDirection from, int amount, boolean actual){return network!=null ? network.extract(from, amount, actual) : 0;}
	@Override public boolean canInsert(ForgeDirection from){return network!=null ? network.canInsert(from) : false;}
	@Override public boolean canExtract(ForgeDirection from){return network!=null ? network.canExtract(from) : false;}
	@Override public int getPower(ForgeDirection from){return network!=null ? network.getPower(from) : 0;}
	@Override public int getMaxPower(ForgeDirection from){return network!=null ? network.getMaxPower(from) : 0;}
}

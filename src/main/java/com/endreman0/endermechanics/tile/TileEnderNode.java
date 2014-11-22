package com.endreman0.endermechanics.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.endreman0.endermechanics.api.IPowerHandler;
import com.endreman0.endermechanics.util.EnderNodeNetwork;
import com.endreman0.endermechanics.util.Utility;

public class TileEnderNode extends TileInventory{
	protected EnderNodeNetwork network;
	protected int range = Utility.nodeRange;
	protected int scan = 2*range + 1;//Scan time; 1 tick per layer. One layer for the layer this is in, "range" layers above, and "range" layers below.
	public TileEnderNode(){
		super(1, 1000);
	}
	@Override
	public void updateEntity(){
		super.updateEntity();
		//Network managing
		if(network==null || network.nodes()<=1) scanNetwork((int)ticks % scan);
		if(network==null && ticks % scan==scan-1) createFromTile(this);
		
		//Power exchange
		ForgeDirection dir = ForgeDirection.getOrientation((int)ticks % 6);
		TileEntity tile = worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ);
		if(tile instanceof IPowerHandler && ((IPowerHandler)tile).canInsert(dir.getOpposite()) && canExtract(dir))
			((IPowerHandler)tile).insert(dir.getOpposite(), extract(dir, 1000, true), true);
		
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
	public EnderNodeNetwork network(){return network;}
	@Override
	public void invalidate(){
		super.invalidate();
		if(network!=null) network.removeNode(this);
	}
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
	
	//NBT
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		if(network!=null && network.getMaster().equals(this)){
			nbt.setInteger("netPower", network.getPower(ForgeDirection.UNKNOWN));
		}else{
			nbt.setInteger("netPower", 0);
		}
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		power+=nbt.getInteger("netPower");
	}
	
	//Overrides to make this an extension of the network's inventory, starting with IInventory
	@Override public int getSizeInventory(){return network!=null ? network.getSizeInventory() : 0;}
	@Override public ItemStack getStackInSlot(int slot){return network!=null ? network.getStackInSlot(slot) : null;}
	@Override public void setInventorySlotContents(int slot, ItemStack stack){if(network!=null) network.setInventorySlotContents(slot, stack);}
	
	public int getNodePower(){return power;}
	public int getNetworkPower(ForgeDirection from){return network!=null ? network.getPower(from) : 0;}
	
	//IPowerHandler
	@Override public int getPower(ForgeDirection from){return super.getPower(from) + (network!=null ? network.getPower(from) : 0);}
	@Override public int getMaxPower(ForgeDirection from){return super.getMaxPower(from) + (network!=null ? network.getMaxPower(from) : 0);}
	/*@Override
	public int insert(ForgeDirection from, int amount, boolean actual){
		if(amount<0) return extract(from, -amount, actual);
		if(!canInsert(from)) return 0;
		int amt = Math.min(amount, getMaxPower(from)-getPower(from));
		if(actual){
			if(maxPower-power>=amount){power+=amount;}else{//If there is room in this node's buffer for the power, put it in; otherwise,
				if(network!=null) network.insert(from, amt-(maxPower-power), actual);//Fill the network with all the excess power,
				power=maxPower;//and fill the node's buffer.
			}
		}
		return amt;
	}
	@Override
	public int extract(ForgeDirection from, int amount, boolean actual){
		if(amount<0) return insert(from, -amount, actual);
		if(!canExtract(from)) return 0;
		
		int amt = Math.min(amount, getPower(from));
		if(actual){
			if(power>=amt){power-=amt;}else{
				if(network!=null) network.extract(from, amt-power, actual);//Extract all of the power needed from the network, 
				power=0;//and empty this node's power.
			}
		}
		return amt;
	}*/
	@Override
	public int insert(ForgeDirection from, int amount, boolean actual){
		if(amount<0) return extract(from, -amount, actual);
		if(!canInsert(from)) return 0;
		
		int amt = Math.min(amount, getMaxPower(from)-getPower(from));
		if(actual){
			if(network!=null){
				power+=amt-network.insert(from, amt, true);
			}else{
				power+=amt;
			}
		}
		return amt;
	}
	@Override
	public int extract(ForgeDirection from, int amount, boolean actual){
		if(amount<0) return insert(from, -amount, actual);
		if(!canExtract(from)) return 0;
		
		int amt = Math.min(amount, getPower(from));
		if(actual){
			if(power>=amt){
				power-=amt;
			}else if(network!=null){
				network.extract(from, amt-power, true);
				power=0;
			}
		}
		return amt;
	}
}

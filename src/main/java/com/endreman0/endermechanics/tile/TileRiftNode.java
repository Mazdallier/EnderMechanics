package com.endreman0.endermechanics.tile;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.endreman0.endermechanics.api.IFramableMachine;
import com.endreman0.endermechanics.api.IPowerHandler;
import com.endreman0.endermechanics.item.focus.ItemFocusEM;
import com.endreman0.endermechanics.util.NodeNetwork;
import com.endreman0.endermechanics.util.Utility;

public class TileRiftNode extends TileInventory implements IFramableMachine{
	private NodeNetwork network;
	private int range = Utility.nodeRange;
	private int scan = 2*range + 1;//Scan time; 1 tick per layer. One layer for the layer this is in, "range" layers above, and "range" layers below.
	private ItemStack focus;
	public TileRiftNode(){
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
				if(tile instanceof TileRiftNode && !tile.equals(this)){
					createFromTile((TileRiftNode)tile);
					network.addNode(this);
					break scan;
				}
			}
		}
	}
	public NodeNetwork network(){return network;}
	@Override
	public void invalidate(){
		super.invalidate();
		if(network!=null) network.removeNode(this);
	}
	public NodeNetwork getNetworkFromMaster(){
		if(network==null) network = new NodeNetwork(this);
		return network;
	}
	private void createFromTile(TileRiftNode tile){
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
		if(focus==null) nbt.setTag("focus", null); else nbt.setTag("focus", focus.getTagCompound());
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
		NBTTagCompound comp = nbt.getCompoundTag("focus");
		if(comp==null) focus = null; else focus = ItemStack.loadItemStackFromNBT(comp);
	}
	public boolean insertFocus(ItemStack focus){
		if(this.focus==null && focus!=null && focus.getItem() instanceof ItemFocusEM){
			this.focus = focus;
			return true;
		}
		return false;
	}
	public ItemStack focus(){return focus;}
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
	@Override public int[] getAccessibleSlotsFromSide(int side){return null;}
	@Override public boolean canInsertItem(int slot, ItemStack stack, int side){return false;}
	@Override public boolean canExtractItem(int slot, ItemStack stack, int side){return false;}
	@Override public ItemStack getRenderedStack(){return null;}
	@Override public void setInFrame(boolean inFrame){}
	@Override public boolean isActive(){return network!=null && network.nodes()>1;}
}

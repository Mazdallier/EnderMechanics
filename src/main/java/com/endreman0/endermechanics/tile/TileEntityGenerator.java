package com.endreman0.endermechanics.tile;

import com.endreman0.endermechanics.LogHelper;
import com.endreman0.endermechanics.Utility;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntityGenerator extends TileEntity implements IInventory, IFluidHandler{
	private ItemStack[] inventory = new ItemStack[1];
	protected FluidTank lavaTank = new FluidTank(10000);//capacity in mB
	
	@Override
	public int getSizeInventory(){
		return inventory.length;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack){
		inventory[slot] = stack;
	}
	
	@Override
	public ItemStack getStackInSlot(int slot){
		return inventory[slot];
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot){
		ItemStack stack = getStackInSlot(slot);
		if(stack!=null){
			setInventorySlotContents(slot, null);
		}
		return stack;
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount){
		ItemStack stack = getStackInSlot(slot);
		if(stack!=null){
			if(stack.stackSize <= amount){
				setInventorySlotContents(slot, null);
			}else{
				stack = stack.splitStack(amount);
				if(stack.stackSize==0){
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack){
		return true;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player){
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("Inventory", 10);
		for(int i=0;i<getSizeInventory();i++){
			NBTTagCompound tag = list.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if(slot >= 0 && slot < inventory.length){
				inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
		
		int amt = nbt.getInteger("tankAmount");
		if(amt>0){
			lavaTank.setFluid(new FluidStack(2, amt));
		}
	}
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		NBTTagList list = new NBTTagList();
		for(int i=0; i<inventory.length; i++){
			ItemStack stack = inventory[i];
			if(stack!=null){
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte)i);
				stack.writeToNBT(tag);
				list.appendTag(tag);
			}
		}
		nbt.setTag("Inventory", list);
		
		if(lavaTank.getFluid()!=null){
			nbt.setInteger("tankAmount", lavaTank.getFluid().amount);
		}
	}
	
	@Override
	public Packet getDescriptionPacket(){
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet){
		readFromNBT(packet.func_148857_g());//func_148857_g gets NBT from packet
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill){
		LogHelper.info(resource.getFluid().getID());
		if(resource.getFluid().getID()!=2){
			return 0;
		}
		return lavaTank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain){
		return new FluidStack(0,0);//Don't allow draining
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain){
		return new FluidStack(0,0);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid){
		return fluid.getID()==2;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid){
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from){
		return new FluidTankInfo[]{new FluidTankInfo(lavaTank)};
	}
	
	@Override public String getInventoryName(){return Utility.RESOURCE_PREFIX + ".tileGenerator";}
	@Override public boolean hasCustomInventoryName(){return false;}
	@Override public int getInventoryStackLimit(){return 64;}
	@Override public void openInventory(){}
	@Override public void closeInventory(){}
}

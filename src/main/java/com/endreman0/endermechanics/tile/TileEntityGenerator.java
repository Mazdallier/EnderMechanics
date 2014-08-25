package com.endreman0.endermechanics.tile;

import com.endreman0.endermechanics.Utility;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGenerator extends TileEntity implements IInventory{
	private ItemStack[] inventory = new ItemStack[1];
	
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
	}
	@Override public String getInventoryName(){return Utility.RESOURCE_PREFIX + ".tileGenerator";}
	@Override public boolean hasCustomInventoryName(){return false;}
	@Override public int getInventoryStackLimit(){return 64;}
	@Override public void openInventory(){}
	@Override public void closeInventory(){}
}

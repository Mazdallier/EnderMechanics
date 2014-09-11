package com.endreman0.endermechanics.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileGeneratorLiving extends TileMachineEM{
	protected int getInvSlots(){return 1;}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("Inventory", 10);
		for(int i=0;i<list.tagCount();i++){
			NBTTagCompound tag = list.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if(slot >= 0 && slot < inv.length){
				inv[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		NBTTagList list = new NBTTagList();
		for(int i=0; i<inv.length; i++){
			ItemStack stack = inv[i];
			if(stack!=null){
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte)i);
				stack.writeToNBT(tag);
				list.appendTag(tag);
			}
		}
		nbt.setTag("Inventory", list);
	}
}

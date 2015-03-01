package com.endreman0.endermechanics.tile;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.endreman0.endermechanics.item.focus.ItemFocusEM;

public class TileRiftNode extends TileEM{
	private ItemStack focus = null;
	private int ticks = 0;
	public TileRiftNode(){super();}
	public ItemStack focus(){return focus;}
	public boolean insertFocus(ItemStack focus){
		if(focus!=null && this.focus==null && focus.getItem() instanceof ItemFocusEM){
			this.focus = focus;
			markDirty();
			System.out.println("Focus inserted");
			return true;
		}
		return false;
	}
	@Override
	public void updateEntity(){
		super.updateEntity();
		ticks++;
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		System.out.println("Reading node from NBT " + nbt);
		if(nbt.hasKey("focus_id") && nbt.hasKey("focus_meta"))
			focus = new ItemStack(Item.getItemById(nbt.getInteger("focus_id")), 1, nbt.getInteger("focus_meta"));
	}
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		if(focus!=null){
			nbt.setInteger("focus_id", Item.getIdFromItem(focus.getItem()));
			nbt.setInteger("focus_meta", focus.getItemDamage());
		}
		System.out.println("Writing node to NBT " + nbt);
	}
}

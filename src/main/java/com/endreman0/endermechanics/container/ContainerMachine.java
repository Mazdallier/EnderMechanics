package com.endreman0.endermechanics.container;

import com.endreman0.endermechanics.tile.TileInventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ContainerMachine extends Container{
	protected TileInventory tile;
	public ContainerMachine(InventoryPlayer inventoryPlayer, TileInventory tileEntity){
		tile = tileEntity;
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer){
		int startIndex = tile.getSizeInventory()+1;//First index of player inventories.
		for(int i = 0; i < 3; i++){
			for (int j = 0; j < 9; j++){
				addSlotToContainer(new Slot(inventoryPlayer, (i+1)*9 + j, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(int i = 0; i < 9; i++){
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player){
		return tile.isUseableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot){
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);
		if (slotObject != null && slotObject.getHasStack()){
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();
			
			//merges the item into player inventory since its in the tileEntity
			if (slot < 9){
				if (!this.mergeItemStack(stackInSlot, 0, 35, true)){
					return null;
				}
			}
			//places it into the tileEntity if possible since its in the player inventory
			else if (!this.mergeItemStack(stackInSlot, 0, 9, false)){
				return null;
			}
			
			if(stackInSlot.stackSize == 0){
				slotObject.putStack(null);
			}else{
				slotObject.onSlotChanged();
			}
			
			if(stackInSlot.stackSize == stack.stackSize){
				return null;
			}
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		return stack;
	}
}

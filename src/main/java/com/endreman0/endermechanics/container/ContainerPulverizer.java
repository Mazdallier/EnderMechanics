package com.endreman0.endermechanics.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

import com.endreman0.endermechanics.tile.TileInventory;

public class ContainerPulverizer extends ContainerMachine{
	public ContainerPulverizer(InventoryPlayer inventoryPlayer, TileInventory tileEntity){
		super(inventoryPlayer, tileEntity);
		super.addSlotToContainer(new Slot(tileEntity, 0, 34, 37));
		super.addSlotToContainer(new Slot(tileEntity, 1, 128, 37));
		bindPlayerInventory(inventoryPlayer);
	}
}

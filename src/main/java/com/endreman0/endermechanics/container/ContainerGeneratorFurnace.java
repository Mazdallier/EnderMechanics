package com.endreman0.endermechanics.container;

import com.endreman0.endermechanics.tile.TileInventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGeneratorFurnace extends ContainerMachine{
	public ContainerGeneratorFurnace(InventoryPlayer inventoryPlayer, TileInventory tileEntity){
		super(inventoryPlayer, tileEntity);
		super.addSlotToContainer(new Slot(tile, 0, 80, 39));//inventory, slot number, x, y
		bindPlayerInventory(inventoryPlayer);
	}
}

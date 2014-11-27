package com.endreman0.endermechanics.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

import com.endreman0.endermechanics.tile.TileInventory;

public class ContainerDefaultMachine extends ContainerBase{
	public ContainerDefaultMachine(InventoryPlayer player, TileInventory tile){
		super(player, tile);
		super.addSlotToContainer(new Slot(tile, 0, 34, 37));
		super.addSlotToContainer(new Slot(tile, 1, 128, 37));
		bindPlayerInventory(player);
	}
}

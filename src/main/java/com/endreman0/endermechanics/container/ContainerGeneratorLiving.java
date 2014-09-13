package com.endreman0.endermechanics.container;

import com.endreman0.endermechanics.tile.TileGeneratorEM;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerGeneratorLiving extends ContainerMachineEM{
	public ContainerGeneratorLiving(InventoryPlayer inventoryPlayer, TileGeneratorEM tileEntity){
		super(inventoryPlayer, tileEntity);
		super.addSlotToContainer(new Slot(tile, 0, 80, 39));
		bindPlayerInventory(inventoryPlayer);
	}
}

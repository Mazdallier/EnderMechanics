package com.endreman0.endermechanics.container;

import com.endreman0.endermechanics.tile.TileMachineEM;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerGeneratorLiving extends ContainerMachineEM{
	public ContainerGeneratorLiving(InventoryPlayer inventoryPlayer, TileMachineEM tileEntity){
		super(inventoryPlayer, tileEntity);
		super.addSlotToContainer(new Slot(tile, 0, 80, 39));
		bindPlayerInventory(inventoryPlayer);
	}
}

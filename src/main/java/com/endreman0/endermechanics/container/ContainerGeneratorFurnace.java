package com.endreman0.endermechanics.container;

import com.endreman0.endermechanics.tile.TileFunctionalEM;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGeneratorFurnace extends ContainerMachineEM{
	public ContainerGeneratorFurnace(InventoryPlayer inventoryPlayer, TileFunctionalEM tileEntity){
		super(inventoryPlayer, tileEntity);
		super.addSlotToContainer(new Slot(tile, 0, 80, 39));//inventory, slot number, x, y
		bindPlayerInventory(inventoryPlayer);
	}
}

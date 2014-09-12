package com.endreman0.endermechanics.container;

import com.endreman0.endermechanics.tile.TileGeneratorLiving;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGeneratorLiving extends ContainerMachineEM{
	public ContainerGeneratorLiving(InventoryPlayer inventoryPlayer, TileGeneratorLiving tileEntity){
		super(inventoryPlayer, tileEntity);
		super.addSlotToContainer(new Slot(tile, 0, 80, 39));
		bindPlayerInventory(inventoryPlayer);
	}
}

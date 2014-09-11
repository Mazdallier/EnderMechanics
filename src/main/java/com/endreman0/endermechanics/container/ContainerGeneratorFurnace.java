package com.endreman0.endermechanics.container;

import com.endreman0.endermechanics.tile.TileGeneratorFurnace;
import com.endreman0.endermechanics.tile.TileMachineEM;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGeneratorFurnace extends ContainerMachineEM{
	public ContainerGeneratorFurnace(InventoryPlayer inventoryPlayer, TileMachineEM tileEntity){
		super(inventoryPlayer, tileEntity);
		
		//Add slots
		super.addSlotToContainer(new Slot(tile, 0, 80, 39));//inventory, slot number, x, y
		
		bindPlayerInventory(inventoryPlayer);
	}
}

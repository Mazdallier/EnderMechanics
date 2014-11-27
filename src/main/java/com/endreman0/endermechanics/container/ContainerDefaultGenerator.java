package com.endreman0.endermechanics.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

import com.endreman0.endermechanics.tile.generator.TileGenerator;

public class ContainerDefaultGenerator extends ContainerBase{
	public ContainerDefaultGenerator(InventoryPlayer player, TileGenerator tile){
		super(player, tile);
		super.addSlotToContainer(new Slot(tile, 0, 80, 39));
		bindPlayerInventory(player);
	}
}

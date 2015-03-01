package com.endreman0.endermechanics.item.focus;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.endreman0.endermechanics.item.ItemMetadataEM;

public class ItemFocusEM extends ItemMetadataEM{
	public ItemFocusEM(){
		super("focus", 2);
		this.maxStackSize = 1;
	}
}

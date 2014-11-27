package com.endreman0.endermechanics.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

import com.endreman0.endermechanics.block.MultiBlockEM;
import com.endreman0.endermechanics.util.Utility;

public class ItemBlockEM extends ItemBlockWithMetadata{
	public ItemBlockEM(Block block){super(block, block);}
	@Override public String getUnlocalizedName(ItemStack stack){
		return "tile." + Utility.RESOURCE_PREFIX + ":" + ((MultiBlockEM)field_150939_a).names()[stack.getItemDamage()];
	}
}

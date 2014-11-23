package com.endreman0.endermechanics.block.itemblock;

import com.endreman0.endermechanics.block.BlockGenerator;
import com.endreman0.endermechanics.util.Utility;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockGenerator extends ItemBlockWithMetadata{
	public ItemBlockGenerator(Block block){super(block, block);}
	@Override public String getUnlocalizedName(ItemStack stack){
		return "tile." + Utility.RESOURCE_PREFIX + ":generator" + BlockGenerator.names[stack.getItemDamage()];
	}
}

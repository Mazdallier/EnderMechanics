package com.endreman0.endermechanics.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

import com.endreman0.endermechanics.block.BlockMachine;
import com.endreman0.endermechanics.util.Utility;

public class ItemBlockMachine extends ItemBlockWithMetadata{
	public ItemBlockMachine(Block block){super(block, block);}
	@Override public String getUnlocalizedName(ItemStack stack){
		return "tile." + Utility.RESOURCE_PREFIX + ":" + BlockMachine.names[stack.getItemDamage()];
	}
}

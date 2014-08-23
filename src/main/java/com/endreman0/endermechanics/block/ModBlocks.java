package com.endreman0.endermechanics.block;

import net.minecraft.block.BlockContainer;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks{
	public static final BlockContainer blockMachineFrame = new BlockMachineFrame();
	public static void init(){
		GameRegistry.registerBlock(blockMachineFrame, "blockMachineFrame");
	}
}

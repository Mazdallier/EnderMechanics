package com.endreman0.endermechanics.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks{
	public static final BlockContainer machineFrame = new BlockMachineFrame();
	public static final BlockContainer generator = new BlockGenerator();
	public static void init(){
		GameRegistry.registerBlock(machineFrame, "machineFrame");
		GameRegistry.registerBlock(generator, "generator");
	}
}

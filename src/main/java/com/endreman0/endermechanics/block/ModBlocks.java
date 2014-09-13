package com.endreman0.endermechanics.block;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks{
	public static final BlockEM machineFrame = new BlockMachineFrame();
	public static final BlockEM generatorFurnace = new BlockGeneratorFurnace();
	public static final BlockEM generatorLiving = new BlockGeneratorLiving();
	public static final BlockEM enderNode = new BlockEnderNode();
	public static void init(){
		GameRegistry.registerBlock(machineFrame, "machineFrame");
		GameRegistry.registerBlock(generatorFurnace, "generatorFurnace");
		GameRegistry.registerBlock(generatorLiving, "generatorLiving");
		GameRegistry.registerBlock(enderNode, "enderNode");
	}
}

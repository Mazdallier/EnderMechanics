package com.endreman0.endermechanics.block;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks{
	public static final MultiBlockEM generator = new BlockGenerator();
	public static final MultiBlockEM machine = new BlockMachine();
	public static final BlockEM machineFrame = new BlockMachineFrame();
	public static final BlockEM enderNode = new BlockEnderNode();
}

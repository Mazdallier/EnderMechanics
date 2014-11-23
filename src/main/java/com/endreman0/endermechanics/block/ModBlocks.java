package com.endreman0.endermechanics.block;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks{
	public static final BlockEM generator = new BlockGenerator();
	
	public static final BlockEM furnace = new BlockFurnaceEM();
	public static final BlockEM pulverizer = new BlockPulverizer();
	public static final BlockEM crystallizer = new BlockCrystallizer();
	public static final BlockEM enrichmentCell = new BlockEnrichmentCell();
	public static final BlockEM purifier = new BlockPurifier();
	
	public static final BlockEM machineFrame = new BlockMachineFrame();
	public static final BlockEM enderNode = new BlockEnderNode();
}

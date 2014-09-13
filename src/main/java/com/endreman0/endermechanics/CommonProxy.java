package com.endreman0.endermechanics;

import com.endreman0.endermechanics.tile.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy{
	public void registerRenders(){}
	public void registerEntities(){
		//EntityRegistry.registerModEntity(Class EntityClass, String unlocalizedName, int modSpecificID, EnderMechanics.instance, int trackingRange, int updateFrequency, true);
	}
	public void registerTileEntities(){
		GameRegistry.registerTileEntity(TileMachineFrame.class, "machineFrame");
		GameRegistry.registerTileEntity(TileGeneratorFurnace.class, "generatorFurnace");
		GameRegistry.registerTileEntity(TileGeneratorLiving.class, "generatorLiving");
		GameRegistry.registerTileEntity(TileEnderNode.class, "enderNode");
	}
}

package com.endreman0.endermechanics;

import com.endreman0.endermechanics.tile.TileEntityMachineFrame;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
	public void registerRenders(){}
	public void registerEntities(){
		//EntityRegistry.registerModEntity(Class EntityClass, String unlocalizedName, int modSpecificID, EnderMechanics.instance, int trackingRange, int updateFrequency, true);
	}
	public void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityMachineFrame.class, "tileMachineFrame");
	}
}

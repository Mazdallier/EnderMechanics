package com.endreman0.endermechanics;

import com.endreman0.endermechanics.render.RenderMachineFrame;
import com.endreman0.endermechanics.tile.TileEntityMachineFrame;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{
	@Override
	public void registerRenders(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineFrame.class, new RenderMachineFrame());
	}
}

package com.endreman0.endermechanics;

import com.endreman0.endermechanics.render.*;
import com.endreman0.endermechanics.tile.*;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{
	@Override
	public void registerRenders(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineFrame.class, new RenderMachineFrame());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnderNode.class, new RenderEnderNode());
	}
}

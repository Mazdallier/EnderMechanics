package com.endreman0.endermechanics;

//import com.endreman0.endermechanics.entity.*;
//import com.endreman0.endermechanics.item.ModItems;
import com.endreman0.endermechanics.render.*;
import com.endreman0.endermechanics.tile.*;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{
	@Override
	public void itemRenders(){
		
	}
	@Override
	public void tileRenders(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileMachineFrame.class, new RenderMachineFrame());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEnderNode.class, new RenderEnderNode());
	}
	@Override
	public void entityRenders(){
		
	}
}

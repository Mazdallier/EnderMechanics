package com.endreman0.endermechanics;

import com.endreman0.endermechanics.render.RenderRiftNode;
import com.endreman0.endermechanics.render.RenderSyncFrame;
import com.endreman0.endermechanics.tile.TileRiftNode;
import com.endreman0.endermechanics.tile.TileSyncFrame;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{
	@Override
	public void itemRenders(){
		
	}
	@Override
	public void tileRenders(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileRiftNode.class, new RenderRiftNode());
		ClientRegistry.bindTileEntitySpecialRenderer(TileSyncFrame.class, new RenderSyncFrame());
	}
	@Override
	public void entityRenders(){
		
	}
}

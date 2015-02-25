package com.endreman0.endermechanics;

import org.lwjgl.opengl.GL11;

import com.endreman0.endermechanics.render.RenderFramable;
import com.endreman0.endermechanics.render.RenderMachineFrame;
import com.endreman0.endermechanics.tile.TileRiftNode;
import com.endreman0.endermechanics.tile.TileSyncFrame;
import com.endreman0.endermechanics.tile.generator.*;
import com.endreman0.endermechanics.tile.machine.*;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{
	@Override
	public void itemRenders(){
		
	}
	@Override
	public void tileRenders(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileGeneratorFurnace.class, new RenderFramable(){public void recolor(boolean active){GL11.glColor3d(active ? 0.75 : 0.25, active ? 0.5 : 0, 0);}});
		ClientRegistry.bindTileEntitySpecialRenderer(TileGeneratorLiving.class, new RenderFramable(){public void recolor(boolean active){GL11.glColor3d(0, active ? 0.9 : 0.45, 0);}});
		ClientRegistry.bindTileEntitySpecialRenderer(TileGeneratorTool.class, new RenderFramable(){public void recolor(boolean active){double color = active ? 0.6 : 0.4; GL11.glColor3d(color, color, color);}});
		ClientRegistry.bindTileEntitySpecialRenderer(TileGeneratorPotion.class, new RenderFramable(){public void recolor(boolean active){GL11.glColor3d(active ? 0.45 : 0.2, 0, active ? 0.45 : 0.2);}});
		ClientRegistry.bindTileEntitySpecialRenderer(TileGeneratorNetherStar.class, new RenderFramable(){public void recolor(boolean active){GL11.glColor3d(0, active ? 0.1 : 0, active ? 0.2 : 0.1);}});
		ClientRegistry.bindTileEntitySpecialRenderer(TileGeneratorCreative.class, new RenderFramable(){public void recolor(boolean active){GL11.glColor3d(1, 0 , 1);}});
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileFurnaceEM.class, new RenderFramable(){public void recolor(boolean active){GL11.glColor3d(active ? 1 : 0.5, 0, 0);}});
		ClientRegistry.bindTileEntitySpecialRenderer(TilePulverizer.class, new RenderFramable(){public void recolor(boolean active){GL11.glColor3d(0, 0, active ? 0.25 : 0);}});
		ClientRegistry.bindTileEntitySpecialRenderer(TileCrystallizer.class, new RenderFramable(){public void recolor(boolean active){GL11.glColor3d(0, 0, active ? 0.5 : 0);}});
		ClientRegistry.bindTileEntitySpecialRenderer(TileEnrichmentCell.class, new RenderFramable(){public void recolor(boolean active){GL11.glColor3d(0, 0, active ? 0.75 : 0);}});
		ClientRegistry.bindTileEntitySpecialRenderer(TilePurifier.class, new RenderFramable(){public void recolor(boolean active){GL11.glColor3d(0, 0, active ? 1 : 0);}});
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileSyncFrame.class, new RenderMachineFrame());
		ClientRegistry.bindTileEntitySpecialRenderer(TileRiftNode.class, new RenderFramable(){public void recolor(boolean active){GL11.glColor3d(0, 0, active ? 0.25 : 0.15);}});
	}
	@Override
	public void entityRenders(){
		
	}
}

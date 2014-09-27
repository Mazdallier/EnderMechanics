package com.endreman0.endermechanics.render;

import org.lwjgl.opengl.GL11;

import com.endreman0.endermechanics.tile.TileEnderNode;
import com.endreman0.endermechanics.util.Utility;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderEnderNode extends TileEntitySpecialRenderer{
	private ModelEnderNode model;
	public RenderEnderNode() {
		model = new ModelEnderNode();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale) {
		TileEnderNode tileEN = (TileEnderNode)tile;
		GL11.glPushMatrix();
		GL11.glTranslated(x+0.5D, y-0.5D, z+0.5D);
		GL11.glPushMatrix();
		if(tileEN.network()==null){
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Utility.RESOURCE_PREFIX, "models/enderNodeLoading.png"));
		}else if(tileEN.network().nodes()<=1){
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Utility.RESOURCE_PREFIX, "models/enderNodeAlone.png"));
		}else{
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Utility.RESOURCE_PREFIX, "models/enderNode.png"));
		}
		model.render(null, tileEN.ticks(), 0F, -0.1F, 0F, 0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}

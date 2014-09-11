package com.endreman0.endermechanics.render;

import org.lwjgl.opengl.GL11;

import com.endreman0.endermechanics.block.ModBlocks;
import com.endreman0.endermechanics.tile.TileMachineFrame;
import com.endreman0.endermechanics.util.Utility;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderMachineFrame extends TileEntitySpecialRenderer{
	private final ModelMachineFrame model;
	public RenderMachineFrame(){
		model = new ModelMachineFrame();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale) {
		TileMachineFrame tileMF = (TileMachineFrame)tile;
		GL11.glPushMatrix();
		GL11.glTranslated(x+0.5D, y-0.5D, z+0.5D);
		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Utility.RESOURCE_PREFIX, "models/machineFrame.png"));
		model.render(null, 0F, 0F, -0.1F, 0F, 0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}

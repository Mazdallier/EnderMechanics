package com.endreman0.endermechanics.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.endreman0.endermechanics.api.IFramableMachine;
import com.endreman0.endermechanics.util.Utility;

public abstract class RenderFramable extends TileEntitySpecialRenderer{
	protected static ResourceLocation base = new ResourceLocation(Utility.RESOURCE_PREFIX, "models/framableBase.png");
	protected static ResourceLocation top = new ResourceLocation(Utility.RESOURCE_PREFIX, "models/framableTop.png");
	protected ModelFramable model = new ModelFramable();
	protected abstract void recolor(boolean active);
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale){
		IFramableMachine machine = (IFramableMachine)tile;
		GL11.glPushMatrix();
		GL11.glTranslated(x+0.5D, y-0.5D, z+0.5D); recolor(true);
		Minecraft.getMinecraft().renderEngine.bindTexture(base);
		model.render(null, machine.ticks(), 0F, -0.1F, 0F, 0F, 0.0625F);//Render the frame
		recolor(machine.isActive());
		Minecraft.getMinecraft().renderEngine.bindTexture(top);
		model.render(null, machine.ticks(), 0F, -0.1F, 0F, 0F, 0.0625F);//Render the corners
		GL11.glPopMatrix();
	}
	
}

package com.endreman0.endermechanics.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.endreman0.endermechanics.tile.TileRiftNode;
import com.endreman0.endermechanics.util.Utility;

public class RenderRiftNode extends TileEntitySpecialRenderer{
	private ModelRiftNode model = new ModelRiftNode();
	public RenderRiftNode(){
		// TODO Auto-generated constructor stub
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale) {
		TileRiftNode node = (TileRiftNode)tile;
		GL11.glPushMatrix();
		GL11.glTranslated(x+0.5D, y-0.5D, z+0.5D);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Utility.RESOURCE_PREFIX, "models/riftNode.png"));
		model.render(null, 0F, 0F, -0.1F, 0F, 0F, 0.0625F);
		GL11.glPopMatrix();
		
		ItemStack focus = node.focus();
		if(focus==null) return;
		EntityItem entity = new EntityItem(node.getWorldObj(), x, y, z, focus);
		entity.hoverStart = 0;
		RenderItem.renderInFrame = false;
		GL11.glPushMatrix();
		GL11.glTranslated(x+0.5, y+0.5, z+0.5);
		GL11.glRotatef(180, 0, 1, 1);
		RenderManager.instance.renderEntityWithPosYaw(entity, 0, 0, 0, 0, 0);
		GL11.glPopMatrix();
	}
}

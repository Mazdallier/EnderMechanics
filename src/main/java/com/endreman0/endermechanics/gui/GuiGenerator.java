package com.endreman0.endermechanics.gui;

import org.lwjgl.opengl.GL11;

import com.endreman0.endermechanics.Utility;
import com.endreman0.endermechanics.container.ContainerGenerator;
import com.endreman0.endermechanics.tile.TileEntityGenerator;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiGenerator extends GuiContainer{
	private TileEntityGenerator tile;
	public GuiGenerator(InventoryPlayer inventory, TileEntityGenerator tileEntity) {
		super(new ContainerGenerator(inventory, tileEntity));
		tile = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2){
		fontRendererObj.drawString(StatCollector.translateToLocal(tile.getInventoryName()), 8, 6, 0x000000);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 0x000000);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3){
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation(Utility.RESOURCE_PREFIX, "textures/gui/GuiGenerator.png"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		//x, y, u, v, width, height
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}

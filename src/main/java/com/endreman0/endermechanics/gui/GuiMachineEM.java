package com.endreman0.endermechanics.gui;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import com.endreman0.endermechanics.container.ContainerGeneratorLiving;
import com.endreman0.endermechanics.container.ContainerMachineEM;
import com.endreman0.endermechanics.tile.TileGeneratorLiving;
import com.endreman0.endermechanics.tile.TileMachineEM;
import com.endreman0.endermechanics.util.Utility;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

public abstract class GuiMachineEM extends GuiContainer{
	protected TileMachineEM tile;
	protected ResourceLocation texture;
	public GuiMachineEM(ContainerMachineEM container, TileMachineEM tileEntity){//Constructing the container here causes a crash.
		super(container);
		tile = tileEntity;
		texture = new ResourceLocation(Utility.RESOURCE_PREFIX, "textures/gui/" + tileEntity.getInventoryName().substring(tileEntity.getInventoryName().indexOf(':')+1) + ".png");//GUIs folder, texture with machine's name
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		fontRendererObj.drawString(StatCollector.translateToLocal(tile.getInventoryName().concat(".name")), 8, 6, 0x404040);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 92, 0x404040);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY){
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect((width-xSize)/2, (height - ySize) / 2, 0, 0, xSize, ySize);//x, y, u, v, width, height
	}
	//Copied from Mekanism GuiDynamicTank.displayGauge(). Everything I tried failed; this worked and is open-source.
	protected void drawFluid(int xPos, int yPos, int scale1, FluidTankInfo tank){
		if(tank.fluid == null){return;}
		int scale = tank.fluid.amount*scale1/tank.capacity;
		
		int guiWidth = (width - xSize) / 2;
		int guiHeight = (height - ySize) / 2;
		
		int start = 0;
		
		while(true){
			int renderRemaining = 0;
			if(scale>16){
				renderRemaining = 16;
				scale-=16;
			}else{
				renderRemaining = scale;
				scale = 0;
			}

			mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			drawTexturedModelRectFromIcon(guiWidth+xPos, guiHeight+yPos+58-renderRemaining-start, tank.fluid.getFluid().getIcon(), 16, renderRemaining);
			start+=16;

			if(renderRemaining==0 || scale==0){break;}
		}
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiWidth+xPos, guiHeight+yPos+5, xSize, 14, 16, 53);
	}
	protected void drawTooltip(int mouseX, int mouseY, String... text){drawHoveringText(Arrays.asList(text), mouseX, mouseY, fontRendererObj);}
	protected void drawTankTooltip(FluidTankInfo tank, int mouseX, int mouseY, int minX, int minY, int maxX, int maxY){
		if(mouseX<minX || mouseY<minY || mouseX>maxX || mouseY>maxY) return;
		FluidStack fluid = tank.fluid;
		if(fluid==null){
			drawTooltip(mouseX, mouseY, "Empty");
		}else{
			drawTooltip(mouseX, mouseY, fluid.getFluid().getLocalizedName(fluid), fluid.amount + "mB/" + tank.capacity + "mB");
		}
	}
}

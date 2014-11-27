package com.endreman0.endermechanics.gui;

import java.util.Arrays;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

import org.lwjgl.opengl.GL11;

import com.endreman0.endermechanics.container.ContainerBase;
import com.endreman0.endermechanics.tile.TileInventory;
import com.endreman0.endermechanics.util.Log;
import com.endreman0.endermechanics.util.Utility;

public abstract class GuiBase extends GuiContainer{
	public static final int u = 176;
	protected TileInventory tile;
	public static final ResourceLocation texture = new ResourceLocation(Utility.RESOURCE_PREFIX, "textures/gui/base.png");
	public GuiBase(ContainerBase container, TileInventory tile){super(container); this.tile = tile;}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		fontRendererObj.drawString(StatCollector.translateToLocal(tile.getInventoryName()+".name"), 8, 6, 0x404040);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 92, 0x404040);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY){
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect((width-xSize)/2, (height - ySize) / 2, 0, 0, xSize, ySize);//x, y, u, v, width, height
	}
	//Copied from Mekanism GuiDynamicTank.displayGauge(). Everything I tried failed; this worked and is open-source.
	protected void drawFluid(int xPos, int yPos, FluidTankInfo tank){
		int guiWidth = (width - xSize) / 2;
		int guiHeight = (height - ySize) / 2;
		
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiWidth+xPos, guiHeight+yPos, u+18, 0, 18, 60);//Tank background
		
		if(tank.fluid!=null){//Fluid
			int scale = tank.fluid.amount*58/tank.capacity;//58 is a constant supplied as an argument in Mekanism.
			
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
				drawTexturedModelRectFromIcon(guiWidth+xPos+1, guiHeight+yPos+59-renderRemaining-start, tank.fluid.getFluid().getIcon(), 16, renderRemaining);
				//Original arg0 and arg1 have been increased by 1 to account for 1 pixel of padding each side of the tank background image.
				start+=16;
	
				if(renderRemaining==0 || scale==0){break;}
			}
		}
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiWidth+xPos, guiHeight+yPos, u+36, 0, 18, 60);//Tank bars
	}
	protected void drawInvSlot(int x, int y){
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect((width-xSize)/2 + x-1, (height-ySize)/2 + y-1, u, 32, 18, 18);//Subtracting 1 from the given coordinates accounts for the border.
	}
	protected void drawFire(int x, int y, int amt){//Draws the furnace's fire icon; amt=0 means no fire, just the background; amt=16 means full fire
		int xPos = (width-xSize)/2 + x + 1;//Accounts for 1px of padding on each side
		int yPos = (height-ySize)/2 + y + 1;
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(xPos, yPos, u+1, 0, 16, 16);//Background
		drawTexturedModalRect(xPos, yPos+(16-amt), u+1, 32-amt, 16, amt);//Fire; set upper-left corner at upper-left corner of whatever is to be drawn, be it nothing or full. Then draw a cut-off section.
	}
	protected void drawGeneratorPower(int x, int y){//Draws the vertical power bar used in generators
		int xPos = (width-xSize)/2 + x + 1;
		int yPos = (height-ySize)/2 + y + 1;
		float amt = (float)tile.getPower(ForgeDirection.UNKNOWN);
		float max = (float)tile.getMaxPower(ForgeDirection.UNKNOWN);
		float height = (amt/max * 60F);
		int v = (int)height;
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(xPos, yPos, u+18, 60, 18, 60);//Background
		drawTexturedModalRect(xPos, yPos+(60-v), u+36, 120-v, 18, v);//Power bar
	}
	protected void drawMachinePower(int x, int y){//Draw the horizontal power/progress bar used by machines
		int xPos = (width-xSize)/2 + x + 1;
		int yPos = (height-ySize)/2 + y + 1;
		float amt = (float)tile.getPower(ForgeDirection.UNKNOWN);
		float max = (float)tile.getMaxPower(ForgeDirection.UNKNOWN);
		float w = (amt/max * 60F);
		int width = (int)w;
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(xPos, yPos, u, 120, 60, 10);//Background
		drawTexturedModalRect(xPos, yPos, u, 130, width, 10);//Power Bar
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
	protected void drawPowerTooltip(int mouseX, int mouseY, int minX, int minY, int maxX, int maxY){//Power data comes from 'tile' field
		if(mouseX<minX || mouseY<minY || mouseX>maxX || mouseY>maxY) return;
		drawTooltip(mouseX, mouseY, Utility.powerString(tile.getPower(ForgeDirection.UNKNOWN))+'/'+Utility.powerString(tile.getMaxPower(ForgeDirection.UNKNOWN)));
	}
}

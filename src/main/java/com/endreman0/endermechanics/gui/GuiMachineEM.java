package com.endreman0.endermechanics.gui;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import com.endreman0.endermechanics.container.ContainerMachineEM;
import com.endreman0.endermechanics.tile.TileFunctionalEM;
import com.endreman0.endermechanics.util.LogHelper;
import com.endreman0.endermechanics.util.Utility;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

public abstract class GuiMachineEM extends GuiContainer{
	protected TileFunctionalEM tile;
	protected ResourceLocation texture;
	public GuiMachineEM(ContainerMachineEM container, TileFunctionalEM tileEntity){//Constructing the container here causes a crash.
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
	protected void drawFluid(int xPos, int yPos, FluidTankInfo tank){
		int guiWidth = (width - xSize) / 2;
		int guiHeight = (height - ySize) / 2;
		
		mc.renderEngine.bindTexture(Utility.GUI_UTILS);
		drawTexturedModalRect(guiWidth+xPos, guiHeight+yPos, 16, 0, 18, 60);//Tank background
		
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
		mc.renderEngine.bindTexture(Utility.GUI_UTILS);
		drawTexturedModalRect(guiWidth+xPos, guiHeight+yPos, 16, 60, 18, 60);//Tank bars
	}
	protected void drawFire(int x, int y, int v){//Draws the furnace's fire icon; v==0 means no fire, just the background, v==16 means full fire
		int xPos = (width-xSize)/2 + x + 1;//Accounts for 1px of padding on each side
		int yPos = (height-ySize)/2 + y + 1;
		mc.renderEngine.bindTexture(Utility.GUI_UTILS);
		drawTexturedModalRect(xPos, yPos, 0, 0, 16, 16);//Background
		drawTexturedModalRect(xPos, yPos+(16-v), 0, 32-v, 16, v);//Fire; set upper-left corner at upper-left corner of whatever is to be drawn, be it nothing or full. Then draw a cut-off section.
	}
	protected void drawPower(int x, int y){//, int power, int maxPower){//Draws the vertical power bar
		int xPos = (width-xSize)/2 + x + 1;
		int yPos = (height-ySize)/2 + y + 1;
		float amt = (float)tile.getPower(ForgeDirection.UNKNOWN);
		float max = (float)tile.getMaxPower(ForgeDirection.UNKNOWN);
		float height = (amt/max * 60F);
		int v = (int)height;
		mc.renderEngine.bindTexture(Utility.GUI_UTILS);
		drawTexturedModalRect(xPos, yPos, 34, 0, 18, 60);//Background
		drawTexturedModalRect(xPos, yPos+(60-v), 34, 120-v, 60, v);//Power bar
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
		drawTooltip(mouseX, mouseY, getStringFromPower(tile.getPower(ForgeDirection.UNKNOWN))+'/'+getStringFromPower(tile.getMaxPower(ForgeDirection.UNKNOWN)));
	}
	private String getStringFromPower(int power){
		float scaledPower = power;
		String unit;
		if(power>=1000000){//1 million Endergy = 1mE (megaEndergy)
			scaledPower/=1000000;
			unit="mE";
		}else if(power>=1000){//1 thousand Endergy = 1kE (kiloEndergy)
			scaledPower/=1000;
			unit="kE";
		}else{
			unit = "E";
		}
		String string = String.valueOf(scaledPower);
		if(scaledPower==Math.floor(scaledPower) && string.contains(".")){//If it's a whole number, just return the whole part.
			string = string.substring(0, string.indexOf('.'));
		}else{
			if(string.length()>4) string = string.substring(0, 4);//Truncate to two decimal points
		}
		return string + unit;
	}
}

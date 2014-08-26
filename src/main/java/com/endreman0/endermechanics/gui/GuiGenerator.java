package com.endreman0.endermechanics.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import scala.actors.threadpool.Arrays;

import com.endreman0.endermechanics.LogHelper;
import com.endreman0.endermechanics.Utility;
import com.endreman0.endermechanics.container.ContainerGenerator;
import com.endreman0.endermechanics.tile.TileEntityGenerator;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

public class GuiGenerator extends GuiContainer{
	private TileEntityGenerator tile;
	public GuiGenerator(InventoryPlayer inventory, TileEntityGenerator tileEntity){
		super(new ContainerGenerator(inventory, tileEntity));
		tile = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		fontRendererObj.drawString(StatCollector.translateToLocal(tile.getInventoryName()), 8, 6, 0x000000);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 92, 0x000000);
		
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int par2, int par3){
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation(Utility.RESOURCE_PREFIX, "textures/gui/GuiGenerator.png"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);//x, y, u, v, width, height
		
		drawFluid(56, 18, 58, getTankInfo().fluid);
	}
	//Copied from Mekanism GuiDynamicTank.displayGauge(). Everything I tried failed; this worked and is open-source.
	public void drawFluid(int xPos, int yPos, int scale1, FluidStack fluid){
		if(fluid == null){return;}
		int scale = fluid.amount*scale1/getTankInfo().capacity;
		
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
			drawTexturedModelRectFromIcon(guiWidth+xPos, guiHeight+yPos+58-renderRemaining-start, fluid.getFluid().getIcon(), 16, renderRemaining);
			start+=16;

			if(renderRemaining==0 || scale==0){break;}
		}
		mc.renderEngine.bindTexture(new ResourceLocation(Utility.RESOURCE_PREFIX, "textures/gui/GuiGenerator.png"));
		drawTexturedModalRect(guiWidth+xPos, guiHeight+yPos+5, xSize, 14, 16, 54);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick){
		super.drawScreen(mouseX, mouseY, partialTick);
		if(mouseX>=180 && mouseX<=196 && mouseY>=54 && mouseY<=112){//Over tank
			FluidStack fluid = getTankInfo().fluid;
			List<String> list = new ArrayList<String>();
			if(fluid!=null && fluid.getFluid()!=null){
				list.add(fluid.getFluid().getLocalizedName(fluid));//Deprecated, but it's the one thing that works. Not even copy-pasting the code.
				list.add(fluid.amount + "mB/" + getTankInfo().capacity + "mB");
			}else{
				list.add("0mB/" + getTankInfo().capacity + "mB");
			}
			drawHoveringText(list, mouseX, mouseY, fontRendererObj);
		}
	}
	private FluidTankInfo getTankInfo(){return tile.getTankInfo(ForgeDirection.UNKNOWN)[0];}
	//tile.getTankInfo takes a direction and returns an array with one FluidTankInfo per tank accessible on that side.
}

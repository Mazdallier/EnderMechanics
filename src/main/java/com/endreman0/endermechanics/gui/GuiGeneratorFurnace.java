package com.endreman0.endermechanics.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.endreman0.endermechanics.container.ContainerGeneratorFurnace;
import com.endreman0.endermechanics.container.ContainerGeneratorLiving;
import com.endreman0.endermechanics.tile.TileGeneratorFurnace;
import com.endreman0.endermechanics.tile.TileMachineEM;
import com.endreman0.endermechanics.util.Utility;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class GuiGeneratorFurnace extends GuiMachineEM{
	public GuiGeneratorFurnace(InventoryPlayer inventoryPlayer, TileMachineEM tileEntity){
		super(new ContainerGeneratorFurnace(inventoryPlayer, tileEntity), tileEntity);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY){
		super.drawGuiContainerBackgroundLayer(partialTick, mouseX, mouseY);
		
		drawFluid(56, 18, 58, getTankInfo());
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick){
		super.drawScreen(mouseX, mouseY, partialTick);
		drawTankTooltip(getTankInfo(), mouseX, mouseY, 180, 54, 196, 112);//Putting this in drawScreen (after the super) and not in drawGuiForegroundLayer puts tooltips in front of item stacks.
	}
	private FluidTankInfo getTankInfo(){return ((IFluidHandler)tile).getTankInfo(ForgeDirection.UNKNOWN)[0];}
	//tile.getTankInfo takes a direction and returns an array with one FluidTankInfo per tank accessible on that side.
}

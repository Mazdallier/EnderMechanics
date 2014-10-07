package com.endreman0.endermechanics.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import com.endreman0.endermechanics.container.ContainerGeneratorFurnace;
import com.endreman0.endermechanics.tile.TileInventory;

public class GuiGeneratorFurnace extends GuiMachine{
	public GuiGeneratorFurnace(InventoryPlayer inventoryPlayer, TileInventory tileEntity){
		super(new ContainerGeneratorFurnace(inventoryPlayer, tileEntity), tileEntity);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY){
		super.drawGuiContainerBackgroundLayer(partialTick, mouseX, mouseY);
		drawFire(80, 57, 0);
		drawFluid(56, 15, getTankInfo());
		drawGeneratorPower(xSize-30, 10);
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick){
		super.drawScreen(mouseX, mouseY, partialTick);
		//Putting this in drawScreen (after the super) and not in drawGuiForegroundLayer puts tooltips in front of item stacks.
		drawTankTooltip(getTankInfo(), mouseX, mouseY, 180, 54, 196, 112);
		drawPowerTooltip(mouseX, mouseY, 272, 48, 288, 107);
	}
	
	private FluidTankInfo getTankInfo(){return ((IFluidHandler)tile).getTankInfo(ForgeDirection.UNKNOWN)[0];}
}

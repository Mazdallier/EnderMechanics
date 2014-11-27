package com.endreman0.endermechanics.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import com.endreman0.endermechanics.tile.generator.TileGeneratorFurnace;

public class GuiGeneratorFurnace extends GuiDefaultGenerator{
	public GuiGeneratorFurnace(InventoryPlayer player, TileGeneratorFurnace tile){super(player, tile);}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY){
		super.drawGuiContainerBackgroundLayer(partialTick, mouseX, mouseY);
		drawFluid(56, 15, getTankInfo());
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick){
		super.drawScreen(mouseX, mouseY, partialTick);
		drawTankTooltip(getTankInfo(), mouseX, mouseY, 180, 54, 196, 112);
	}
	
	private FluidTankInfo getTankInfo(){return ((IFluidHandler)tile).getTankInfo(ForgeDirection.UNKNOWN)[0];}
}

package com.endreman0.endermechanics.gui;

import net.minecraft.entity.player.InventoryPlayer;

import com.endreman0.endermechanics.container.ContainerFurnaceEM;
import com.endreman0.endermechanics.tile.TileInventory;

public class GuiFurnaceEM extends GuiMachine{
	public GuiFurnaceEM(InventoryPlayer inventoryPlayer, TileInventory tileEntity){
		super(new ContainerFurnaceEM(inventoryPlayer, tileEntity), tileEntity);
	}
	@Override
	public void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY){
		super.drawGuiContainerBackgroundLayer(partialTick, mouseX, mouseY);
		drawMachinePower(58, 40);
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick){
		super.drawScreen(mouseX, mouseY, partialTick);
		drawPowerTooltip(mouseX, mouseY, 184, 77, 243, 87);
	}
}
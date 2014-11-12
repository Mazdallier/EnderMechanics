package com.endreman0.endermechanics.gui;

import net.minecraft.entity.player.InventoryPlayer;

import com.endreman0.endermechanics.container.ContainerGrinder;
import com.endreman0.endermechanics.tile.TileInventory;

public class GuiGrinder extends GuiMachine{
	public GuiGrinder(InventoryPlayer inventoryPlayer, TileInventory tileEntity){
		super(new ContainerGrinder(inventoryPlayer, tileEntity), tileEntity);
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

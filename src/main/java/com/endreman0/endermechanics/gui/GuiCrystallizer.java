package com.endreman0.endermechanics.gui;

import net.minecraft.entity.player.InventoryPlayer;

import com.endreman0.endermechanics.container.ContainerCrystallizer;
import com.endreman0.endermechanics.tile.TileInventory;

public class GuiCrystallizer extends GuiMachine{
	public GuiCrystallizer(InventoryPlayer inventoryPlayer, TileInventory tileEntity){
		super(new ContainerCrystallizer(inventoryPlayer, tileEntity), tileEntity);
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

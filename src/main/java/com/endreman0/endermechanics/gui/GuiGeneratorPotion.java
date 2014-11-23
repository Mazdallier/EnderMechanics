package com.endreman0.endermechanics.gui;

import net.minecraft.entity.player.InventoryPlayer;

import com.endreman0.endermechanics.container.ContainerGeneratorPotion;
import com.endreman0.endermechanics.container.ContainerMachine;
import com.endreman0.endermechanics.tile.TileInventory;

public class GuiGeneratorPotion extends GuiMachine{
	public GuiGeneratorPotion(InventoryPlayer inventoryPlayer, TileInventory tileEntity){
		super(new ContainerGeneratorPotion(inventoryPlayer, tileEntity), tileEntity);
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY){
		super.drawGuiContainerBackgroundLayer(partialTick, mouseX, mouseY);
		drawFire(80, 57, 0);
		drawGeneratorPower(xSize-30, 10);
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick){
		super.drawScreen(mouseX, mouseY, partialTick);
		drawPowerTooltip(mouseX, mouseY, 272, 48, 288, 107);
	}
}

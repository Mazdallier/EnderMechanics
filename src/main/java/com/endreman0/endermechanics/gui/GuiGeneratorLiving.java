package com.endreman0.endermechanics.gui;

import com.endreman0.endermechanics.container.ContainerGeneratorLiving;
import com.endreman0.endermechanics.tile.TileInventory;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.common.util.ForgeDirection;

public class GuiGeneratorLiving extends GuiMachine{
	public GuiGeneratorLiving(InventoryPlayer inventoryPlayer, TileInventory tileEntity){
		super(new ContainerGeneratorLiving(inventoryPlayer, tileEntity), tileEntity);
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

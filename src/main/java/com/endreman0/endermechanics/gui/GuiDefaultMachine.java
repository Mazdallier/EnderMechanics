package com.endreman0.endermechanics.gui;

import net.minecraft.entity.player.InventoryPlayer;

import com.endreman0.endermechanics.container.ContainerDefaultMachine;
import com.endreman0.endermechanics.tile.machine.TileMachine;

public class GuiDefaultMachine extends GuiBase{
	public GuiDefaultMachine(InventoryPlayer player, TileMachine tile){
		super(new ContainerDefaultMachine(player, tile), tile);
	}
	@Override
	public void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY){
		super.drawGuiContainerBackgroundLayer(partialTick, mouseX, mouseY);
		drawMachinePower(58, 40);
		drawInvSlot(34, 37);
		drawInvSlot(128, 37);
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick){
		super.drawScreen(mouseX, mouseY, partialTick);
		drawPowerTooltip(mouseX, mouseY, 184, 77, 243, 87);
	}
}

package com.endreman0.endermechanics.gui;

import com.endreman0.endermechanics.container.ContainerGeneratorLiving;
import com.endreman0.endermechanics.tile.TileMachineEM;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.common.util.ForgeDirection;

public class GuiGeneratorLiving extends GuiMachineEM{
	public GuiGeneratorLiving(InventoryPlayer inventoryPlayer, TileMachineEM tileEntity){
		super(new ContainerGeneratorLiving(inventoryPlayer, tileEntity), tileEntity);
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY){
		super.drawGuiContainerBackgroundLayer(partialTick, mouseX, mouseY);
		drawFire(80, 57, 0);
		drawPower(xSize-30, 10, tile.getPower(ForgeDirection.UNKNOWN), tile.getMaxPower(ForgeDirection.UNKNOWN));
	}
}

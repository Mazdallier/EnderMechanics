package com.endreman0.endermechanics.gui;

import net.minecraft.entity.player.InventoryPlayer;

import com.endreman0.endermechanics.container.ContainerDefaultGenerator;
import com.endreman0.endermechanics.tile.generator.TileGenerator;

public class GuiDefaultGenerator extends GuiBase{
	protected TileGenerator generator;
	public GuiDefaultGenerator(InventoryPlayer player, TileGenerator tile){
		super(new ContainerDefaultGenerator(player, tile), tile);
		generator = (TileGenerator)tile;
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY){
		super.drawGuiContainerBackgroundLayer(partialTick, mouseX, mouseY);
		drawInvSlot(80, 39);
		drawGeneratorPower(xSize-30, 10);
		if(generator.life()>0){
			double amt = generator.running()/generator.life();
			drawFire(80, 57, (int)(16*amt));
		}else{
			drawFire(80, 57, 0);
		}
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick){
		super.drawScreen(mouseX, mouseY, partialTick);
		drawPowerTooltip(mouseX, mouseY, 272, 48, 288, 107);
	}
}

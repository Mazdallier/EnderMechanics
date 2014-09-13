package com.endreman0.endermechanics.gui;

import com.endreman0.endermechanics.container.ContainerGeneratorFurnace;
import com.endreman0.endermechanics.tile.TileMachineEM;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class GuiGeneratorFurnace extends GuiMachineEM{
	public GuiGeneratorFurnace(InventoryPlayer inventoryPlayer, TileMachineEM tileEntity){
		super(new ContainerGeneratorFurnace(inventoryPlayer, tileEntity), tileEntity);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY){
		super.drawGuiContainerBackgroundLayer(partialTick, mouseX, mouseY);
		drawFire(80, 57, 0);
		drawFluid(56, 15, getTankInfo());
		drawPower(xSize-30, 10, tile.getPower(ForgeDirection.UNKNOWN), tile.getMaxPower(ForgeDirection.UNKNOWN));
//		drawPower(xSize-30, 10, 60, 0);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick){
		super.drawScreen(mouseX, mouseY, partialTick);
		drawTankTooltip(getTankInfo(), mouseX, mouseY, 180, 54, 196, 112);//Putting this in drawScreen (after the super) and not in drawGuiForegroundLayer puts tooltips in front of item stacks.
	}
	private FluidTankInfo getTankInfo(){return ((IFluidHandler)tile).getTankInfo(ForgeDirection.UNKNOWN)[0];}
}

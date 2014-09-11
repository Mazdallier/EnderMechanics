package com.endreman0.endermechanics.gui;

import org.lwjgl.opengl.GL11;

import com.endreman0.endermechanics.container.ContainerGeneratorLiving;
import com.endreman0.endermechanics.tile.TileGeneratorLiving;
import com.endreman0.endermechanics.util.Utility;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiGeneratorLiving extends GuiMachineEM{
	public GuiGeneratorLiving(InventoryPlayer inventoryPlayer, TileGeneratorLiving tileEntity){
		super(new ContainerGeneratorLiving(inventoryPlayer, tileEntity), tileEntity);
	}
}

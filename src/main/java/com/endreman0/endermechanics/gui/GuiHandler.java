package com.endreman0.endermechanics.gui;

import com.endreman0.endermechanics.container.*;
import com.endreman0.endermechanics.tile.TileInventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id){
			case(0): return new GuiGeneratorFurnace(player.inventory, (TileInventory)world.getTileEntity(x, y, z));
			case(1): return new GuiGeneratorLiving(player.inventory, (TileInventory)world.getTileEntity(x, y, z));
			//cases 2-4: saved for reactors in planning
			case(5): return new GuiFurnaceEM(player.inventory, (TileInventory)world.getTileEntity(x, y, z));
			case(6): return new GuiPulverizer(player.inventory, (TileInventory)world.getTileEntity(x, y, z));
			case(7): return new GuiCrystallizer(player.inventory, (TileInventory)world.getTileEntity(x, y, z));
			case(8): return new GuiEnrichmentCell(player.inventory, (TileInventory)world.getTileEntity(x, y, z));
			case(9): return new GuiPurifier(player.inventory, (TileInventory)world.getTileEntity(x, y, z));
			default: return null;
		}
	}
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id){
			case(0): return new ContainerGeneratorFurnace(player.inventory, (TileInventory)world.getTileEntity(x, y, z));
			case(1): return new ContainerGeneratorLiving(player.inventory, (TileInventory)world.getTileEntity(x, y, z));
			//cases 2-4: saved for reactors in planning
			case(5): return new ContainerFurnaceEM(player.inventory, (TileInventory)world.getTileEntity(x, y, z));
			case(6): return new ContainerPulverizer(player.inventory, (TileInventory)world.getTileEntity(x, y, z));
			case(7): return new ContainerCrystallizer(player.inventory, (TileInventory)world.getTileEntity(x, y, z));
			case(8): return new ContainerEnrichmentCell(player.inventory, (TileInventory)world.getTileEntity(x, y, z));
			case(9): return new ContainerPurifier(player.inventory, (TileInventory)world.getTileEntity(x, y, z));
			default: return null;
		}
	}	
}

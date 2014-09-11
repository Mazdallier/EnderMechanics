package com.endreman0.endermechanics.gui;

import com.endreman0.endermechanics.container.*;
import com.endreman0.endermechanics.tile.*;
import com.endreman0.endermechanics.util.LogHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id){
			case(0): 
				LogHelper.info("GUI created client-side with player inventory "+player.inventory+" and tile "+(TileMachineEM)world.getTileEntity(x, y, z));
			return new GuiGeneratorFurnace(player.inventory, (TileMachineEM)world.getTileEntity(x, y, z));
			case(1): return new GuiGeneratorLiving(player.inventory, (TileGeneratorLiving)world.getTileEntity(x, y, z));
			default: 
				LogHelper.info("Failed to create GUI client-side for ID " + id);
				return null;
		}
	}
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id){
			case(0): 
				LogHelper.info("Container created server-side with player inventory "+player.inventory+" and tile "+(TileMachineEM)world.getTileEntity(x, y, z));
			return new ContainerGeneratorFurnace(player.inventory, (TileMachineEM)world.getTileEntity(x, y, z));
			case(1): return new ContainerGeneratorLiving(player.inventory, (TileGeneratorLiving)world.getTileEntity(x, y, z));
			default: 
				LogHelper.info("Failed to create container server-side for ID " + id);
				return null;
		}
	}	
}

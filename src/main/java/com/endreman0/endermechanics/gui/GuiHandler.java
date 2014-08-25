package com.endreman0.endermechanics.gui;

import com.endreman0.endermechanics.container.*;
import com.endreman0.endermechanics.tile.*;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id){
			case(0): return new GuiGenerator(player.inventory, (TileEntityGenerator)world.getTileEntity(x, y, z));
			default: return null;
		}
	}
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id){
			case(0): return new ContainerGenerator(player.inventory, (TileEntityGenerator)world.getTileEntity(x, y, z));
			default: return null;
		}
	}	
}

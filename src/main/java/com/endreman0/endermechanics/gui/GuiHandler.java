package com.endreman0.endermechanics.gui;

import com.endreman0.endermechanics.container.*;
import com.endreman0.endermechanics.tile.TileMachineEM;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id){
			case(0): return new GuiGeneratorFurnace(player.inventory, (TileMachineEM)world.getTileEntity(x, y, z));
			case(1): return new GuiGeneratorLiving(player.inventory, (TileMachineEM)world.getTileEntity(x, y, z));
			default: return null;
		}
	}
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id){
			case(0): return new ContainerGeneratorFurnace(player.inventory, (TileMachineEM)world.getTileEntity(x, y, z));
			case(1): return new ContainerGeneratorLiving(player.inventory, (TileMachineEM)world.getTileEntity(x, y, z));
			default: return null;
		}
	}	
}

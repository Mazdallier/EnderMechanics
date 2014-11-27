package com.endreman0.endermechanics.gui;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.endreman0.endermechanics.container.ContainerDefaultGenerator;
import com.endreman0.endermechanics.container.ContainerDefaultMachine;
import com.endreman0.endermechanics.tile.generator.TileGenerator;
import com.endreman0.endermechanics.tile.generator.TileGeneratorFurnace;
import com.endreman0.endermechanics.tile.machine.TileMachine;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiFactory implements IModGuiFactory, IGuiHandler{
	@Override public void initialize(Minecraft minecraft){}
	@Override public Class<? extends GuiScreen> mainConfigGuiClass(){return ConfigGui.class;}
	@Override public Set<RuntimeOptionCategoryElement> runtimeGuiCategories(){return null;}
	@Override public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element){return null;}
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		switch(id){
			case(0): return new GuiGeneratorFurnace(player.inventory, (TileGeneratorFurnace)world.getTileEntity(x, y, z));
			case(1): case(2): case(3): case(4):
				return new GuiDefaultGenerator(player.inventory, (TileGenerator)world.getTileEntity(x, y, z));
			case(5): case(6): case(7): case(8): case(9): 
				return new GuiDefaultMachine(player.inventory, (TileMachine)world.getTileEntity(x, y, z));
			default: return null;
		}
	}
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id){
			case(0): case(1): case(2): case(3): case(4):
				return new ContainerDefaultGenerator(player.inventory, (TileGenerator)world.getTileEntity(x, y, z));
			case(5): case(6): case(7): case(8): case(9):
				return new ContainerDefaultMachine(player.inventory, (TileMachine)world.getTileEntity(x, y, z));
			default: return null;
		}
	}
}

package com.endreman0.endermechanics.gui;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
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
			default: return null;
		}
	}
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id){
			default: return null;
		}
	}
}

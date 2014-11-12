package com.endreman0.endermechanics.tile;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class TileGrinder extends TileMachineRecipe{
	public TileGrinder(){
		super(2, 10000);
	}
	public static void addRecipes(){
		addRecipe(new ItemStack(Blocks.glass), new ItemStack(Blocks.sand), 1000);
		addRecipe(new ItemStack(Blocks.stonebrick), new ItemStack(Blocks.stone), 1000);
		addRecipe(new ItemStack(Blocks.stone), new ItemStack(Blocks.cobblestone), 850);
		addRecipe(new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.gravel), 750);
		addRecipe(new ItemStack(Blocks.gravel), new ItemStack(Blocks.sand), 600);
	}
}

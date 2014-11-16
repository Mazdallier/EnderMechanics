package com.endreman0.endermechanics.tile;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.endreman0.endermechanics.item.ModItems;
import com.endreman0.endermechanics.util.Recipes;

public class TileGrinder extends TileMachineRecipe{
	public static final Recipes recipes = new Recipes();
	public TileGrinder(){
		super(2, 10000);
	}
	@Override protected Recipes recipes(){return recipes;}
	
	public static void addRecipes(){
//		recipes.add(new ItemStack(ModItems.oreClump, 1, COPY_META), new ItemStack(ModItems.oreDust, 1, COPY_META), 1000);
		recipes.add(new ItemStack(Blocks.iron_ore), new ItemStack(ModItems.oreDust, 2, 0), 1000);
		recipes.add(new ItemStack(Blocks.gold_ore), new ItemStack(ModItems.oreDust, 2, 1), 1000);

		recipes.add(new ItemStack(ModItems.oreClump, 1, 0), new ItemStack(ModItems.oreDust, 1, 0), 1000);
		recipes.add(new ItemStack(ModItems.oreClump, 1, 1), new ItemStack(ModItems.oreDust, 1, 1), 1000);
	}
}

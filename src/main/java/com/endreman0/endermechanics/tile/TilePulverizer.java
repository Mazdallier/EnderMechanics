package com.endreman0.endermechanics.tile;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.endreman0.endermechanics.item.ModItems;
import com.endreman0.endermechanics.util.Recipes;

public class TilePulverizer extends TileMachineRecipe{
	public static final Recipes recipes = new Recipes();
	public TilePulverizer(){super(2, 10000);}
	@Override protected Recipes recipes(){return recipes;}
	public static void addRecipes(){
		recipes.add(new ItemStack(Blocks.iron_ore), new ItemStack(ModItems.orePieces[0], 2, 0), 1000);
		recipes.add(new ItemStack(Blocks.gold_ore), new ItemStack(ModItems.orePieces[0], 2, 1), 1000);
		recipes.add(new ItemStack(ModItems.orePieces[1], 1, Recipes.COPY_META), new ItemStack(ModItems.orePieces[0], 1, Recipes.COPY_META), 1000);
		
		recipes.add(new ItemStack(Blocks.stonebrick), new ItemStack(Blocks.stone), 1000);
		recipes.add(new ItemStack(Blocks.stone), new ItemStack(Blocks.cobblestone), 1000);
		recipes.add(new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.gravel), 850);
		recipes.add(new ItemStack(Blocks.gravel), new ItemStack(Blocks.sand), 700);
		recipes.add(new ItemStack(Blocks.glass), new ItemStack(Blocks.sand), 800);
		recipes.add(new ItemStack(Blocks.log, 1, Recipes.COPY_META), new ItemStack(Blocks.planks, 6, Recipes.COPY_META), 1000);
		recipes.add(new ItemStack(Blocks.log2, 1, Recipes.COPY_META), new ItemStack(Blocks.planks, 6, Recipes.COPY_META+4), 1000);
	}
}

package com.endreman0.endermechanics.tile.machine;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.endreman0.endermechanics.item.ModItems;
import com.endreman0.endermechanics.util.Recipes;

public class TilePurifier extends TileMachineRecipe{
	private static final Recipes recipes = new Recipes();
	public TilePurifier(){super(2, 10000);}
	@Override public Recipes recipes(){return recipes;}
	public static void addRecipes(){
		recipes.add(new ItemStack(Blocks.iron_ore), new ItemStack(ModItems.orePieces[3], 5, 0), 1000);
		recipes.add(new ItemStack(Blocks.gold_ore), new ItemStack(ModItems.orePieces[3], 5, 1), 1000);
		recipes.add(new ItemStack(Blocks.diamond_ore), new ItemStack(ModItems.orePieces[3], 5, 2), 1000);
		recipes.add(new ItemStack(Blocks.emerald_ore), new ItemStack(ModItems.orePieces[3], 5, 3), 1000);
		recipes.add(new ItemStack(Blocks.coal_ore), new ItemStack(ModItems.orePieces[3], 5, 4), 1000);
		recipes.add(new ItemStack(Blocks.lapis_ore), new ItemStack(ModItems.orePieces[3], 6, 5), 1000);
		recipes.add(new ItemStack(Blocks.quartz_ore), new ItemStack(ModItems.orePieces[3], 5, 6), 1000);
		recipes.add(new ItemStack(Blocks.redstone_ore), new ItemStack(ModItems.orePieces[3], 6, 7), 1000);
	}
}

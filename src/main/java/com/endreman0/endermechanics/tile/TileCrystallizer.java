package com.endreman0.endermechanics.tile;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.endreman0.endermechanics.item.ModItems;
import com.endreman0.endermechanics.util.Recipes;

public class TileCrystallizer extends TileMachineRecipe{
	private static final Recipes recipes = new Recipes();
	public TileCrystallizer(){super(2, 10000);}
	@Override public Recipes recipes(){return recipes;}
	public static void addRecipes(){
		recipes.add(new ItemStack(Blocks.iron_ore), new ItemStack(ModItems.orePieces[1], 3, 0), 1000);
		recipes.add(new ItemStack(Blocks.gold_ore), new ItemStack(ModItems.orePieces[1], 3, 1), 1000);
		recipes.add(new ItemStack(ModItems.orePieces[2], 1, Recipes.COPY_META), new ItemStack(ModItems.orePieces[1], 1, Recipes.COPY_META), 1000);		
	}
}

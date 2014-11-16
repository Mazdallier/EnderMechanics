package com.endreman0.endermechanics.tile;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.endreman0.endermechanics.item.ModItems;
import com.endreman0.endermechanics.util.Recipes;

public class TilePulverizer extends TileMachineRecipe{
	public static Recipes recipes = new Recipes();
	public TilePulverizer(){
		super(2, 10000);
	}
	public static void addRecipes(){
		recipes.add(new ItemStack(Blocks.iron_ore), new ItemStack(ModItems.oreClump, 3, 0), 1000);
		recipes.add(new ItemStack(Blocks.gold_ore), new ItemStack(ModItems.oreClump, 3, 1), 1000);
	}
	@Override protected Recipes recipes(){return recipes;}
}

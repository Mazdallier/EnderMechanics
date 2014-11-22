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
		recipes.add(new ItemStack(Blocks.iron_ore), new ItemStack(ModItems.oreDust, 2, 0), 1000);
		recipes.add(new ItemStack(Blocks.gold_ore), new ItemStack(ModItems.oreDust, 2, 1), 1000);

		recipes.add(new ItemStack(ModItems.oreClump, 1, Recipes.COPY_META), new ItemStack(ModItems.oreDust, 1, Recipes.COPY_META), 1000);
		
		recipes.add(new ItemStack(Blocks.stonebrick), new ItemStack(Blocks.stone), 1000);
		recipes.add(new ItemStack(Blocks.stone), new ItemStack(Blocks.cobblestone), 1000);
		recipes.add(new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.gravel), 850);
		recipes.add(new ItemStack(Blocks.gravel), new ItemStack(Blocks.sand), 700);
		recipes.add(new ItemStack(Blocks.glass), new ItemStack(Blocks.sand), 800);
		recipes.add(new ItemStack(Blocks.log, 1, Recipes.COPY_META), new ItemStack(Blocks.planks, 6, Recipes.COPY_META), 1000);
		recipes.add(new ItemStack(Blocks.log2, 1, Recipes.COPY_META), new ItemStack(Blocks.planks, 6, Recipes.COPY_META+4), 1000);
		//The two meta values in log2 (Acacia @0, Dark Oak@1) become planks@5,6. Why not all 6 logs in one item? Beats me.
	}
}

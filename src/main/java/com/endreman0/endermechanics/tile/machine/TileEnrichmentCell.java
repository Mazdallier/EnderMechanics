package com.endreman0.endermechanics.tile.machine;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.endreman0.endermechanics.item.ModItems;
import com.endreman0.endermechanics.util.Recipes;

public class TileEnrichmentCell extends TileMachineRecipe{
	private static final Recipes recipes = new Recipes();
	public TileEnrichmentCell(){super(2, 10000);}
	@Override public Recipes recipes(){return recipes;}
	public static void addRecipes(){
		recipes.add(new ItemStack(Blocks.iron_ore), new ItemStack(ModItems.orePieces[2], 4, 0), 1000);
		recipes.add(new ItemStack(Blocks.gold_ore), new ItemStack(ModItems.orePieces[2], 4, 1), 1000);
		recipes.add(new ItemStack(Blocks.diamond_ore), new ItemStack(ModItems.orePieces[2], 4, 2), 1000);
		recipes.add(new ItemStack(Blocks.emerald_ore), new ItemStack(ModItems.orePieces[2], 4, 3), 1000);
		recipes.add(new ItemStack(Blocks.coal_ore), new ItemStack(ModItems.orePieces[2], 4, 4), 1000);
		recipes.add(new ItemStack(Blocks.lapis_ore), new ItemStack(ModItems.orePieces[2], 4, 5), 1000);
		recipes.add(new ItemStack(Blocks.quartz_ore), new ItemStack(ModItems.orePieces[2], 4, 6), 1000);
		recipes.add(new ItemStack(Blocks.redstone_ore), new ItemStack(ModItems.orePieces[2], 5, 7), 1000);
		recipes.add(new ItemStack(ModItems.orePieces[3], 1, Recipes.COPY_META), new ItemStack(ModItems.orePieces[2], 1, Recipes.COPY_META), 1000);		
	}

}

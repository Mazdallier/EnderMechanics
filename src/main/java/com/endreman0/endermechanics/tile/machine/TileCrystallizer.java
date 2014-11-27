package com.endreman0.endermechanics.tile.machine;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
		recipes.add(new ItemStack(Blocks.diamond_ore), new ItemStack(ModItems.orePieces[1], 3, 2), 1000);
		recipes.add(new ItemStack(Blocks.emerald_ore), new ItemStack(ModItems.orePieces[1], 3, 3), 1000);
		recipes.add(new ItemStack(Blocks.coal_ore), new ItemStack(ModItems.orePieces[1], 3, 4), 1000);
		recipes.add(new ItemStack(Blocks.lapis_ore), new ItemStack(ModItems.orePieces[1], 3, 5), 1000);
		recipes.add(new ItemStack(Blocks.quartz_ore), new ItemStack(ModItems.orePieces[1], 3, 6), 1000);
		recipes.add(new ItemStack(Blocks.redstone_ore), new ItemStack(ModItems.orePieces[1], 4, 7), 1000);
		recipes.add(new ItemStack(ModItems.orePieces[2], 1, Recipes.COPY_META), new ItemStack(ModItems.orePieces[1], 1, Recipes.COPY_META), 1000);		
		
		//For nonmetal resources, dusts are converted to items in this machine.
		recipes.add(new ItemStack(ModItems.orePieces[0], 1, 2), new ItemStack(Items.diamond), 1000);
		recipes.add(new ItemStack(ModItems.orePieces[0], 1, 3), new ItemStack(Items.emerald), 1000);
		recipes.add(new ItemStack(ModItems.orePieces[0], 1, 4), new ItemStack(Items.coal), 1000);
		recipes.add(new ItemStack(ModItems.orePieces[0], 1, 5), new ItemStack(Items.dye, 1, 4), 1000);//Lapis
		recipes.add(new ItemStack(ModItems.orePieces[0], 1, 6), new ItemStack(Items.quartz), 1000);
	}
}

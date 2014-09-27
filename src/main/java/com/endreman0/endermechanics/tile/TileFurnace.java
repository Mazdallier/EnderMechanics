package com.endreman0.endermechanics.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fluids.FluidStack;

import com.endreman0.endermechanics.util.RecipeEM;

public class TileFurnace extends TileMachine{
	public TileFurnace(){super(10000, 2);}
	
	@Override protected RecipeEM getOutput(boolean execute){return new RecipeEM(null, 1000, FurnaceRecipes.smelting().getSmeltingResult(inv[0]));}
	
	@Override protected void addOutputs(ItemStack[] items, FluidStack[] fluids){setInventorySlotContents(1, items[0]);}
}

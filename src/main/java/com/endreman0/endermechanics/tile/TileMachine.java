package com.endreman0.endermechanics.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

import com.endreman0.endermechanics.util.RecipeEM;

public abstract class TileMachine extends TileInventory{
	public TileMachine(int maxPower, int invSlots){
		super(maxPower, invSlots);
	}
	/**
	 * Get the outputted items from the inventory of a machine.
	 * @param execute Whether or not to consume the inputs and produce the outputs. This includes items, fluids and power.
	 * @return A RecipeEM containing the outputs of the recipe, and the power to be *consumed* (Positive means loss of power).
	 */
	protected abstract RecipeEM getOutput(boolean execute);
	/**
	 * Insert the designated items and fluids into the output slots and tanks.
	 * I don't have to know what your machine's output put slots are, as long as you can handle inserting into them.
	 * If your machine is not a fluid handler, the fluids should be null and you can ignore them.
	 * The length of the parameters is determined by the recipes registered to your machine. If you receive an array that is too long, check your recipes.
	 * @param items the ItemStack[] to put in the output
	 * @param fluids the FluidStack[] to put in the output tanks
	 */
	protected abstract void addOutputs(ItemStack[] items, FluidStack[] fluids);
	/**
	 * Whether or not the tile is capable of running this tick.
	 * Multi-blocks would check here if they have a correctly formed structure.
	 * @return true if everything is good to go.
	 */
	protected boolean canRun(){return true;}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(!canRun()) return;//If the machine can't run,
		RecipeEM recipe = getOutput(false);
		if(recipe==null) return;//or there is no recipe available,
		if(recipe.power()>=power) return;//or there is not enough power, stop.
		
		getOutput(true);//Consume items and power
		addOutputs(recipe.itemOutputs(), recipe.fluidOutputs());
	}
}

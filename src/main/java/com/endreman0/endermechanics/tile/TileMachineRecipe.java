package com.endreman0.endermechanics.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.IFluidHandler;

import com.endreman0.endermechanics.util.RecipeEM;

public abstract class TileMachineRecipe extends TileMachine{
	protected List<RecipeEM> recipes;
	protected int recipeID;
	public TileMachineRecipe(int maxPower, int invSlots) {
		super(maxPower, invSlots);
		recipes = new ArrayList<RecipeEM>();
		recipeID=-1;
	}
	public void addRecipe(RecipeEM recipe){
		if(recipe==null) return;
		if(recipes.contains(recipe)) return;
		//Ignore recipes involving fluids if does not have a tank.
		if(!(this instanceof IFluidHandler) && (recipe.fluidInputs().length>0 || recipe.fluidOutputs().length>0)) return;
		recipes.add(recipe);
	}
	@Override
	public RecipeEM getOutput(boolean execute){
		if(recipeID>=0){
			return recipes.get(recipeID);
		}
		return null;
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		recipeID = nbt.getInteger("recipeID");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setInteger("recipeID", recipeID);
	}
}

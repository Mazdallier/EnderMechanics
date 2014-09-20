package com.endreman0.endermechanics.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

import com.endreman0.endermechanics.recipe.RecipeEM;

public abstract class TileMachineEM extends TileFunctionalEM{
	protected List<RecipeEM> recipes;
	protected int recipeID;
	public TileMachineEM(int maxPower){
		super(maxPower);
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
	public void updateEntity(){
		super.updateEntity();
		if(recipeID<0){
			for(int i=0;i<recipes.size();i++){
				if(recipes.get(0).canExecute(this)){
					recipeID=i;
					break;
				}
			}
		}
		if(recipeID==-1){return;}
		power-=recipes.get(recipeID).power();
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

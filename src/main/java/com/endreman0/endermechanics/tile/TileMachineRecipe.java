package com.endreman0.endermechanics.tile;

import com.endreman0.endermechanics.util.Recipes;
import com.endreman0.endermechanics.util.Recipes.Recipe;
import com.endreman0.endermechanics.util.Utility;

public abstract class TileMachineRecipe extends TileMachine{
	public TileMachineRecipe(int invSlots, int maxPower){
		super(invSlots, maxPower);
	}
	protected abstract Recipes recipes();
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(inv[0]==null) return;
		Recipe recipe = recipes().getRecipe(inv[0]);
		if(recipe!=null && inv[0].stackSize>=recipe.input.stackSize && power>=recipe.power
				&& Utility.merge(recipe.output, inv[1])!=null){
			//If there is a recipe, there is enough input and power to use it, and the output
			//can fit in the same ItemStack as what is already in the output, run.
			power-=recipe.power;
			inv[1] = Utility.merge(recipe.output, inv[1]);
			inv[0].stackSize-=recipe.input.stackSize;
			if(inv[0].stackSize<=0) inv[0]=null;
		}
	}
}

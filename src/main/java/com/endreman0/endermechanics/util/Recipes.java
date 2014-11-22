package com.endreman0.endermechanics.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Recipes{
	/**
	 * A constant that allows some special behaviors in machines.<br/>
	 * First, it acts as a wildcard. Passing an input ItemStack with this metadata will allow any metadata to be used. This is also
	 * required to enable the second and third behaviors.<br/>
	 * Second, as the name suggests, passing COPY_META as the input and output metadata will make the machine copy the metadata of the
	 * input onto the output.
	 * Third, using COPY_META +/- 16 on the output will make the machine copythe input meta and add (meta - COPY_META) before passing
	 * it to the output.
	 */
	public static final int COPY_META = Integer.MAX_VALUE-16;
	private List<Recipe> recipes = new ArrayList<Recipe>();
	public void add(ItemStack input, ItemStack output, int power){
		if(input==null || output==null || power==0) return;
		if(contains(input)){
			Log.info(String.format("Overriding recipe with input %s and output %s in favor of output %s", input, getRecipe(input).output, output));
			remove(input);
		}
		recipes.add(new Recipe(input, output, power));
	}
	public boolean contains(ItemStack input){return getRecipe(input)!=null;}
	public Recipe getRecipe(ItemStack input){
		for(Recipe recipe : recipes)
			if(Utility.canConsume(recipe.input, input)){
				int metaDiff = recipe.output.getItemDamage() - COPY_META;//If this is +/-16, relative metadata will be applied.
				Log.info("Metadata difference: " + metaDiff);
				if(Math.abs(metaDiff)<=16){
					ItemStack output = recipe.output.copy();
					output.setItemDamage(input.getItemDamage() + metaDiff);
					Log.info("Output metadata: " + output.getItemDamage());
					return new Recipe(recipe.input.copy(), output, recipe.power);
				}else{
					return new Recipe(recipe.input.copy(), recipe.output.copy(), recipe.power);
				}
				/*if(Math.abs(recipe.output.getItemDamage()-COPY_META)<=16){
					ItemStack out = recipe.output.copy();
					LogHelper.info("Metadata value " + out.getItemDamage() + " within range of " + COPY_META + "(difference " + (recipe.output.getItemDamage() - COPY_META) + "). Applying relative metadata rules.");
					out.setItemDamage(input.getItemDamage() + (recipe.output.getItemDamage() - COPY_META));
					LogHelper.info("Setting output metadata to " + out.getItemDamage() + " from input metadata of " + input.getItemDamage());
					return new Recipe(recipe.input, out, recipe.power);
				}else{
					return recipe;
				}*/
			}
		return null;
	}
	public void remove(ItemStack input){
		for(Iterator<Recipe> iterator = recipes.iterator(); iterator.hasNext();){
			if(iterator.next().input.isItemEqual(input)) iterator.remove();
		}
	}
	public class Recipe{
		public final ItemStack input, output;
		public final int power;
		public Recipe(ItemStack input, ItemStack output, int power){
			this.input = input;
			this.output = output;
			this.power = power;
		}
	}
}

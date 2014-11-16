package com.endreman0.endermechanics.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class Recipes{
	/**
	 * A constant that allows some special behaviors in machines.<br/>
	 * First, it acts as a wildcard. Passing an input ItemStack with this metadata will allow any
	 * metadata to be used. This is also required to enable the second and third behaviors.<br/>
	 * Second, as the name suggests, passing COPY_META as the input and output metadata will make
	 * the machine copy the metadata of the input onto the output.
	 * Third, using COPY_META +/- 16 on the output will make the machine copythe input meta and
	 * add (meta - COPY_META) before passing it to the output.
	 */
	public static final int COPY_META = Integer.MAX_VALUE-16;
	private List<Recipe> recipes = new ArrayList<Recipe>();
	public void add(ItemStack input, ItemStack output, int power){
		if(input==null || output==null || power==0) return;
		if(contains(input)) return;
		recipes.add(new Recipe(input, output, power));
	}
	public boolean contains(ItemStack input){return getOutput(input)!=null;}
	public ItemStack getOutput(ItemStack input){
		for(Recipe recipe : recipes)
			if(Utility.canConsume(recipe.input, input)) return recipe.output;
		return null;
	}
	public int getPower(ItemStack input){
		for(Recipe recipe : recipes)
			if(Utility.canConsume(recipe.input, input)) return recipe.power;
		return 0;
	}
	public Recipe getRecipe(ItemStack input){
		for(Recipe recipe : recipes) if(Utility.canConsume(recipe.input, input)) return recipe;
		return null;
	}
	public class Recipe{
		public final ItemStack input, output;
		public final int power;
		public Recipe(ItemStack input, ItemStack output, int power){
			this.input = input.copy();
			this.output = output.copy();
			this.power = power;
		}
	}
}

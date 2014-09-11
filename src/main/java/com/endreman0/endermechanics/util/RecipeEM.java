package com.endreman0.endermechanics.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RecipeEM{
	private ItemStack[] inputs, outputs;
	private FluidStack[] fluidInputs, fluidOutputs;
	private int power;
	public RecipeEM(ItemStack[] inputs, FluidStack[] fluidInputs, int power, ItemStack[] outputs, FluidStack[] fluidOutputs){
		this.inputs = (inputs==null ? new ItemStack[0] : inputs); //Prevents NPEs by making sure each input is initialized, even if null is passed in.
		this.fluidInputs = (fluidInputs==null ? new FluidStack[0] : fluidInputs);
		this.power = power;
		this.outputs = (outputs==null ? new ItemStack[0] : outputs);
		this.fluidOutputs = (fluidOutputs==null ? new FluidStack[0] : fluidOutputs);
	}
	public ItemStack[] itemInputs(){return inputs;}
	public FluidStack[] fluidInputs(){return fluidInputs;}
	public int power(){return power;}
	public ItemStack[] itemOutputs(){return outputs;}
	public FluidStack[] fluidOutputs(){return fluidOutputs;}
}

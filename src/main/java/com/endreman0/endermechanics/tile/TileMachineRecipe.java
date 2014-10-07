package com.endreman0.endermechanics.tile;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;

import com.endreman0.endermechanics.util.LogHelper;
import com.endreman0.endermechanics.util.Utility;

public abstract class TileMachineRecipe extends TileMachine{
	protected static Map<ItemStack, ItemStack> items = new HashMap<ItemStack, ItemStack>();
	protected static Map<ItemStack, Integer> powerUse = new HashMap<ItemStack, Integer>();//Apparently primitives are not allowed in <> declarations.
	public TileMachineRecipe(int maxPower, int invSlots) {
		super(maxPower, invSlots);
	}
	public boolean addRecipe(ItemStack input, ItemStack output, int power){
		if(input==null || input.getItem()==null || input.stackSize<=0 || output==null || output.getItem()==null || output.stackSize<=0) return false;
		if(items.put(input, output)!=null || powerUse.put(input, new Integer(power))!=null){
			LogHelper.warn(String.format("Attempting to add recipe for %s using %s and %s in machine %s; recipe already had values assigned to it", output, input, Utility.powerString(power), Utility.className(this)));
		}
		return true;
	}
	@Override
	public void updateEntity(){
		super.updateEntity();
		
	}
}

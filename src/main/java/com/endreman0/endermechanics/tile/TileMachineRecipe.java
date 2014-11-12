package com.endreman0.endermechanics.tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.item.ItemStack;

public abstract class TileMachineRecipe extends TileMachine{
//	protected static Map<ItemStack, ItemStack> items = new HashMap<ItemStack, ItemStack>();
//	protected static Map<ItemStack, Integer> powerUse = new HashMap<ItemStack, Integer>();//Apparently primitives are not allowed in <> declarations.
	protected static final List<ItemStack> inputs = new ArrayList<ItemStack>();
	protected static final List<ItemStack> outputs = new ArrayList<ItemStack>();
	protected static final List<Integer> powerUse = new ArrayList<Integer>();
	protected static int recipeID = -1;
	public TileMachineRecipe(int maxPower, int invSlots){
		super(maxPower, invSlots);
	}
	public static boolean addRecipe(ItemStack input, ItemStack output, int recipePower){
		if(input==null || input.getItem()==null || input.stackSize<=0 || output==null || output.getItem()==null || output.stackSize<=0) return false;
//		items.put(input, output);
//		powerUse.put(input, recipePower);
		inputs.add(input);
		outputs.add(output);
		powerUse.add(recipePower);
		return true;
	}
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(inv[0]==null) return;
		if(recipeID>-1){//Already has recipe
			ItemStack input = inputs.get(recipeID);
			ItemStack output = outputs.get(recipeID);
			int powerUsed = powerUse.get(recipeID);
			if(inv[0].isItemEqual(input) && (inv[1]==null || (inv[1].isItemEqual(output) && inv[1].stackSize + output.stackSize<=64)) && power>=powerUsed){
				//If the input matches, the output is empty or matches and has space, and there is enough power,
				if(inv[0].stackSize>input.stackSize) inv[0].stackSize-=input.stackSize; else inv[0]=null;
				if(inv[1]==null) inv[1] = outputs.get(recipeID).copy(); else inv[1].stackSize+=outputs.get(recipeID).stackSize;
				power-=powerUsed;				
			}else{
				recipeID = -1;
			}
		}else{
			for(int i=0;i<inputs.size();i++){
				if(inv[0].isItemEqual(inputs.get(i))){
					recipeID = i;
					return;
				}
			}
		}
	}
}

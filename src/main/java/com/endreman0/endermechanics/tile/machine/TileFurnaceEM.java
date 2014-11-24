package com.endreman0.endermechanics.tile.machine;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class TileFurnaceEM extends TileMachine{
	public TileFurnaceEM(){super(2, 10000);}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(inv[0]==null) return;//If there is an input,
		if(power<1000) return;//there is enough power,
		ItemStack output = FurnaceRecipes.smelting().getSmeltingResult(inv[0]);
		if(output==null) return;//the item is smeltable,
		if(inv[1]!=null){//and there is room for the item in the output slot, 
			if(inv[1].stackSize + output.stackSize>64) return;
			if(!inv[1].getItem().equals(output.getItem())) return;
		}
		if(inv[1]==null){//Smelt.
			inv[1] = output.copy();
		}else{
			inv[1].stackSize+=output.stackSize;
		}
		if(inv[0].stackSize>1){inv[0].stackSize--;}else{inv[0]=null;}
		power-=1000;
		markDirty();
	}
}

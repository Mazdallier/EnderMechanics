package com.endreman0.endermechanics.tile;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TileGeneratorLiving extends TileGeneratorEM{
	@Override protected int fuelTicks(){
		int[] oreIDs = OreDictionary.getOreIDs(inv[0]);
		if(oreIDs==null || oreIDs.length<1){
			if(inv[0].getItem().equals(Items.coal) && inv[0].getItemDamage()==1) return 800;//Charcoal is coal with meta 1
		}else{
			String ore = OreDictionary.getOreName(oreIDs[0]);
			if(ore.equals("treeSapling")){
				return 100;
			}else if(ore.equals("logWood")){
				return 300;
			}
		}
		return 0;
	}
	@Override protected int powerOutput(){return 10;}
}

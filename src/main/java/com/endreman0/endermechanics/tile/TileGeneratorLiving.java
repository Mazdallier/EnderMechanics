package com.endreman0.endermechanics.tile;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TileGeneratorLiving extends TileGenerator{
	public TileGeneratorLiving(){super(25, 10000);}
	@Override
	protected int consumeFuel(boolean execute){
		if(inv[0]==null) return 0;
		int power = 0;
		int[] oreIDs = OreDictionary.getOreIDs(inv[0]);
		if(oreIDs==null || oreIDs.length<1){
			if(inv[0].getItem().equals(Items.coal) && inv[0].getItemDamage()==1) power=800;
		}else{
			String ore = OreDictionary.getOreName(oreIDs[0]);
			if(ore.equals("treeSapling")){
				power=450;
			}else if(ore.equals("logWood")){
				power=600;
			}
		}
		if(execute){
			inv[0].stackSize--;
			if(inv[0].stackSize==0) inv[0] = null;
		}
		return power;
	}
}

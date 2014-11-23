package com.endreman0.endermechanics.tile;

import net.minecraft.init.Items;

public class TileGeneratorNetherStar extends TileGenerator{
	public TileGeneratorNetherStar(){super(500, 1000000);}
	@Override
	protected int consumeFuel(boolean execute){
		if(inv[0]==null) return 0;
		if(!inv[0].getItem().equals(Items.nether_star)) return 0;
		
		if(execute) inv[0].stackSize--;
		if(inv[0].stackSize<=0) inv[0]=null;
		return 2000;
	}
}

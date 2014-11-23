package com.endreman0.endermechanics.tile;

import com.endreman0.endermechanics.util.Log;

public class TileGeneratorTool extends TileGenerator{
	public TileGeneratorTool(){super(50, 20000);}
	@Override
	protected int consumeFuel(boolean execute){
		if(inv[0]==null) return 0;
		if(inv[0].getItem().isItemTool(inv[0])){
			if(execute){
				inv[0].setItemDamage(inv[0].getItemDamage()+1);
				if(inv[0].getItemDamage()==inv[0].getMaxDamage()) inv[0]=null;
				//Set power output based on mining level
			}
			return 1;
		}
		return 0;
	}
}

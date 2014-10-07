package com.endreman0.endermechanics.tile;

import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileMachine extends TileInventory{
	public TileMachine(int invSlots, int maxPower){
		super(invSlots, maxPower);
	}
	@Override public boolean canExtract(ForgeDirection from){return from.equals(ForgeDirection.UNKNOWN);}
}

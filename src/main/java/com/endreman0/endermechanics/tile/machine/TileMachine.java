package com.endreman0.endermechanics.tile.machine;

import net.minecraftforge.common.util.ForgeDirection;

import com.endreman0.endermechanics.block.BlockMachine;
import com.endreman0.endermechanics.tile.TileInventory;

public abstract class TileMachine extends TileInventory{
	public TileMachine(int invSlots, int maxPower){
		super(invSlots, maxPower);
	}
	@Override public String getInventoryName(){
		String base = super.getInventoryName();
		return base.substring(0, base.indexOf(':') + 1) + BlockMachine.names[blockMetadata>=0 ? blockMetadata : 0];}//Use block name as GUI name
	@Override public boolean canExtract(ForgeDirection from){return from.equals(ForgeDirection.UNKNOWN);}
}

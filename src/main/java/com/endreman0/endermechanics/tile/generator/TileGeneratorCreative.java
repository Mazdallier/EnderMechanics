package com.endreman0.endermechanics.tile.generator;

import net.minecraftforge.common.util.ForgeDirection;

public class TileGeneratorCreative extends TileGenerator{
	public TileGeneratorCreative(){super(10000, 10000);}
	@Override protected int consumeFuel(boolean execute){return 1;}
	@Override public int extract(ForgeDirection from, int amount, boolean actual){return amount;}
}

package com.endreman0.endermechanics.api;

import net.minecraft.world.World;

public interface IWrenchBreakable{
	public void breakWithWrench(World world, int x, int y, int z);
	public int getTier(int meta);
}

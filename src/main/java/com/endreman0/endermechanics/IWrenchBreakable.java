package com.endreman0.endermechanics;

import net.minecraft.world.World;

public interface IWrenchBreakable {
	public void breakWithWrench(World world, int x, int y, int z);
}

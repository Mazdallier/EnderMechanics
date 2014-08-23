package com.endreman0.endermechanics.multipart;

import com.endreman0.endermechanics.Utility;
import com.endreman0.endermechanics.block.ModBlocks;

import net.minecraft.block.Block;
import codechicken.lib.vec.Cuboid6;
import codechicken.multipart.minecraft.McMetaPart;

public class PartMachineFrame extends McMetaPart{
	public PartMachineFrame() {
		super();
	}
	public PartMachineFrame(int meta){
		super(meta);
	}
	@Override
	public Cuboid6 getBounds() {
		return new Cuboid6(0.4, 0.4, 0.4, 0.6, 0.6, 0.6);
	}

	@Override
	public Block getBlock() {
		return ModBlocks.blockMachineFrame;
	}

	@Override
	public String getType() {
		return "endermech:blockMachineFrame";
	}

}

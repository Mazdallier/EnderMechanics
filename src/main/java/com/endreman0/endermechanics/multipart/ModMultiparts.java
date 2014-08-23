package com.endreman0.endermechanics.multipart;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import com.endreman0.endermechanics.Utility;
import com.endreman0.endermechanics.block.ModBlocks;

import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartConverter;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.TMultiPart;

public class ModMultiparts implements IPartFactory, IPartConverter{
	@Override
	public TMultiPart createPart(String name, boolean client){
		if(name.equals("endermech:blockMachineFrame")) return new PartMachineFrame();
		return null;
	}
	public void init(){
		MultiPartRegistry.registerConverter(this);
		MultiPartRegistry.registerParts(this, new String[]{
				"endermech:blockMachineFrame",
				"endermech:blockBasic"
			});
	}
	@Override
	public Iterable<Block> blockTypes(){
		//return Arrays.asList((Block)ModBlocks.blockMachineFrame);
		return Arrays.asList((Block)ModBlocks.blockMachineFrame);
	}
	@Override
	public TMultiPart convert(World world, BlockCoord pos)
	{
		Block block = world.getBlock(pos.x, pos.y, pos.z);
		//int meta = world.getBlockMetadata(pos.x, pos.y, pos.z);
		if(block.equals(ModBlocks.blockMachineFrame)) return new PartMachineFrame();
		return null;
	}
}

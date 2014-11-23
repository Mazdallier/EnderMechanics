package com.endreman0.endermechanics.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.endreman0.endermechanics.tile.TileGeneratorCreative;

public class BlockGeneratorCreative extends BlockMachine{
	protected BlockGeneratorCreative(){super("generatorCreative", Integer.MAX_VALUE);}
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TileGeneratorCreative();}
}

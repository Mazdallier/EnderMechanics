package com.endreman0.endermechanics.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.endreman0.endermechanics.tile.TilePurifier;

public class BlockPurifier extends BlockBasicMachine{
	public BlockPurifier(){super("purifier", 3, 9);}
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TilePurifier();}
}

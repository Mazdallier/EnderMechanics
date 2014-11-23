package com.endreman0.endermechanics.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.endreman0.endermechanics.EnderMechanics;
import com.endreman0.endermechanics.tile.TileGeneratorLiving;

public class BlockGeneratorLiving extends BlockBasicMachine{
	protected BlockGeneratorLiving(){super("generatorLiving", 1, 1);}
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TileGeneratorLiving();}
}

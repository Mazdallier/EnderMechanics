package com.endreman0.endermechanics.block;

import com.endreman0.endermechanics.EnderMechanics;
import com.endreman0.endermechanics.tile.TilePulverizer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPulverizer extends BlockBasicMachine{
	protected BlockPulverizer(){super("pulverizer", 1, 6);}
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TilePulverizer();}
}

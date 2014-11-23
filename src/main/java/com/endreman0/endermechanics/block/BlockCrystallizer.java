package com.endreman0.endermechanics.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.endreman0.endermechanics.EnderMechanics;
import com.endreman0.endermechanics.tile.TileCrystallizer;

public class BlockCrystallizer extends BlockBasicMachine{
	protected BlockCrystallizer(){super("crystallizer", 2, 7);}
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TileCrystallizer();}
}

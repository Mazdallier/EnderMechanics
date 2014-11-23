package com.endreman0.endermechanics.block;

import com.endreman0.endermechanics.EnderMechanics;
import com.endreman0.endermechanics.tile.TileFurnaceEM;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFurnaceEM extends BlockBasicMachine{
	public BlockFurnaceEM(){super("furnaceEM", 0, 5);}
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TileFurnaceEM();}
}

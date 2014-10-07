package com.endreman0.endermechanics.block;

import com.endreman0.endermechanics.EnderMechanics;
import com.endreman0.endermechanics.tile.TileFurnaceEM;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFurnaceEM extends BlockMachine{
	public BlockFurnaceEM(){super("furnaceEM");}
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TileFurnaceEM();}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float partialX, float partialY, float partialZ){
		if(player.isSneaking()) return false;
		player.openGui(EnderMechanics.instance, 5, world, x, y, z);
		return true;
	}
}

package com.endreman0.endermechanics.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.endreman0.endermechanics.EnderMechanics;
import com.endreman0.endermechanics.tile.TilePulverizer;

public class BlockPulverizer extends BlockMachine{
	protected BlockPulverizer(){super("pulverizer");}
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TilePulverizer();}
	@Override public int getTier(int meta){return 2;}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float partialX, float partialY, float partialZ){
		if(player.isSneaking()) return false;
		player.openGui(EnderMechanics.instance, 7, world, x, y, z);
		return true;
	}
}

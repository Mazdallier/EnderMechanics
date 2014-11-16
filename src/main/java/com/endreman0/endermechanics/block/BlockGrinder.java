package com.endreman0.endermechanics.block;

import com.endreman0.endermechanics.EnderMechanics;
import com.endreman0.endermechanics.tile.TileGrinder;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGrinder extends BlockMachine{
	protected BlockGrinder(){super("grinder");}
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TileGrinder();}
	@Override public int getTier(int meta){return 1;}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float partialX, float partialY, float partialZ){
		if(player.isSneaking()) return false;
		player.openGui(EnderMechanics.instance, 6, world, x, y, z);
		return true;
	}
}

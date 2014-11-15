package com.endreman0.endermechanics.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.endreman0.endermechanics.EnderMechanics;
import com.endreman0.endermechanics.tile.TileGeneratorLiving;

public class BlockGeneratorLiving extends BlockMachine{
	protected BlockGeneratorLiving(){super("generatorLiving");}
	
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TileGeneratorLiving();}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float partialX, float partialY, float partialZ){
		if(player.isSneaking()){
			return false;
		}else{
			player.openGui(EnderMechanics.instance, 1, world, x, y, z);
			return true;
		}
	}
	@Override public int getTier(int meta){return 1;}
}

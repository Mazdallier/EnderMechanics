package com.endreman0.endermechanics.block;

import com.endreman0.endermechanics.EnderMechanics;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockBasicMachine extends BlockMachine{
	protected int guiIndex;
	public BlockBasicMachine(String name, int tier, int guiIndex){
		super(name, tier);
		this.guiIndex = guiIndex;
	}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float partialX, float partialY, float partialZ){
		if(player.isSneaking()) return false;
		player.openGui(EnderMechanics.instance, guiIndex, world, x, y, z);
		return true;
	}
}

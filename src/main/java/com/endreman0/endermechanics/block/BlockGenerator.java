package com.endreman0.endermechanics.block;

import com.endreman0.endermechanics.EnderMechanics;
import com.endreman0.endermechanics.tile.TileEntityGenerator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGenerator extends BlockContainerEM{
	public BlockGenerator() {
		super(Material.iron);
		setBlockName("generator");
		setBlockTextureName("generator");
	}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float par8, float par9, float par10){
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile == null || player.isSneaking()){
			return false;
		}
		player.openGui(EnderMechanics.instance, 0, world, x, y, z);
		return true;
	}
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta){
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}
	private void dropItems(World world, int x, int y, int z){
		TileEntity tile = world.getTileEntity(x, y, z);
		if(!(tile instanceof IInventory)){
			return;
		}
		IInventory inventory = (IInventory)tile;
		for(int i=0;i<inventory.getSizeInventory();i++){
			ItemStack stack =  inventory.getStackInSlot(i);
			if(stack!=null && stack.stackSize>0){
				EntityItem entity = new EntityItem(world, x, y, z, stack.copy());
				entity.motionY=0.2F;
				world.spawnEntityInWorld(entity);
				stack.stackSize=0;
			}
		}
	}
	@Override
	public TileEntity createNewTileEntity(World world, int meta){
		return new TileEntityGenerator();
	}
}

package com.endreman0.endermechanics.block;

import com.endreman0.endermechanics.util.IWrenchBreakable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockMachineEM extends BlockEM implements ITileEntityProvider, IWrenchBreakable{
	protected BlockMachineEM(Material material, String name){
		super(material, name);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta){
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}
	protected void dropItems(World world, int x, int y, int z){
		IInventory inventory = (IInventory)world.getTileEntity(x, y, z);
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
	public void breakWithWrench(World world, int x, int y, int z) {
		dropBlockAsItem(world, x, y, z, new ItemStack(this.getItem(world, x, y, z)));
		dropItems(world, x, y, z);
		world.setBlockToAir(x, y, z);
	}
	@Override public boolean hasTileEntity(int meta){return true;}
}

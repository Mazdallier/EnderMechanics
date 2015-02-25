package com.endreman0.endermechanics.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.endreman0.endermechanics.api.IWrenchBreakable;
import com.endreman0.endermechanics.tile.TileSyncFrame;

public class BlockSyncFrame extends BlockEM implements ITileEntityProvider, IWrenchBreakable{
	protected BlockSyncFrame(){super(Material.iron, "syncFrame");}
	@Override
	public void breakWithWrench(World world, int x, int y, int z) {
		dropBlockAsItem(world, x, y, z, new ItemStack(this.getItem(world, x, y, z)));
		onBlockBroken(world, x, y, z);
		world.setBlockToAir(x, y, z);
	}
	private void onBlockBroken(World world, int x, int y, int z){
		TileSyncFrame tile = (TileSyncFrame)world.getTileEntity(x, y, z);
		
	}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float partialX, float partialY, float partialZ){
		boolean execute = ((TileSyncFrame)world.getTileEntity(x, y, z)).insertMachine(player.getCurrentEquippedItem());
		if(execute) player.getCurrentEquippedItem().stackSize--;
		if(player.getCurrentEquippedItem()!=null && player.getCurrentEquippedItem().stackSize<=0) player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
		return execute;
	}
	@Override public boolean isOpaqueCube(){return false;}
	@Override public boolean renderAsNormalBlock(){return false;}
	@Override public int getRenderType(){return -1;}
	@Override public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side){return false;}
	@Override public boolean hasTileEntity(int metadata){return true;}
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TileSyncFrame();}
	@Override public int getTier(int meta){return 1;}
}

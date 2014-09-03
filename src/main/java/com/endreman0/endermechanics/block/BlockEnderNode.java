package com.endreman0.endermechanics.block;

import java.util.Random;

import com.endreman0.endermechanics.LogHelper;
import com.endreman0.endermechanics.tile.TileEntityEnderNode;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEnderNode extends BlockEM implements ITileEntityProvider{
	public BlockEnderNode() {
		super(Material.iron);
		setBlockName("enderNode");
		setBlockTextureName("enderNode");
		blockHardness=10;
	}
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta){
		((TileEntityEnderNode)world.getTileEntity(x, y, z)).breakBlock();
		super.breakBlock(world, x, y, z, block, meta);
	}
	@Override public boolean isOpaqueCube(){return false;}
	@Override public boolean renderAsNormalBlock(){return false;}
	@Override public int getRenderType(){return -1;}
	@Override public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side){return false;}
	@Override public boolean hasTileEntity(int metadata){return true;}
	@Override public TileEntity createNewTileEntity(World world, int meta){
		return new TileEntityEnderNode();
	}
}

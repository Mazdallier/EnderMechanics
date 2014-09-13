package com.endreman0.endermechanics.block;

import com.endreman0.endermechanics.tile.TileEnderNode;
import com.endreman0.endermechanics.util.IWrenchBreakable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEnderNode extends BlockEM implements ITileEntityProvider, IWrenchBreakable{
	protected BlockEnderNode() {
		super(Material.iron, "enderNode");
		setBlockBounds(0.125F, 0.125F, 0.125F, 0.875F, 0.875F, 0.875F);
	}
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta){
		((TileEnderNode)world.getTileEntity(x, y, z)).breakBlock();
		super.breakBlock(world, x, y, z, block, meta);
	}
	@Override
	public void breakWithWrench(World world, int x, int y, int z) {
		dropBlockAsItem(world, x, y, z, new ItemStack(this.getItem(world, x, y, z)));
		world.setBlockToAir(x, y, z);
	}
	@Override public boolean isOpaqueCube(){return false;}
	@Override public boolean renderAsNormalBlock(){return false;}
	@Override public int getRenderType(){return -1;}
	@Override public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side){return false;}
	@Override public boolean hasTileEntity(int metadata){return true;}
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TileEnderNode();}
}

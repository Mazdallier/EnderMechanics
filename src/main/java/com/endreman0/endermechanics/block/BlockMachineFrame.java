package com.endreman0.endermechanics.block;

import com.endreman0.endermechanics.tile.TileMachineFrame;
import com.endreman0.endermechanics.util.IWrenchBreakable;
import com.endreman0.endermechanics.util.Utility;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMachineFrame extends BlockEM implements ITileEntityProvider, IWrenchBreakable{
	public BlockMachineFrame(){
		super(Material.iron, "machineFrame");
		setBlockBounds(0.125F, 0.125F, 0.125F, 0.875F, 0.875F, 0.875F);
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
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TileMachineFrame();}
}

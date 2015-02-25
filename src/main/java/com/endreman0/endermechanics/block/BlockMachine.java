package com.endreman0.endermechanics.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.endreman0.endermechanics.tile.machine.*;

public class BlockMachine extends MultiBlockEM{
	protected BlockMachine(){
		super(Material.iron, "machine");
		guiIndices = new int[]{5, 6, 7, 8, 9};
		tiers = new int[]{0, 1, 2, 3, 3};
		setBlockBounds(0.125F, 0.125F, 0.125F, 0.875F, 0.875F, 0.875F);
	}
	@Override public String[] names(){return new String[]{"furnaceEM", "pulverizer", "crystallizer", "enrichmentCell", "purifier"};}
	@Override
	public TileEntity createNewTileEntity(World world, int meta){
		switch(meta){
			case(0): return new TileFurnaceEM();
			case(1): return new TilePulverizer();
			case(2): return new TileCrystallizer();
			case(3): return new TileEnrichmentCell();
			case(4): return new TilePurifier();
			default: return null;
		}
	}
	@Override public boolean isOpaqueCube(){return false;}
	@Override public boolean renderAsNormalBlock(){return false;}
	@Override public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side){return false;}
}
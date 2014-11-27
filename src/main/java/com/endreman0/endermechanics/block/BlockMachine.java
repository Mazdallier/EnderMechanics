package com.endreman0.endermechanics.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.endreman0.endermechanics.tile.machine.*;

public class BlockMachine extends MultiBlockEM{
	protected BlockMachine(){
		super(Material.iron, "machine");
		guiIndices = new int[]{5, 6, 7, 8, 9};
		tiers = new int[]{0, 1, 2, 3, 3};
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
}
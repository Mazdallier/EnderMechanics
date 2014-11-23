package com.endreman0.endermechanics.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.endreman0.endermechanics.tile.TileEnrichmentCell;

public class BlockEnrichmentCell extends BlockBasicMachine{
	public BlockEnrichmentCell(){super("enrichmentCell", 3, 8);}
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TileEnrichmentCell();}
}

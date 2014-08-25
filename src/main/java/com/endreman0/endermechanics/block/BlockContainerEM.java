package com.endreman0.endermechanics.block;

import com.endreman0.endermechanics.Utility;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockContainerEM extends BlockContainer{
	public BlockContainerEM(Material material){
		super(material);
		setCreativeTab(Utility.EM_TAB);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta){
		return null;
	}
	public String getUnwrappedUnlocalizedName(String unlocalizedName){return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);}
	@Override @SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		blockIcon = iconRegister.registerIcon(Utility.RESOURCE_PREFIX+':'+getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
}

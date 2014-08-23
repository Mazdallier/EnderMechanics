package com.endreman0.endermechanics.block;

import com.endreman0.endermechanics.Utility;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockEM extends Block{
	public BlockEM(Material material) {
		super(material);
		setCreativeTab(Utility.EM_TAB);
	}
	@Override
	public String getUnlocalizedName(){
		return "tile." + Utility.MOD_ID.toLowerCase() + ':' + getUnwrappedUnlocalizedName(super.getUnlocalizedName());
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		blockIcon = iconRegister.registerIcon(getUnlocalizedName().substring(getUnlocalizedName().indexOf('.')+1));
	}
	protected String getUnwrappedUnlocalizedName(String unlocalizedName){
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}

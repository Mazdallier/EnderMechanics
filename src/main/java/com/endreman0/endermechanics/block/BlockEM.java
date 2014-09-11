package com.endreman0.endermechanics.block;

import com.endreman0.endermechanics.util.IWrenchBreakable;
import com.endreman0.endermechanics.util.Utility;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class BlockEM extends Block{
	public BlockEM(Material material, String name){
		super(material);
		setCreativeTab(Utility.EM_TAB);
		setBlockName(name);
		setBlockTextureName(name);
		blockHardness = 10;
	}
	@Override
	public String getUnlocalizedName(){
		return "tile." + Utility.RESOURCE_PREFIX + ':' + getBasicName();
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		blockIcon = iconRegister.registerIcon(Utility.MOD_ID.toLowerCase() + ':' + getBasicName());
	}
	public String getBasicName(){
		String name = super.getUnlocalizedName();
		return name.substring(name.indexOf('.')+1);
    }
}

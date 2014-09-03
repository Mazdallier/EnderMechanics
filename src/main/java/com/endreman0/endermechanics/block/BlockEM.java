package com.endreman0.endermechanics.block;

import com.endreman0.endermechanics.Utility;
import com.endreman0.endermechanics.interfaces.IWrenchBreakable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockEM extends Block implements IWrenchBreakable{
	public BlockEM(Material material) {
		super(material);
		setCreativeTab(Utility.EM_TAB);
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
	@Override
	public void breakWithWrench(World world, int x, int y, int z) {
		dropBlockAsItem(world, x, y, z, new ItemStack(this.getItem(world, x, y, z)));
		world.setBlockToAir(x, y, z);
	}
}

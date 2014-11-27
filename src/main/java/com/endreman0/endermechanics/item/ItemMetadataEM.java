package com.endreman0.endermechanics.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.endreman0.endermechanics.util.Utility;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ItemMetadataEM extends ItemEM{
	protected IIcon[] icons;
	ItemMetadataEM(String name, int variants){
		super(name);
		maxStackSize=64;
		icons = new IIcon[variants];
	}
	@Override public IIcon getIconFromDamage(int meta){if(meta<icons.length) return icons[meta]; else return icons[0];}
	@Override public String getUnlocalizedName(ItemStack stack){return super.getUnlocalizedName(stack) + stack.getItemDamage();}
	
	@Override @SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list){
		for(int i=0;i<icons.length;i++) list.add(new ItemStack(item, 1, i));
	}
	@Override
	public void registerIcons(IIconRegister register){
		String baseName = Utility.MOD_ID + ":" + getBasicName();
		for(int i=0;i<icons.length;i++) icons[i] = register.registerIcon(baseName + i);
	}
}

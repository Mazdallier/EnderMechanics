package com.endreman0.endermechanics.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;

import com.endreman0.endermechanics.util.Utility;

public class ItemProcessedOre extends ItemMetadataEM{
	private static String[] names = new String[]{"Iron", "Gold", "Diamond", "Redstone", "Quartz", "Coal", "Emerald", "Lapis"};
	ItemProcessedOre(int tier){
		super("ore" + tier, names.length);
	}
	@Override
	public void registerIcons(IIconRegister register){
		String baseName = Utility.MOD_ID + ":" + getBasicName();
		for(int i=0;i<names.length;i++)
			icons[i] = register.registerIcon(baseName.concat(names[i]));
	}
	@Override public String getUnlocalizedName(ItemStack stack){return super.getUnlocalizedName() + names[stack.getItemDamage()];}
}

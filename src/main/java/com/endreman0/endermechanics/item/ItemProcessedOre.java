package com.endreman0.endermechanics.item;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.endreman0.endermechanics.util.Utility;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemProcessedOre extends ItemMetadataEM{
	private static List<String> names = Arrays.asList(new String[]{"Iron", "Gold", "Diamond", "Emerald", "Coal", "Lapis", "Quartz", "Redstone"});
	public static void addOre(String name){if(!names.contains(name)) names.add(name);}
	
	private int tier;
	ItemProcessedOre(int tier){
		super("ore" + tier, tier>0 ? names.size() : names.size()-1); this.tier=tier;
		//Tier 0, the last metadata is Redstone in dust form. Redstone Dust is already a vanilla item - no need to add it again.
		//By cutting down the array size by 1, this prevents the last subtype (Redstone Dust) from being added.
	}
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list){
		if(tier>0){super.getSubItems(item, tab, list); return;}
		for(int i=0;i<icons.length;i++) if(!names.get(i).equals("Redstone")) list.add(new ItemStack(item, 1, i));
	}
	@Override
	public void registerIcons(IIconRegister register){
		String baseName = Utility.MOD_ID + ":" + getBasicName();
		if(tier>0){
			for(int i=0;i<icons.length;i++) icons[i] = register.registerIcon(baseName + names.get(i));
		}else{
			for(int i=0;i<icons.length;i++) if(!names.get(i).equals("Redstone")) icons[i] = register.registerIcon(baseName + names.get(i));
		}
	}
	@Override public String getUnlocalizedName(ItemStack stack){return getUnlocalizedName() + names.get(stack.getItemDamage());}
}

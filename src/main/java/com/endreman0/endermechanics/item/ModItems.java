package com.endreman0.endermechanics.item;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems{
	public static final ItemEM wrench = new ItemWrench();
	public static void initItems(){
		GameRegistry.registerItem(wrench, "wrench");
	}
	public static void initRecipes(){
		GameRegistry.addRecipe(new ItemStack(wrench), "i i", " p ", " i ", 'i', Items.iron_ingot, 'p', Items.ender_pearl);
	}
}

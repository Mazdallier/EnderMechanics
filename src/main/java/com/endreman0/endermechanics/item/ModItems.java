package com.endreman0.endermechanics.item;

import com.endreman0.endermechanics.block.ModBlocks;
import com.endreman0.endermechanics.util.Utility;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems{
	//Basic resources
	public static final ItemEM pearlEM = new ItemPearlEM();
	public static final ItemEM ingotEM = new ItemIngotEM();
	public static final ItemEM obsidianStick = new ItemObsidianStick();
	
	//Items
	public static final ItemEM wrench = new ItemWrench();
	
	//Processed ore items
	public static final ItemEM oreDust = new ItemOreDust();
	
	//Armor
	public static final ArmorMaterial armorMatVoid = EnumHelper.addArmorMaterial("voidsteel", -1, new int[]{4, 10, 7, 4}, 0);
	public static final ItemVoidArmor voidHelmet = new ItemVoidArmor(0);
	public static final ItemVoidArmor voidChestplate = new ItemVoidArmor(1);
	public static final ItemVoidArmor voidLeggings = new ItemVoidArmor(2);
	public static final ItemVoidArmor voidBoots = new ItemVoidArmor(3);
}

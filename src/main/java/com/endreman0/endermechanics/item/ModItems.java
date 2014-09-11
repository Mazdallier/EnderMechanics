package com.endreman0.endermechanics.item;

import com.endreman0.endermechanics.block.ModBlocks;
import com.endreman0.endermechanics.util.Utility;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems{
	//Basic resources
	public static final ItemEM redPearl = new ItemRedPearl();
	public static final ItemEM darkIngot = new ItemDarkIngot();
	public static final ItemEM obsidianStick = new ItemObsidianStick();
	
	//Items
	public static final ItemEM wrench = new ItemWrench();
	
	//Armor
	private static final ArmorMaterial armorMatVoid = EnumHelper.addArmorMaterial("voidsteel", -1, new int[]{4, 10, 7, 4}, 0);
	public static final ItemVoidArmor voidHelmet = new ItemVoidArmor(armorMatVoid, 0);
	public static final ItemVoidArmor voidChestplate = new ItemVoidArmor(armorMatVoid, 1);
	public static final ItemVoidArmor voidLeggings = new ItemVoidArmor(armorMatVoid, 2);
	public static final ItemVoidArmor voidBoots = new ItemVoidArmor(armorMatVoid, 3);
	
	//Void items
	public static final ItemEM voidPearl = new ItemVoidPearl();
	public static final ItemEM voidIngot = new ItemVoidIngot();
	
	public static void initItems(){
		GameRegistry.registerItem(redPearl, "redPearl");
		GameRegistry.registerItem(darkIngot, "darkIngot");
		GameRegistry.registerItem(obsidianStick, "obsidianStick");
		GameRegistry.registerItem(wrench, "wrench");
		GameRegistry.registerItem(voidPearl, "voidPearl");
		GameRegistry.registerItem(voidIngot, "voidIngot");
		GameRegistry.registerItem(voidHelmet, "voidHelmet");
		GameRegistry.registerItem(voidChestplate, "voidChestplate");
		GameRegistry.registerItem(voidLeggings, "voidLeggings");
		GameRegistry.registerItem(voidBoots, "voidBoots");
	}
	public static void initRecipes(){
		//Items
		GameRegistry.addRecipe(new ItemStack(redPearl), " r ", "rpr", " r ", 'r', Items.redstone, 'p', Items.ender_pearl);
		GameRegistry.addShapelessRecipe(new ItemStack(darkIngot, 4), Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.diamond, Items.redstone, Items.redstone, Items.redstone, Items.redstone);
		GameRegistry.addRecipe(new ItemStack(obsidianStick, 4), "o", "o", 'o', Blocks.obsidian);
		if(Utility.enableWrench){
			GameRegistry.addRecipe(new ItemStack(wrench), "i i", " p ", " i ", 'i', darkIngot, 'p', redPearl);
		}
		
		//Blocks
		if(Utility.enableGenFurnace){
			GameRegistry.addRecipe(new ItemStack(ModBlocks.generatorFurnace), "blb", "rpr", "cfc", 'b', Items.blaze_powder, 'l', Items.lava_bucket, 'r', Items.blaze_rod, 'p', redPearl, 'c', Items.coal, 'f', Blocks.furnace);
		}
		if(Utility.enableFrame){
			GameRegistry.addRecipe(new ItemStack(ModBlocks.machineFrame), "isi", "sps", "isi", 'i', darkIngot, 's', obsidianStick, 'p', redPearl);
		}
		if(Utility.enableNode){
			GameRegistry.addRecipe(new ItemStack(ModBlocks.enderNode), "sis", "ipi", "sis", 's', obsidianStick, 'i', darkIngot, 'p', redPearl);
		}
	}
}

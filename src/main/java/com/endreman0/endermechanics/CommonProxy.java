package com.endreman0.endermechanics;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.endreman0.endermechanics.block.ModBlocks;
import com.endreman0.endermechanics.item.ModItems;
import com.endreman0.endermechanics.tile.*;
import com.endreman0.endermechanics.util.Utility;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy{
	//Client stuff, must exist to be called on EnderMechanics.proxy
	public void itemRenders(){}
	public void entityRenders(){}
	public void tileRenders(){}
	
	//Common stuff
	public void items(){
		GameRegistry.registerItem(ModItems.redPearl, "redPearl");
		GameRegistry.registerItem(ModItems.darkIngot, "darkIngot");
		GameRegistry.registerItem(ModItems.obsidianStick, "obsidianStick");
		GameRegistry.registerItem(ModItems.wrench, "wrench");
		GameRegistry.registerItem(ModItems.voidPearl, "voidPearl");
		GameRegistry.registerItem(ModItems.voidIngot, "voidIngot");
		GameRegistry.registerItem(ModItems.voidHelmet, "voidHelmet");
		GameRegistry.registerItem(ModItems.voidChestplate, "voidChestplate");
		GameRegistry.registerItem(ModItems.voidLeggings, "voidLeggings");
		GameRegistry.registerItem(ModItems.voidBoots, "voidBoots");
	}
	public void blocks(){
		GameRegistry.registerBlock(ModBlocks.machineFrame, "machineFrame");
		GameRegistry.registerBlock(ModBlocks.generatorFurnace, "generatorFurnace");
		GameRegistry.registerBlock(ModBlocks.generatorLiving, "generatorLiving");
		GameRegistry.registerBlock(ModBlocks.enderNode, "enderNode");
	}
	public void crafting(){
		//Items
		GameRegistry.addRecipe(new ItemStack(ModItems.redPearl), " r ", "rpr", " r ", 'r', Items.redstone, 'p', Items.ender_pearl);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.darkIngot, 4), Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.diamond, Items.redstone, Items.redstone, Items.redstone, Items.redstone);
		GameRegistry.addRecipe(new ItemStack(ModItems.obsidianStick, 4), "o", "o", 'o', Blocks.obsidian);
		if(Utility.enableWrench)
			GameRegistry.addRecipe(new ItemStack(ModItems.wrench), "i i", " p ", " i ", 'i', ModItems.darkIngot, 'p', ModItems.redPearl);
		
		//Blocks
		if(Utility.enableGenFurnace)
			GameRegistry.addRecipe(new ItemStack(ModBlocks.generatorFurnace), " l ", "bpb", "cfc", 'l', Items.lava_bucket, 'b', Items.blaze_rod, 'p', ModItems.redPearl, 'c', Items.coal, 'f', Blocks.furnace);
		if(Utility.enableGenLiving)
			GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.generatorLiving, " c ", "sps", "wfw", 'c', Items.cooked_beef, 's', "treeSapling", 'p', ModItems.redPearl, 'w', "logWood", 'f', Blocks.furnace));
		if(Utility.enableFrame)
			GameRegistry.addRecipe(new ItemStack(ModBlocks.machineFrame), "isi", "sps", "isi", 'i', ModItems.darkIngot, 's', ModItems.obsidianStick, 'p', ModItems.redPearl);
		if(Utility.enableNode)
			GameRegistry.addRecipe(new ItemStack(ModBlocks.enderNode), "sis", "ipi", "sis", 's', ModItems.obsidianStick, 'i', ModItems.darkIngot, 'p', ModItems.redPearl);
	}
	public void smelting(){
		//GameRegistry.addSmelting(Item input, ItemStack output, float xp);
	}
	public void machineRecipes(){
		
	}
	public void tileEntities(){
		GameRegistry.registerTileEntity(TileMachineFrame.class, "machineFrame");
		GameRegistry.registerTileEntity(TileGeneratorFurnace.class, "generatorFurnace");
		GameRegistry.registerTileEntity(TileGeneratorLiving.class, "generatorLiving");
		GameRegistry.registerTileEntity(TileEnderNode.class, "enderNode");
	}
	public void entities(){
		//EntityRegistry.registerModEntity(Class EntityClass, String unlocalizedName, int modSpecificID, EnderMechanics.instance, int trackingRange, int updateFrequency, true);
	}
}

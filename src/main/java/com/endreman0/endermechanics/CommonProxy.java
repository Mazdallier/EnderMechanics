package com.endreman0.endermechanics;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.endreman0.endermechanics.block.BlockEM;
import com.endreman0.endermechanics.block.ModBlocks;
import com.endreman0.endermechanics.item.ItemEM;
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
		registerItem(ModItems.pearlEM);
		registerItem(ModItems.ingotEM);
		registerItem(ModItems.obsidianStick);
		registerItem(ModItems.wrench);
		/* Future spoilers
		registerItem(ModItems.voidPearl);
		registerItem(ModItems.voidIngot);
		GameRegistry.registerItem(ModItems.voidHelmet, "voidHelmet");
		GameRegistry.registerItem(ModItems.voidChestplate, "voidChestplate");
		GameRegistry.registerItem(ModItems.voidLeggings, "voidLeggings");
		GameRegistry.registerItem(ModItems.voidBoots, "voidBoots");
		*/
	}
	protected void registerItem(ItemEM item){GameRegistry.registerItem(item, item.getBasicName());}
	public void blocks(){
		registerBlock(ModBlocks.machineFrame);
		registerBlock(ModBlocks.generatorFurnace);
		registerBlock(ModBlocks.generatorLiving);
		registerBlock(ModBlocks.enderNode);
		registerBlock(ModBlocks.furnace);
		registerBlock(ModBlocks.grinder);
	}
	protected void registerBlock(BlockEM block){GameRegistry.registerBlock(block, block.getBasicName());}
	public void tileEntities(){
		GameRegistry.registerTileEntity(TileMachineFrame.class, "machineFrame");
		GameRegistry.registerTileEntity(TileGeneratorFurnace.class, "generatorFurnace");
		GameRegistry.registerTileEntity(TileGeneratorLiving.class, "generatorLiving");
		GameRegistry.registerTileEntity(TileEnderNode.class, "enderNode");
		GameRegistry.registerTileEntity(TileFurnaceEM.class, "furnace");
		GameRegistry.registerTileEntity(TileGrinder.class, "grinder");
	}
	public void entities(){
		//EntityRegistry.registerModEntity(Class EntityClass, String unlocalizedName, int modSpecificID, EnderMechanics.instance, int trackingRange, int updateFrequency, true);
	}
	
	public void crafting(){
		//Items - Tier 1
		GameRegistry.addRecipe(new ItemStack(ModItems.obsidianStick, 4), "o", "o", 'o', Blocks.obsidian);
		if(Utility.enableWrench){
			GameRegistry.addRecipe(new ItemStack(ModItems.wrench, 1, 0), "i i", " p ", " i ", 'i', Items.iron_ingot, 'p', Items.ender_pearl);
		}
		//Items - Tier 2
		ItemStack lapis = new ItemStack(Items.dye, 1, 4);
		GameRegistry.addRecipe(new ItemStack(ModItems.pearlEM), " l ", "lpl", " l ", 'l', lapis, 'p', Items.ender_pearl);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotEM, 4), Items.iron_ingot, Items.iron_ingot, Items.gold_ingot, Items.gold_ingot, Items.diamond, lapis, lapis, lapis, lapis);
		//Multi-tool wrench coming soon
		
		//Blocks
		if(Utility.enableGenFurnace)
			GameRegistry.addRecipe(new ItemStack(ModBlocks.generatorFurnace), " l ", "bpb", "cfc", 'l', Items.lava_bucket, 'b', Items.blaze_rod, 'p', ModItems.pearlEM, 'c', Items.coal, 'f', Blocks.furnace);
		if(Utility.enableGenLiving)
			GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.generatorLiving, " c ", "sps", "wfw", 'c', Items.cooked_beef, 's', "treeSapling", 'p', ModItems.pearlEM, 'w', "logWood", 'f', Blocks.furnace));
		if(Utility.enableFrame)
			GameRegistry.addRecipe(new ItemStack(ModBlocks.machineFrame), "isi", "sps", "isi", 'i', ModItems.ingotEM, 's', ModItems.obsidianStick, 'p', ModItems.pearlEM);
		if(Utility.enableNode)
			GameRegistry.addRecipe(new ItemStack(ModBlocks.enderNode), "sis", "ipi", "sis", 's', ModItems.obsidianStick, 'i', ModItems.ingotEM, 'p', ModItems.pearlEM);
	}
	public void smelting(){
		//GameRegistry.addSmelting(ItemStack input, ItemStack output, float xp);
	}
	public void machineRecipes(){
		TileGrinder.addRecipes();
	}
}

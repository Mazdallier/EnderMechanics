package com.endreman0.endermechanics;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.endreman0.endermechanics.block.ModBlocks;
import com.endreman0.endermechanics.item.ItemEM;
import com.endreman0.endermechanics.item.ModItems;
import com.endreman0.endermechanics.tile.TileRiftNode;
import com.endreman0.endermechanics.tile.TileSyncFrame;
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
		registerItem(ModItems.focus);
	}
	protected void registerItem(ItemEM item){GameRegistry.registerItem(item, item.getBasicName());}
	public void blocks(){
		GameRegistry.registerBlock(ModBlocks.syncFrame, ModBlocks.syncFrame.getBasicName());
		GameRegistry.registerBlock(ModBlocks.riftNode, ModBlocks.riftNode.getBasicName());
	}
	public void tileEntities(){
		/*GameRegistry.registerTileEntity(TileGeneratorFurnace.class, "generatorFurnace");
		GameRegistry.registerTileEntity(TileGeneratorLiving.class, "generatorLiving");
		GameRegistry.registerTileEntity(TileGeneratorTool.class, "generatorTool");
		GameRegistry.registerTileEntity(TileGeneratorPotion.class, "generatorPotion");
		GameRegistry.registerTileEntity(TileGeneratorNetherStar.class, "generatorNetherStar");
		GameRegistry.registerTileEntity(TileGeneratorCreative.class, "generatorCreative");
		
		GameRegistry.registerTileEntity(TileFurnaceEM.class, "furnace");
		GameRegistry.registerTileEntity(TilePulverizer.class, "pulverizer");
		GameRegistry.registerTileEntity(TileCrystallizer.class, "crystallizer");
		GameRegistry.registerTileEntity(TileEnrichmentCell.class, "enrichmentCell");
		GameRegistry.registerTileEntity(TilePurifier.class, "purifier");*/
		
		GameRegistry.registerTileEntity(TileSyncFrame.class, "syncFrame");
		GameRegistry.registerTileEntity(TileRiftNode.class, "riftNode");
	}
	public void entities(){
		//EntityRegistry.registerModEntity(Class EntityClass, String unlocalizedName, int modSpecificID, EnderMechanics.instance, int trackingRange, int updateFrequency, true);
	}
	
	public void crafting(){
		GameRegistry.addRecipe(new ItemStack(ModItems.obsidianStick, 4), "o", "o", 'o', Blocks.obsidian);
		ItemStack lapis = new ItemStack(Items.dye, 1, 4);
		GameRegistry.addRecipe(new ItemStack(ModItems.pearlEM), " l ", "lpl", " l ", 'l', lapis, 'p', Items.ender_pearl);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotEM, 4), Items.iron_ingot, Items.iron_ingot, Items.gold_ingot, Items.gold_ingot, Items.diamond, lapis, lapis, lapis, lapis);
		
		//Wrenches
		GameRegistry.addRecipe(new ItemStack(ModItems.wrench, 1, 0), "i i", " s ", " p ", 'i', new ItemStack(ModItems.ingotEM, 1, 0), 's', new ItemStack(ModItems.obsidianStick), 'p', new ItemStack(ModItems.pearlEM, 1, 0));
		GameRegistry.addRecipe(new ItemStack(ModItems.wrench, 1, 1), "i i", " w ", " p ", 'i', new ItemStack(ModItems.ingotEM, 1, 1), 'w', new ItemStack(ModItems.wrench, 1, 0), 'p', new ItemStack(ModItems.pearlEM, 1, 1));
		GameRegistry.addRecipe(new ItemStack(ModItems.wrench, 1, 2), "i i", " w ", " p ", 'i', new ItemStack(ModItems.ingotEM, 1, 2), 'w', new ItemStack(ModItems.wrench, 1, 1), 'p', new ItemStack(ModItems.pearlEM, 1, 2));
		GameRegistry.addRecipe(new ItemStack(ModItems.wrench, 1, 3), "i i", " w ", " p ", 'i', new ItemStack(ModItems.ingotEM, 1, 3), 'w', new ItemStack(ModItems.wrench, 1, 2), 'p', new ItemStack(ModItems.pearlEM, 1, 3));
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.syncFrame), "isi", "sps", "isi", 'i', ModItems.ingotEM, 's', ModItems.obsidianStick, 'p', ModItems.pearlEM);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.riftNode), "sis", "ipi", "sis", 's', ModItems.obsidianStick, 'i', ModItems.ingotEM, 'p', ModItems.pearlEM);
		GameRegistry.addRecipe(new ItemStack(ModItems.focus, 1, 0), " c ", "sls", " p ", 'c', Items.coal, 's', ModItems.obsidianStick, 'l', Items.lava_bucket, 'p', new ItemStack(ModItems.pearlEM, 1, 1));
	}
	public void smelting(){
		GameRegistry.addSmelting(new ItemStack(ModItems.orePieces[0], 1, 0), new ItemStack(Items.iron_ingot), 0);
		GameRegistry.addSmelting(new ItemStack(ModItems.orePieces[0], 1, 1), new ItemStack(Items.gold_ingot), 0);
	}
	public void machineRecipes(){
		
	}
	public void postInit(){
		//This is done in post-init to allow other mods to add their ore types before it is registered.
		for(int i=0;i<ModItems.orePieces.length;i++) registerItem(ModItems.orePieces[i]);
	}
}

package com.endreman0.endermechanics.util;

import java.io.File;

import com.endreman0.endermechanics.item.ModItems;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.ForgeDirection;

public class Utility{
	public static final Utility instance = new Utility();
	private Utility(){}//Disables public constructor
	//Utility fields
	public static final String MOD_ID = "EnderMechanics";
	public static final String MOD_NAME = "Ender Mechanics";
	public static final String VERSION = "1.7.10-0.0.2";
	public static final String CLIENT_PROXY = "com.endreman0.endermechanics.ClientProxy";
	public static final String SERVER_PROXY = "com.endreman0.endermechanics.CommonProxy";
	public static final String GUI_FACTORY = "com.endreman0.endermechanics.gui.GuiFactory";
	public static final String RESOURCE_PREFIX = MOD_ID.toLowerCase();
	public static final ResourceLocation GUI_UTILS = new ResourceLocation(RESOURCE_PREFIX, "textures/gui/utils.png");
	public static final CreativeTabs EM_TAB = new CreativeTabs(MOD_ID.toLowerCase()){
		@Override public Item getTabIconItem(){return ModItems.wrench;}
	};
	
	//Helper methods
	public static String className(Object obj){
		String string = obj.getClass().getCanonicalName();//"com.endreman0.endermechanics.item.ItemWrench", "net.minecraft.block.BlockDirt", "java.util.List"
		string = string.substring(string.lastIndexOf('.')+1);//"ItemWrench", "BlockDirt", "List"
		return string;
	}
	public static String powerString(int power){
		float scaledPower = Math.abs(power);
		String unit;
		if(power>=1000000){//1 million Endergy = 1mE (megaEndergy)
			scaledPower/=1000000;
			unit="mE";
		}else if(power>=1000){//1 thousand Endergy = 1kE (kiloEndergy)
			scaledPower/=1000;
			unit="kE";
		}else{
			unit = "E";
		}
		String string = (power<0 ? "-" : "") + String.valueOf(scaledPower);
		if(scaledPower==Math.floor(scaledPower) && string.contains(".")){//If it's a whole number, just return the whole part.
			string = string.substring(0, string.indexOf('.'));
		}else{
			if(string.length()>4) string = string.substring(0, 4);//Truncate to two decimal points
		}
		return string + unit;
	}
	public static void addChat(EntityPlayer player, String message){
		player.addChatMessage(new ChatComponentText(message));
	}
	public static boolean canConsume(ItemStack recipe, ItemStack actual){
		if(recipe==null || actual==null) return false;
		if(!recipe.getItem().equals(actual.getItem())) return false;
		if(recipe.getItemDamage()!=Recipes.COPY_META && recipe.getItemDamage()!=actual.getItemDamage()) return false;
		if(recipe.stackSize>actual.stackSize) return false;
		return true;
	}
	public static ItemStack merge(ItemStack stack1, ItemStack stack2){
		if(stack2==null){return stack1.copy();}
		if(!stack1.getItem().equals(stack2.getItem())) return null;
		if(stack1.getItemDamage()!=stack2.getItemDamage()) return null;
		if(stack1.stackSize + stack2.stackSize > 64) return null;
		
		ItemStack ret = stack1.copy();
		ret.stackSize+=stack2.stackSize;
		return ret;
	}
	public static String stackToString(ItemStack stack){
		return stack.toString();
	}
	
	//Config variables
	public static boolean wrenchKey = true;
	public static int nodeRange = 5;
	public static boolean enableWrench = true;
	public static boolean enableFrame = true;
	public static boolean enableGenFurnace = true;
	public static boolean enableGenLiving = true;
	public static boolean enableNode = true;
	
	
	//Config stuff
	public static Configuration config;
	public static final String catGeneral = Configuration.CATEGORY_GENERAL;
	public static final String catEnable = "enable";
	public static void init(File file){
		if(config==null){
			config = new Configuration(file);
			readConfig();
		}
	}
	private static void readConfig(){
		wrenchKey = getBoolean(catGeneral, "wrenchKey", true);
		nodeRange = getInt(catGeneral, "nodeRange", 5, 1, 10);
		enableWrench = getBoolean(catEnable, "wrench", true);
		enableFrame = getBoolean(catEnable, "machineFrame", true);
		enableGenFurnace = getBoolean(catEnable, "generatorFurnace", true);
		enableGenLiving = getBoolean(catEnable, "generatorLiving", true);
		enableNode = getBoolean(catEnable, "node", true);
		if(config.hasChanged()){config.save();}
	}
	//Config helper methods
	private static boolean getBoolean(String category, String name, boolean value){
		return config.getBoolean(StatCollector.translateToLocal("endermechanics.config."+category+'.'+name+".name"), category, value, StatCollector.translateToLocal("endermechanics.config."+category+'.'+name+".tooltip"));
	}
	private static int getInt(String category, String name, int value, int min, int max){
		return config.getInt(StatCollector.translateToLocal("endermechanics.config."+category+'.'+name+".name"), category, value, min, max, StatCollector.translateToLocal("endermechanics.config."+category+'.'+name+".tooltip"));
	}
	@SubscribeEvent
	public void configChanged(ConfigChangedEvent.OnConfigChangedEvent event){
		if(event.modID.equalsIgnoreCase(Utility.MOD_ID)){
			readConfig();
		}
	}
}

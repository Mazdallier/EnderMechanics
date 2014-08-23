package com.endreman0.endermechanics;

import java.io.File;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

public class Utility{
	//Utility fields
	public static final String MOD_ID = "EnderMechanics";
	public static final String MOD_NAME = "Ender Mechanics";
	public static final String VERSION = "1.7.10-0.0";
	public static final String CLIENT_PROXY = "com.endreman0.endermechanics.ClientProxy";
	public static final String SERVER_PROXY = "com.endreman0.endermechanics.CommonProxy";
	public static final String GUI_FACTORY = "com.endreman0.endermechanics.gui.GuiFactory";
	public static final String RESOURCE_PREFIX = MOD_ID.toLowerCase();
	public static final CreativeTabs EM_TAB = new CreativeTabs(MOD_ID.toLowerCase()){
		@Override
		public Item getTabIconItem(){
			//return ModItems.scythe;
			return Items.ender_eye;
		}
	};
	
	//Helper methods
	
	//Config variables
	public static boolean test = false;
	public static boolean test2 = true;
	
	
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
		test = getBoolean(catGeneral, "test", test);
		test2 = getBoolean(catEnable, "test2", test2);
		if(config.hasChanged()){config.save();}
	}
	//Config helper methods
	private static boolean getBoolean(String category, String name, boolean value){
		return config.getBoolean(StatCollector.translateToLocal("endermech.config."+category+'.'+name+".name"), category, value, StatCollector.translateToLocal("endermech.config."+category+'.'+name+".tooltip"));
	}
	private static int getInt(String category, String name, int value, int min, int max){
		return config.getInt(StatCollector.translateToLocal("endermech.config."+category+'.'+name+".name"), category, value, min, max, StatCollector.translateToLocal("endermech.config."+category+'.'+name+".tooltip"));
	}
	@SubscribeEvent
	public void configChanged(ConfigChangedEvent.OnConfigChangedEvent event){
		if(event.modID.equalsIgnoreCase(Utility.MOD_ID)){
			readConfig();
		}
	}
}

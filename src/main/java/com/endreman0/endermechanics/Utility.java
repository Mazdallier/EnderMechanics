package com.endreman0.endermechanics;

import java.io.File;

import com.endreman0.endermechanics.item.ModItems;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

public class Utility{
	//Utility fields
	public static final String MOD_ID = "EnderMechanics";
	public static final String MOD_NAME = "Ender Mechanics";
	public static final String VERSION = "1.7.10-0.0.2";
	public static final String CLIENT_PROXY = "com.endreman0.endermechanics.ClientProxy";
	public static final String SERVER_PROXY = "com.endreman0.endermechanics.CommonProxy";
	public static final String GUI_FACTORY = "com.endreman0.endermechanics.gui.GuiFactory";
	public static final String RESOURCE_PREFIX = MOD_ID.toLowerCase();
	public static final CreativeTabs EM_TAB = new CreativeTabs(MOD_ID.toLowerCase()){
		@Override public Item getTabIconItem(){return ModItems.wrench;}
	};
	
	//Helper methods
	
	//Config variables
	public static boolean wrenchKey = true;
	public static int nodeRange = 5;
	public static int nodeUpdate = 40;
	public static boolean enableWrench = true;
	public static boolean enableFrame = true;
	public static boolean enableGenFurnace = true;
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
		//nodeUpdate = Math.max(getInt(catGeneral, "nodeUpdate", Math.max(40, (2*nodeRange+1)), (2*nodeRange + 1), 200), 2*nodeRange+1);//Make sure it's a legal value. Too small means nodes don't complete all of their scan.
		nodeUpdate = getInt(catGeneral, "nodeUpdate", Math.max(40, (2*nodeRange+1)), (2*nodeRange + 1), 200);
		enableWrench = getBoolean(catEnable, "wrench", true);
		enableFrame = getBoolean(catEnable, "machineFrame", true);
		enableGenFurnace = getBoolean(catEnable, "generatorFurnace", true);
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

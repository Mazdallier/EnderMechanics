package com.endreman0.endermechanics;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Utility.MOD_ID, name=Utility.MOD_NAME, version=Utility.VERSION)
public class EnderMechanics {
	@Instance(Utility.MOD_ID)
	public EnderMechanics instance;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		//ModItems.initItems();
		//ModBlocks.init();
		//Utility.init();//Config
	}
	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		//ModItems.initRecipes();
	}
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		//if(Loader.isModLoaded("modid"){
	}
}

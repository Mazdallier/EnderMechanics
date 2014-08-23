package com.endreman0.endermechanics;

import com.endreman0.endermechanics.block.ModBlocks;
import com.endreman0.endermechanics.multipart.ModMultiparts;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Utility.MOD_ID, name=Utility.MOD_NAME, version=Utility.VERSION, guiFactory=Utility.GUI_FACTORY, dependencies="after:ForgeMultipart")
public class EnderMechanics{
	@Mod.Instance(Utility.MOD_ID)
	public EnderMechanics instance;
	
	@SidedProxy(clientSide=Utility.CLIENT_PROXY,serverSide=Utility.SERVER_PROXY)
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		Utility.init(event.getSuggestedConfigurationFile());//Config
		LogHelper.info("Testing Config Value: " + Utility.test);
		//ModItems.initItems();
		ModBlocks.init();
		if(Loader.isModLoaded("ForgeMultipart")){
			new ModMultiparts().init();
		}
	}
	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		//ModItems.initRecipes();
		MinecraftForge.EVENT_BUS.register(new Events());
		FMLCommonHandler.instance().bus().register(new Utility());//ConfigChangedEvent
		proxy.registerEntities();
		proxy.registerTileEntities();
		proxy.registerRenders();
	}
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		//if(Loader.isModLoaded("modid"){
	}
}

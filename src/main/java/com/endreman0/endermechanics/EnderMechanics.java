package com.endreman0.endermechanics;

import com.endreman0.endermechanics.gui.GuiHandler;
import com.endreman0.endermechanics.item.ItemWrench;
import com.endreman0.endermechanics.item.ModItems;
import com.endreman0.endermechanics.util.Utility;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid=Utility.MOD_ID, name=Utility.MOD_NAME, version=Utility.VERSION, guiFactory=Utility.GUI_FACTORY)
public class EnderMechanics{
	@Mod.Instance(Utility.MOD_ID)
	public static EnderMechanics instance;
	
	@SidedProxy(clientSide=Utility.CLIENT_PROXY, serverSide=Utility.SERVER_PROXY)
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		Utility.init(event.getSuggestedConfigurationFile());//Config
		proxy.items();
		proxy.blocks();
	}
	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		//Recipes
		proxy.crafting();
		proxy.smelting();
		proxy.machineRecipes();
		
		//Events
		MinecraftForge.EVENT_BUS.register(ModItems.wrench);
		FMLCommonHandler.instance().bus().register(Utility.instance);//ConfigChangedEvent
		
		//Random stuff
		proxy.entities();
		proxy.tileEntities();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		
		//Renderers
		proxy.itemRenders();
		proxy.tileRenders();
		proxy.entityRenders();
	}
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		//if(Loader.isModLoaded("modid"){
		//Update checker, if that ever gets figured out
	}
}

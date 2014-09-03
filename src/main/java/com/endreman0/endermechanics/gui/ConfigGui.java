package com.endreman0.endermechanics.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

import com.endreman0.endermechanics.Utility;

import cpw.mods.fml.client.config.DummyConfigElement.DummyCategoryElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.GuiConfigEntries;
import cpw.mods.fml.client.config.GuiConfigEntries.CategoryEntry;
import cpw.mods.fml.client.config.IConfigElement;

public class ConfigGui extends GuiConfig{
	public ConfigGui(GuiScreen screen){
		super(screen, getConfigElements(), Utility.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(Utility.config.toString()));
	}
	private static List<IConfigElement> getConfigElements(){
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.addAll(new ConfigElement(Utility.config.getCategory(Utility.catGeneral)).getChildElements());
		list.add(new DummyCategoryElement("Enable", "endermechanics.config.enable", EnableEntry.class));
		return list;
	}
	public static class EnableEntry extends CategoryEntry{
		public EnableEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement){
			super(owningScreen, owningEntryList, configElement);
		}
		@Override
		protected GuiScreen buildChildScreen(){
			return new GuiConfig(this.owningScreen, new ConfigElement(Utility.config.getCategory(Utility.catEnable)).getChildElements(), Utility.MOD_ID,
					Utility.catEnable, true, true, GuiConfig.getAbridgedConfigPath(Utility.config.toString()));
		}
	}
}

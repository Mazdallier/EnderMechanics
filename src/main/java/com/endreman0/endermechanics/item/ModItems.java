package com.endreman0.endermechanics.item;

import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

import com.endreman0.endermechanics.item.focus.ItemFocusEM;

public class ModItems{
	//Basic resources
	public static final ItemEM pearlEM = new ItemPearlEM();
	public static final ItemEM ingotEM = new ItemIngotEM();
	public static final ItemEM obsidianStick = new ItemObsidianStick();
	
	//Items
	public static final ItemEM wrench = new ItemWrench();
	public static final ItemFocusEM focus = new ItemFocusEM();
	
	//Processed ore items
	public static final ItemEM[] orePieces = getProcessedOres();
	private static ItemEM[] getProcessedOres(){
		ItemEM[] ores = new ItemEM[4];
		for(int i=0;i<ores.length;i++) ores[i] = new ItemProcessedOre(i);
		return ores;
	}
	
	//Armor
	public static final ArmorMaterial armorMatVoid = EnumHelper.addArmorMaterial("voidsteel", -1, new int[]{4, 10, 7, 4}, 0);
	public static final ItemVoidArmor voidHelmet = new ItemVoidArmor(0);
	public static final ItemVoidArmor voidChestplate = new ItemVoidArmor(1);
	public static final ItemVoidArmor voidLeggings = new ItemVoidArmor(2);
	public static final ItemVoidArmor voidBoots = new ItemVoidArmor(3);
}

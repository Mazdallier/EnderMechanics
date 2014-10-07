package com.endreman0.endermechanics.item;

import com.endreman0.endermechanics.util.Utility;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class ItemEM extends Item{
	public ItemEM(){
		super();
		maxStackSize=1;
		setCreativeTab(Utility.EM_TAB);
	}
	@Override
    public String getUnlocalizedName(){
        return String.format("item.%s%s", Utility.MOD_ID.toLowerCase() + ":", getBasicName());
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack){
        return String.format("item.%s%s", Utility.MOD_ID.toLowerCase() + ":", getBasicName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister){
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }
    public String getBasicName(){
		String name = super.getUnlocalizedName();
		return name.substring(name.indexOf('.')+1);
    }
}

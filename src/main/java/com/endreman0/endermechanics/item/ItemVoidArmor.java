package com.endreman0.endermechanics.item;

import com.endreman0.endermechanics.Utility;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemVoidArmor extends ItemArmor{
	public ItemVoidArmor(ArmorMaterial material, int type){
		super(material, type, type);
		setCreativeTab(Utility.EM_TAB);
		switch(type){
			case(0):
				setUnlocalizedName("voidHelmet");
			break;
			case(1):
				setUnlocalizedName("voidChestplate");
			break;
			case(2):
				setUnlocalizedName("voidLeggings");
			break;
			case(3):
				setUnlocalizedName("voidBoots");
			break;
		}
	}
	
	@Override public boolean getIsRepairable(ItemStack stack1, ItemStack stack2){return false;}
	@Override @SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, int slot){
		if(stack.getItem() instanceof ItemVoidArmor){
			ModelBiped model = (slot==2 ? new ModelVoidArmor(1) : new ModelVoidArmor(0.5F));
			model.bipedHead.showModel = model.bipedHeadwear.showModel = slot==0;//Only show skin on head
			model.bipedBody.showModel = slot==1||slot==2;
			model.bipedRightArm.showModel = model.bipedLeftArm.showModel = slot==1;
			model.bipedRightLeg.showModel = model.bipedLeftLeg.showModel = slot==2||slot==3;
			return model;
		}
		return super.getArmorModel(entity, stack, slot);
	}
	
	private static class ModelVoidArmor extends ModelBiped{
		public ModelVoidArmor(float size){
			super(size);
		}
		@Override
		public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7){
			if(entity instanceof EntityLivingBase){
				EntityLivingBase elb = (EntityLivingBase)entity;
				isSneak = elb.isSneaking();
				isRiding = elb.isRiding();
				isChild = elb.isChild();
			}
			setRotationAngles(par2, par3, par4, par5, par6, par7, entity);
			super.render(entity, par2, par3, par4, par5, par6, par7);
		}
	}
	
	@Override
	public String getUnlocalizedName(){
		return String.format("item.%s%s", Utility.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	@Override
	public String getUnlocalizedName(ItemStack itemStack){
		return String.format("item.%s%s", Utility.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
	protected String getUnwrappedUnlocalizedName(String unlocalizedName){
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}

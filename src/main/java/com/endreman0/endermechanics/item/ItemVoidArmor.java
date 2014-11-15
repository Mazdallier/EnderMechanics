package com.endreman0.endermechanics.item;

import java.util.List;

import com.endreman0.endermechanics.util.Utility;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class ItemVoidArmor extends ItemArmor{
	ItemVoidArmor(int type){
		super(ModItems.armorMatVoid, 4, type);
		if(type>=0){setCreativeTab(Utility.EM_TAB);}else{setCreativeTab(null);}
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
			default:
				setUnlocalizedName("voidArmor");
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
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type){
		if(stack.getItem().equals(ModItems.voidHelmet) || stack.getItem().equals(ModItems.voidChestplate) || stack.getItem().equals(ModItems.voidBoots)){
			return Utility.RESOURCE_PREFIX + ":textures/armor/void1.png";
		}else if(stack.getItem().equals(ModItems.voidLeggings)){
			return Utility.RESOURCE_PREFIX + ":textures/armor/void2.png";
		}
		return null;
	}
	
	@Override
	public String getUnlocalizedName(){
		return String.format("item.%s%s", Utility.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	@Override
	public String getUnlocalizedName(ItemStack itemStack){
		return String.format("item.%s%s", Utility.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
	protected String getUnwrappedUnlocalizedName(String unlocalizedName){
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
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
}

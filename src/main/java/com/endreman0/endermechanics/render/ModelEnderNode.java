package com.endreman0.endermechanics.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEnderNode extends ModelBase{
	ModelRenderer cube1, cube2;
	
	public ModelEnderNode(){
		textureWidth = 64;
		textureHeight = 64;
		
		cube1 = new ModelRenderer(this, 0, 32);
		cube1.addBox(-6F, -6F, -6F, 12, 12, 12);
		cube1.setRotationPoint(0F, 16F, 0F);
		cube1.setTextureSize(64, 64);
		cube1.mirror = true;
		setRotation(cube1, (float)Math.toRadians(22.5), (float)Math.toRadians(45), (float)Math.toRadians(22.5));
		cube2 = new ModelRenderer(this, 0, 32);
		cube2.addBox(-6F, -6F, -6F, 12, 12, 12);
		cube2.setRotationPoint(0F, 16F, 0F);
		cube2.setTextureSize(64, 64);
		cube2.mirror = true;
		setRotation(cube2, (float)Math.toRadians(-22.5), (float)Math.toRadians(45), (float)Math.toRadians(-22.5));
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		cube1.render(f5);
		cube2.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float speed1 = 0.02F;
		float speed2 = 0.03F;
		float speed3 = 0.05F;
		setRotation(cube1, f*speed1, -f*speed1, f*speed2);
		setRotation(cube2, -f*speed2, f*speed3, -f*speed3);
	}

}

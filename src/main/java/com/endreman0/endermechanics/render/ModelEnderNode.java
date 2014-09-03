package com.endreman0.endermechanics.render;

import com.endreman0.endermechanics.LogHelper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEnderNode extends ModelBase{
	//fields
	ModelRenderer outer1, outer2, inner1, inner2;
	
	public ModelEnderNode(){
		textureWidth = 64;
		textureHeight = 64;
		
		outer1 = new ModelRenderer(this, 0, 0);
		outer1.addBox(-8F, -8F, -8F, 16, 16, 16);
		outer1.setRotationPoint(0F, 16F, 0F);
		outer1.setTextureSize(64, 64);
		outer1.mirror = true;
		setRotation(outer1, (float)Math.toRadians(22.5), (float)Math.toRadians(45), (float)Math.toRadians(22.5));
		outer2 = new ModelRenderer(this, 0, 0);
		outer2.addBox(-8F, -8F, -8F, 16, 16, 16);
		outer2.setRotationPoint(0F, 16F, 0F);
		outer2.setTextureSize(64, 64);
		outer2.mirror = true;
		setRotation(outer2, (float)Math.toRadians(0), (float)Math.toRadians(0), (float)Math.toRadians(0));
		inner1 = new ModelRenderer(this, 0, 32);
		inner1.addBox(-6F, -6F, -6F, 12, 12, 12);
		inner1.setRotationPoint(0F, 16F, 0F);
		inner1.setTextureSize(64, 64);
		inner1.mirror = true;
		setRotation(inner1, (float)Math.toRadians(22.5), (float)Math.toRadians(45), (float)Math.toRadians(22.5));
		inner2 = new ModelRenderer(this, 0, 32);
		inner2.addBox(-6F, -6F, -6F, 12, 12, 12);
		inner2.setRotationPoint(0F, 16F, 0F);
		inner2.setTextureSize(64, 64);
		inner2.mirror = true;
		setRotation(inner2, (float)Math.toRadians(-22.5), (float)Math.toRadians(45), (float)Math.toRadians(-22.5));
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		outer1.render(f5);
		outer2.render(f5);
		inner1.render(f5);
		inner2.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float speed = 0.03F;
		setRotation(outer1, f*speed, f*speed, f*speed);
		setRotation(outer2, -f*speed, -f*speed, -f*speed);
		setRotation(inner1, f*speed, -f*speed, f*speed);
		setRotation(inner2, -f*speed, f*speed, -f*speed);
	}

}

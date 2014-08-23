package com.endreman0.endermechanics.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMachineFrame extends ModelBase{
	//fields
	ModelRenderer outerBottom1, outerBottom2, outerBottom3, outerBottom4, outerTop1, outerTop2, outerTop3, outerTop4, outerSides1,outerSides2, outerSides3,
			outerSides4, innerBottom1, innerBottom2, innerBottom3, innerBottom4, innerTop1, innerTop2, innerTop3, innerTop4, innerSides1, innerSides2,
			innerSides3, innerSides4, strutsBottom1, strutsBottom2, strutsBottom3, strutsBottom4, strutsTop1, strutsTop2, strutsTop3, strutsTop4;//28 cuboids!
	
	public ModelMachineFrame(){
		textureWidth = 64;
		textureHeight = 32;
		
		outerTop1 = new ModelRenderer(this, 0, 8);
		outerTop1.addBox(0F, 0F, 0F, 16, 1, 1);
		outerTop1.setRotationPoint(-8F, 23F, -8F);
		outerTop1.setTextureSize(64, 32);
		outerTop1.mirror = true;
		setRotation(outerTop1, 0F, 0F, 0F);
		outerTop2 = new ModelRenderer(this, 0, 10);
		outerTop2.addBox(0F, 0F, 0F, 16, 1, 1);
		outerTop2.setRotationPoint(-8F, 23F, 7F);
		outerTop2.setTextureSize(64, 32);
		outerTop2.mirror = true;
		setRotation(outerTop2, 0F, 0F, 0F);
		outerTop3 = new ModelRenderer(this, 0, 12);
		outerTop3.addBox(0F, 0F, 0F, 16, 1, 1);
		outerTop3.setRotationPoint(-7F, 23F, -8F);
		outerTop3.setTextureSize(64, 32);
		outerTop3.mirror = true;
		setRotation(outerTop3, 0F, (float) Math.toRadians(-90), 0F);
		outerTop4 = new ModelRenderer(this, 0, 14);
		outerTop4.addBox(0F, 0F, 0F, 16, 1, 1);
		outerTop4.setRotationPoint(8F, 23F, -8F);
		outerTop4.setTextureSize(64, 32);
		outerTop4.mirror = true;
		setRotation(outerTop4, 0F, (float) Math.toRadians(-90), 0F);
		
		outerBottom1 = new ModelRenderer(this, 0, 0);
		outerBottom1.addBox(0F, 0F, 0F, 16, 1, 1);
		outerBottom1.setRotationPoint(-8F, 8F, -8F);
		outerBottom1.setTextureSize(64, 32);
		outerBottom1.mirror = true;
		setRotation(outerBottom1, 0F, 0F, 0F);
		outerBottom2 = new ModelRenderer(this, 0, 2);
		outerBottom2.addBox(0F, 0F, 0F, 16, 1, 1);
		outerBottom2.setRotationPoint(-8F, 8F, 7F);
		outerBottom2.setTextureSize(64, 32);
		outerBottom2.mirror = true;
		setRotation(outerBottom2, 0F, 0F, 0F);
		outerBottom3 = new ModelRenderer(this, 0, 4);
		outerBottom3.addBox(0F, 0F, 0F, 16, 1, 1);
		outerBottom3.setRotationPoint(-7F, 8F, -8F);
		outerBottom3.setTextureSize(64, 32);
		outerBottom3.mirror = true;
		setRotation(outerBottom3, 0F, (float) Math.toRadians(-90), 0F);
		outerBottom4 = new ModelRenderer(this, 0, 6);
		outerBottom4.addBox(0F, 0F, 0F, 16, 1, 1);
		outerBottom4.setRotationPoint(8F, 8F, -8F);
		outerBottom4.setTextureSize(64, 32);
		outerBottom4.mirror = true;
		setRotation(outerBottom4, 0F, (float) Math.toRadians(-90), 0F);
		
		outerSides1 = new ModelRenderer(this, 0, 16);
		outerSides1.addBox(0F, 0F, 0F, 16, 1, 1);
		outerSides1.setRotationPoint(-7F, 8F, -8F);
		outerSides1.setTextureSize(64, 32);
		outerSides1.mirror = true;
		setRotation(outerSides1, 0F, 0F, (float) Math.toRadians(90));
		outerSides2 = new ModelRenderer(this, 0, 18);
		outerSides2.addBox(0F, 0F, 0F, 16, 1, 1);
		outerSides2.setRotationPoint(-7F, 8F, 7F);
		outerSides2.setTextureSize(64, 32);
		outerSides2.mirror = true;
		setRotation(outerSides2, 0F, 0F, (float) Math.toRadians(90));
		outerSides3 = new ModelRenderer(this, 0, 20);
		outerSides3.addBox(0F, 0F, 0F, 16, 1, 1);
		outerSides3.setRotationPoint(8F, 8F, -8F);
		outerSides3.setTextureSize(64, 32);
		outerSides3.mirror = true;
		setRotation(outerSides3, 0F, 0F, (float) Math.toRadians(90));
		outerSides4 = new ModelRenderer(this, 0, 22);
		outerSides4.addBox(0F, 0F, 0F, 16, 1, 1);
		outerSides4.setRotationPoint(8F, 8F, 7F);
		outerSides4.setTextureSize(64, 32);
		outerSides4.mirror = true;
		setRotation(outerSides4, 0F, 0F, (float) Math.toRadians(90));
		
		innerBottom1 = new ModelRenderer(this, 34, 0);
		innerBottom1.addBox(0F, 0F, 0F, 8, 1, 1);
		innerBottom1.setRotationPoint(-4F, 12F, -4F);
		innerBottom1.setTextureSize(64, 32);
		innerBottom1.mirror = true;
		setRotation(innerBottom1, 0F, 0F, 0F);
		innerBottom2 = new ModelRenderer(this, 34, 2);
		innerBottom2.addBox(0F, 0F, 0F, 8, 1, 1);
		innerBottom2.setRotationPoint(-4F, 12F, 3F);
		innerBottom2.setTextureSize(64, 32);
		innerBottom2.mirror = true;
		setRotation(innerBottom2, 0F, 0F, 0F);
		innerBottom3 = new ModelRenderer(this, 34, 4);
		innerBottom3.addBox(0F, 0F, 0F, 8, 1, 1);
		innerBottom3.setRotationPoint(-3F, 12F, -4F);
		innerBottom3.setTextureSize(64, 32);
		innerBottom3.mirror = true;
		setRotation(innerBottom3, 0F, (float) Math.toRadians(-90), 0F);
		innerBottom4 = new ModelRenderer(this, 34, 6);
		innerBottom4.addBox(0F, 0F, 0F, 8, 1, 1);
		innerBottom4.setRotationPoint(4F, 12F, -4F);
		innerBottom4.setTextureSize(64, 32);
		innerBottom4.mirror = true;
		setRotation(innerBottom4, 0F, (float) Math.toRadians(-90), 0F);
		
		innerTop1 = new ModelRenderer(this, 34, 8);
		innerTop1.addBox(0F, 0F, 0F, 8, 1, 1);
		innerTop1.setRotationPoint(-4F, 19F, -4F);
		innerTop1.setTextureSize(64, 32);
		innerTop1.mirror = true;
		setRotation(innerTop1, 0F, 0F, 0F);
		innerTop2 = new ModelRenderer(this, 34, 10);
		innerTop2.addBox(0F, 0F, 0F, 8, 1, 1);
		innerTop2.setRotationPoint(-4F, 19F, 3F);
		innerTop2.setTextureSize(64, 32);
		innerTop2.mirror = true;
		setRotation(innerTop2, 0F, 0F, 0F);
		innerTop3 = new ModelRenderer(this, 34, 12);
		innerTop3.addBox(0F, 0F, 0F, 8, 1, 1);
		innerTop3.setRotationPoint(-3F, 19F, -4F);
		innerTop3.setTextureSize(64, 32);
		innerTop3.mirror = true;
		setRotation(innerTop3, 0F, (float) Math.toRadians(-90), 0F);
		innerTop4 = new ModelRenderer(this, 34, 14);
		innerTop4.addBox(0F, 0F, 0F, 8, 1, 1);
		innerTop4.setRotationPoint(4F, 19F, -4F);
		innerTop4.setTextureSize(64, 32);
		innerTop4.mirror = true;
		setRotation(innerTop4, 0F, (float) Math.toRadians(-90), 0F);
		
		innerSides1 = new ModelRenderer(this, 34, 16);
		innerSides1.addBox(0F, 0F, 0F, 8, 1, 1);
		innerSides1.setRotationPoint(-3F, 12F, -4F);
		innerSides1.setTextureSize(64, 32);
		innerSides1.mirror = true;
		setRotation(innerSides1, 0F, 0F, (float) Math.toRadians(90));
		innerSides2 = new ModelRenderer(this, 34, 18);
		innerSides2.addBox(0F, 0F, 0F, 8, 1, 1);
		innerSides2.setRotationPoint(-3F, 12F, 3F);
		innerSides2.setTextureSize(64, 32);
		innerSides2.mirror = true;
		setRotation(innerSides2, 0F, 0F, (float) Math.toRadians(90));
		innerSides3 = new ModelRenderer(this, 34, 20);
		innerSides3.addBox(0F, 0F, 0F, 8, 1, 1);
		innerSides3.setRotationPoint(4F, 12F, -4F);
		innerSides3.setTextureSize(64, 32);
		innerSides3.mirror = true;
		setRotation(innerSides3, 0F, 0F, (float) Math.toRadians(90));
		innerSides4 = new ModelRenderer(this, 34, 22);
		innerSides4.addBox(0F, 0F, 0F, 8, 1, 1);
		innerSides4.setRotationPoint(4F, 12F, 3F);
		innerSides4.setTextureSize(64, 32);
		innerSides4.mirror = true;
		setRotation(innerSides4, 0F, 0F, (float) Math.toRadians(90));
		
		strutsTop1 = new ModelRenderer(this, 16, 24);
		strutsTop1.addBox(0F, -1F, -0.5F, 7, 1, 1);
		strutsTop1.setRotationPoint(-7F, 24F, -7F);
		strutsTop1.setTextureSize(64, 32);
		strutsTop1.mirror = true;
		setRotation(strutsTop1, (float) Math.toRadians(21), (float) Math.toRadians(-35), (float) Math.toRadians(-45));
		strutsTop2 = new ModelRenderer(this, 16, 26);
		strutsTop2.addBox(0F, -1F, -0.5F, 7, 1, 1);
		strutsTop2.setRotationPoint(-7F, 24F, 7F);
		strutsTop2.setTextureSize(64, 32);
		strutsTop2.mirror = true;
		setRotation(strutsTop2, (float) Math.toRadians(-21), (float) Math.toRadians(35), (float) Math.toRadians(-45));
		strutsTop3 = new ModelRenderer(this, 16, 28);
		strutsTop3.addBox(0F, -1F, -0.5F, 7, 1, 1);
		strutsTop3.setRotationPoint(7F, 24F, -7F);
		strutsTop3.setTextureSize(64, 32);
		strutsTop3.mirror = true;
		setRotation(strutsTop3, (float) Math.toRadians(-21), (float) Math.toRadians(-145), (float) Math.toRadians(45));
		strutsTop4 = new ModelRenderer(this, 16, 30);
		strutsTop4.addBox(0F, -1F, -0.5F, 7, 1, 1);
		strutsTop4.setRotationPoint(7F, 24F, 7F);
		strutsTop4.setTextureSize(64, 32);
		strutsTop4.mirror = true;
		setRotation(strutsTop4, (float) Math.toRadians(21), (float) Math.toRadians(145), (float) Math.toRadians(45));
		
		strutsBottom1 = new ModelRenderer(this, 0, 24);
		strutsBottom1.addBox(0F, 0F, -0.5F, 7, 1, 1);
		strutsBottom1.setRotationPoint(-7F, 8F, -7F);
		strutsBottom1.setTextureSize(64, 32);
		strutsBottom1.mirror = true;
		setRotation(strutsBottom1, (float) Math.toRadians(-21), (float) Math.toRadians(-35), (float)Math.toRadians(45));
		strutsBottom2 = new ModelRenderer(this, 0, 26);
		strutsBottom2.addBox(0F, 0F, -0.5F, 7, 1, 1);
		strutsBottom2.setRotationPoint(-7F, 8F, 7F);
		strutsBottom2.setTextureSize(64, 32);
		strutsBottom2.mirror = true;
		setRotation(strutsBottom2, (float) Math.toRadians(21), (float) Math.toRadians(35), (float) Math.toRadians(45));
		strutsBottom3 = new ModelRenderer(this, 0, 28);
		strutsBottom3.addBox(0F, 0F, -0.5F, 7, 1, 1);
		strutsBottom3.setRotationPoint(7F, 8F, -7F);
		strutsBottom3.setTextureSize(64, 32);
		strutsBottom3.mirror = true;
		setRotation(strutsBottom3, (float) Math.toRadians(21), (float) Math.toRadians(-145), (float) Math.toRadians(-45));
		strutsBottom4 = new ModelRenderer(this, 0, 30);
		strutsBottom4.addBox(0F, 0F, -0.5F, 7, 1, 1);
		strutsBottom4.setRotationPoint(7F, 8F, 7F);
		strutsBottom4.setTextureSize(64, 32);
		strutsBottom4.mirror = true;
		setRotation(strutsBottom4, (float) Math.toRadians(-21), (float) Math.toRadians(145), (float) Math.toRadians(-45));
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		outerBottom1.render(f5);
		outerBottom2.render(f5);
		outerBottom3.render(f5);
		outerBottom4.render(f5);
		outerTop1.render(f5);
		outerTop2.render(f5);
		outerTop3.render(f5);
		outerTop4.render(f5);
		outerSides1.render(f5);
		outerSides2.render(f5);
		outerSides3.render(f5);
		outerSides4.render(f5);
		innerBottom1.render(f5);
		innerBottom2.render(f5);
		innerBottom3.render(f5);
		innerBottom4.render(f5);
		innerTop1.render(f5);
		innerTop2.render(f5);
		innerTop3.render(f5);
		innerTop4.render(f5);
		innerSides1.render(f5);
		innerSides2.render(f5);
		innerSides3.render(f5);
		innerSides4.render(f5);
		strutsBottom1.render(f5);
		strutsBottom2.render(f5);
		strutsBottom3.render(f5);
		strutsBottom4.render(f5);
		strutsTop1.render(f5);
		strutsTop2.render(f5);
		strutsTop3.render(f5);
		strutsTop4.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}

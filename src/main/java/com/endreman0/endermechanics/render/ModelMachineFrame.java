package com.endreman0.endermechanics.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMachineFrame extends ModelBase{
	private final int sides = 6;
	private ModelRenderer[] outer = new ModelRenderer[sides];
	private ModelRenderer[] inner = new ModelRenderer[sides];
	private float[][] rotation = {{0, 0, 0, 0, 90, -90}, {0, 0, 0, 0, 0, 0}, {0, 180, 90, -90, 0, 0}};
	private final int innerSize = 12;
	public ModelMachineFrame(){
		textureWidth = 64; textureHeight = 64;
		for(int i=0;i<sides;i++){
			outer[i] = new ModelRenderer(this, 0, 0);
			outer[i].addBox(-8F, -8F, -8F, 16, 1, 16);
			outer[i].setRotationPoint(0F, 16F, 0F);
			outer[i].setTextureSize(textureWidth, textureHeight);
			setRotation(outer[i], rotation[0][i], rotation[1][i], rotation[2][i]);
			
			inner[i] = new ModelRenderer(this, 0, 17);
			inner[i].addBox(-innerSize/2, -innerSize/2, -innerSize/2, innerSize, 1, innerSize);
			inner[i].setRotationPoint(0F, 16F, 0F);
			inner[i].setTextureSize(textureWidth, textureHeight);
			setRotation(inner[i], rotation[0][i], rotation[1][i], rotation[2][i]);
		}
	}
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		for(ModelRenderer part : outer) part.render(f5);
		for(ModelRenderer part : inner) part.render(f5);
	}
	private void setRotation(ModelRenderer model, double x, double y, double z){
		model.rotateAngleX = (float)Math.toRadians(x);
		model.rotateAngleY = (float)Math.toRadians(y);
		model.rotateAngleZ = (float)Math.toRadians(z);
	}
}

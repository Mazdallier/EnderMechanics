package com.endreman0.endermechanics.render;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRiftNode extends ModelBase{
	private ModelRenderer[] cubes = new ModelRenderer[2];
	private Random rand = new Random();
	private final int size = 10;
	public ModelRiftNode(){
		textureWidth = 64;
		textureHeight = 32;
		
		for(int i=0;i<cubes.length;i++){
			cubes[i] = new ModelRenderer(this, 0, 0);
			cubes[i].addBox(-size/2, -size/2, -size/2, size, size, size);
			cubes[i].setRotationPoint(0F, 16F, 0F);
			cubes[i].setTextureSize(textureWidth, textureHeight);
			setRotation(cubes[i], rand.nextInt(360)-180, rand.nextInt(360)-180, rand.nextInt(360)-180);
		}
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		for(ModelRenderer cube: cubes) cube.render(f5);
	}
	
	private void setRotation(ModelRenderer model, double x, double y, double z){
		model.rotateAngleX = (float)Math.toRadians(x);
		model.rotateAngleY = (float)Math.toRadians(y);
		model.rotateAngleZ = (float)Math.toRadians(z);
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity){
		double speed1 = 1.2; double speed2 = 1.9; double speed3 = 2.8;
		setRotation(cubes[0], f*speed1, -f*speed1, f*speed2);
		setRotation(cubes[1], -f*speed2, f*speed3, -f*speed3);
	}

}

package com.endreman0.endermechanics.tile.generator;

import java.util.List;

import net.minecraft.item.ItemPotion;
import net.minecraft.potion.PotionEffect;

public class TileGeneratorPotion extends TileGenerator{
	public TileGeneratorPotion(){super(50, 50000);}
	@Override
	protected int consumeFuel(boolean execute){
		if(inv[0]==null) return 0;
		if(!(inv[0].getItem() instanceof ItemPotion)) return 0;
		ItemPotion potion = (ItemPotion)inv[0].getItem();
		List<PotionEffect> effects = (List<PotionEffect>)potion.getEffects(inv[0]);
		int output = 0;//Power/t this potion can generate
		int time = 0;//Ticks that this potion can last
		for(PotionEffect effect : effects){
			output += 25 * (effect.getAmplifier()+1);
			time = Math.max(time, effect.getDuration()/2);
		}
		if(execute){
			powerOutput = output;
			inv[0]=null;
		}
		return time;
	}
}

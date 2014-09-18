package com.endreman0.endermechanics.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileGeneratorEM extends TileFunctionalEM{
	protected int ticksRunning;//Increased whenever a fuel is consumed, decremented every tick
	protected int powerOutput;
	public TileGeneratorEM(int output, int maxEnergy){
		super(maxEnergy);
		ticksRunning = 0;
		powerOutput = output;
	}
	@Override protected int getInvSlots(){return 1;}
	/**
	 * hasFuel() and run() in one.
	 * @param execute Whether to consume fuel or not
	 * @return The ticks to run with the fuel (potentially) consumed
	 */
	protected abstract int consumeFuel(boolean execute);
	protected boolean canRun(){
		if(insert(ForgeDirection.UNKNOWN, powerOutput, false)!=powerOutput) return false;//If there's no room for power...
		if(consumeFuel(false)==0 && ticksRunning==0) return false; //...or there's no fuel and no power left to make, stop running
		return true;
	}
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(canRun()){
			if(ticksRunning>0){
				insert(ForgeDirection.UNKNOWN, powerOutput, true);
				ticksRunning--;
			}else if(consumeFuel(false)>0){//If there is fuel to be burned,
				ticksRunning = consumeFuel(true);//Burn it.
			}
		}
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		power = nbt.getInteger("power");
		ticksRunning = nbt.getInteger("ticksRunning");
		powerOutput = nbt.getInteger("powerOutput");
	}
	@Override public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setInteger("power", power);
		nbt.setInteger("ticksRunning", ticksRunning);
		nbt.setInteger("powerOutput", powerOutput);
	}
}

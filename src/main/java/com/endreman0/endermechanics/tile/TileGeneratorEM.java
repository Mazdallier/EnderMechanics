package com.endreman0.endermechanics.tile;

import com.endreman0.endermechanics.util.LogHelper;
import com.endreman0.endermechanics.util.Utility;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileGeneratorEM extends TileFunctionalEM{
	protected int ticksRunning;//Increased whenever a fuel is consumed, decremented every tick
	protected int powerOutput;//Updated whenever a fuel is consumed
	public TileGeneratorEM(){
		super();
		ticksRunning = 0;
		powerOutput = 0;
	}
	@Override protected int getInvSlots(){return 1;}
	protected abstract int fuelTicks();
	protected boolean hasFuel(){return inv[0]!=null;}//This method allows IFluidHandlers to keep running when there's no items inside.
	protected abstract int powerOutput();
	protected boolean canRun(){
		if(insert(ForgeDirection.UNKNOWN, powerOutput, false)!=powerOutput) return false;//If there's no room for power...
		if(!hasFuel() && ticksRunning==0) return false; //...or there's no fuel and no power left to get, stop running
		return true;
	}
	@Override
	public void updateEntity(){
		if(canRun()){
			if(ticksRunning>0){
				insert(ForgeDirection.UNKNOWN, powerOutput, true);
				ticksRunning--;
			}else if(fuelTicks()>0){
				ticksRunning = fuelTicks();
				powerOutput = powerOutput();
				decrStackSize(0, 1);//Decrement inventory amount by 1
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

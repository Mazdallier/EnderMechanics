package com.endreman0.endermechanics.tile;

import com.endreman0.endermechanics.api.IPowerHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileGenerator extends TileInventory{
	protected int ticksRunning;//Increased whenever a fuel is consumed, decremented every tick
	protected int powerOutput;
	public TileGenerator(int output, int maxEnergy){
		super(1, maxEnergy);
		ticksRunning = 0;
		powerOutput = output;
	}
	/**
	 * hasFuel() and run() in one method.
	 * Similar to IFluidHandler's fill and drain methods, with doFill and doDrain respectively.
	 * If the boolean is true, one unit of fuel should be consumed.
	 * Whether or not the fuel is consumed, return the number of ticks this generator can run for with that fuel.
	 * @param execute Whether to consume fuel or not
	 * @return The ticks to run with the fuel (potentially) consumed
	 */
	protected abstract int consumeFuel(boolean execute);
	/**
	 * Whether or not the tile is capable of generating power this tick.<br/>
	 * Examples are a multi-block checking that it is correctly formed, and something like a GregTech Fusion Reactor (which requires
	 * power to start up) making sure it has enough power.
	 * @return true if everything is good to go.
	 */
	protected boolean canRun(){return true;}
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(canRun() && power+powerOutput<=maxPower){
			if(ticksRunning>0){
				insert(ForgeDirection.UNKNOWN, powerOutput, true);
				ticksRunning--;
			}else if(consumeFuel(false)>0){//If there is fuel to be burned,
				ticksRunning = consumeFuel(true);//Burn it.
			}
			markDirty();
		}
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			TileEntity tile = worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ);
			if(tile instanceof IPowerHandler && ((IPowerHandler)tile).canInsert(dir.getOpposite()) && canExtract(dir))
				((IPowerHandler)tile).insert(dir.getOpposite(), extract(dir, 1000, true), true);
		}
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		ticksRunning = nbt.getInteger("ticksRunning");
		powerOutput = nbt.getInteger("powerOutput");
	}
	@Override public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setInteger("ticksRunning", ticksRunning);
		nbt.setInteger("powerOutput", powerOutput);
	}
	@Override public boolean canInsert(ForgeDirection from){return from.equals(ForgeDirection.UNKNOWN);}
}

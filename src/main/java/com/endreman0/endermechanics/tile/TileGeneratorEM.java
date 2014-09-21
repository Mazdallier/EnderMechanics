package com.endreman0.endermechanics.tile;

import com.endreman0.endermechanics.util.IPowerHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileGeneratorEM extends TileFunctionalEM{
	protected int ticksRunning;//Increased whenever a fuel is consumed, decremented every tick
	protected int powerOutput;
	public TileGeneratorEM(int output, int maxEnergy){
		super(maxEnergy, 1);
		ticksRunning = 0;
		powerOutput = output;
	}
	/**
	 * hasFuel() and run() in one method.
	 * Similar to IFluidHandler's fill and drain methods, with doFill and doDrain respectively.
	 * If the boolean is true, one fuel should be consumed.
	 * Whether or not the fuel is consumed, return the number of ticks this generator can run for with that fuel.
	 * @param execute Whether to consume fuel or not
	 * @return The ticks to run with the fuel (potentially) consumed
	 */
	protected abstract int consumeFuel(boolean execute);
	/**
	 * Whether or not the tile is capable of generating power this tick.
	 * Multi-blocks would check here if they have a correctly formed structure.
	 * If a generator needs some power in it to start generating, like a GregTech Fusion Reactor, check for that here too.
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
		for(int i=0;i<6;i++){
			ForgeDirection dir = ForgeDirection.getOrientation(i);
			if(worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ) instanceof IPowerHandler){
				IPowerHandler machine = (IPowerHandler)worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ);
				if(machine.canInsert(dir.getOpposite())){
					power-=machine.insert(dir.getOpposite(), Math.min(power, 1000), true);
				}
			}
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
}

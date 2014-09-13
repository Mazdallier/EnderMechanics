package com.endreman0.endermechanics.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileGeneratorFurnace extends TileMachineEM implements IFluidHandler{
	public FluidTank lavaTank = new FluidTank(10000);//capacity in mB
	protected int getInvSlots(){return 1;}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		
		int amt = nbt.getInteger("tankAmount");
		if(amt>0){
			lavaTank.setFluid(new FluidStack(2, amt));
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		
		if(lavaTank.getFluid()!=null){
			nbt.setInteger("tankAmount", lavaTank.getFluid().amount);
		}
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill){
		if(!resource.getFluid().equals(FluidRegistry.LAVA)){return 0;}
		return lavaTank.fill(resource, doFill);
	}
	
	@Override public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain){return new FluidStack(0,0);}//Don't allow draining
	@Override public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain){return new FluidStack(0,0);}
	@Override public boolean canFill(ForgeDirection from, Fluid fluid){return fluid.equals(FluidRegistry.LAVA);}
	@Override public boolean canDrain(ForgeDirection from, Fluid fluid){return false;}
	@Override public FluidTankInfo[] getTankInfo(ForgeDirection from){return new FluidTankInfo[]{new FluidTankInfo(lavaTank)};}
}

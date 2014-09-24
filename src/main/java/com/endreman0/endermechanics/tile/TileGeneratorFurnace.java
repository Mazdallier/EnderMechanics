package com.endreman0.endermechanics.tile;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileGeneratorFurnace extends TileGenerator implements IFluidHandler{
	public FluidTank lavaTank = new FluidTank(10000);//capacity in mB
	public TileGeneratorFurnace(){super(10, 10000);}
	
	@Override
	protected int consumeFuel(boolean execute){
		boolean useItem = true;
		int power = 0;
		if(inv[0]!=null){//If there are items to burn, 
			power=TileEntityFurnace.getItemBurnTime(inv[0])/2;
			if(execute) inv[0].stackSize--;//Use them.
			if(inv[0].stackSize==0) inv[0] = null;
		}else if(lavaTank.getFluid()!=null){//If there are no items but there is fluid, use it.
			power=10;//10t/mB means one bucket lasts 10000t, producing 100kE. This is enough to smelt 100 items, just as a lava bucket in a furnace does.
			if(execute) lavaTank.getFluid().amount--;//Use some fluid.
		}
		return power;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		int amt = nbt.getInteger("tankAmount");
		if(amt>0) lavaTank.setFluid(new FluidStack(2, amt));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		if(lavaTank.getFluid()!=null) nbt.setInteger("tankAmount", lavaTank.getFluid().amount);
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill){
		if(!resource.getFluid().equals(FluidRegistry.LAVA)){return 0;}
		return lavaTank.fill(resource, doFill);
	}
	
	@Override public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain){return null;}//Don't allow draining
	@Override public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain){return null;}
	@Override public boolean canFill(ForgeDirection from, Fluid fluid){return fluid.equals(FluidRegistry.LAVA);}
	@Override public boolean canDrain(ForgeDirection from, Fluid fluid){return false;}
	@Override public FluidTankInfo[] getTankInfo(ForgeDirection from){return new FluidTankInfo[]{new FluidTankInfo(lavaTank)};}
}

package com.endreman0.endermechanics.api;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * An interface for tile entities that accept Endergy.
 * Setup very similar to IFluidHandler, but with power. Also, there's no PowerBufferInfo() class.
 * @author endreman0
 */
public interface IPowerHandler{
	/**
	 * Insert power into the block.
	 * @param from Direction that the power is coming from
	 * @param amount Amount of power going in
	 * @param actual Same as doFill; if false, it's just a test
	 * @return The amount of power that went in
	 */
	int insert(ForgeDirection from, int amount, boolean actual);
	
	/**
	 * Extract power from the block.
	 * @param from Direction the power is going to
	 * @param amount Maximum amount of power to draw
	 * @param actual Same as doDrain; if false, it's just a test
	 * @return
	 */
	int extract(ForgeDirection from, int amount, boolean actual);
	//IFluidHandler has a second drain method (second arg is an int instead of a FluidStack), but it's unnecessary for power because there is no PowerStack.
	
	/**
	 * Whether or not power can be inserted from the defined side.
	 * @param from Direction in question
	 * @return Whether the power can be inserted
	 */
	boolean canInsert(ForgeDirection from);
	
	/**
	 * Whether or not power can be extracted from the defined side.
	 * @param from Direction in question
	 * @return Whether or not the power can be extracted
	 */
	boolean canExtract(ForgeDirection from);
	
	//Next is FluidTankInfo[] getTankInfo(ForgeDirection), but I'm not going to create a PowerBufferInfo class. There is also only one buffer, so no need of an array.
	/**
	 * Get the current power in the tile.
	 * @param from Direction that is being detected from
	 * @return The amount of power in its storage
	 */
	int getPower(ForgeDirection from);
	
	/**
	 * Get the maximum amount of power the tile can hold.
	 * @param from Direction that is being detected from
	 * @return The max buffer of the power storage
	 */
	int getMaxPower(ForgeDirection from);
}

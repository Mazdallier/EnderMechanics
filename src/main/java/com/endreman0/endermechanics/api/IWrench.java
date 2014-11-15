package com.endreman0.endermechanics.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * The interface required for standard wrenches.
 * While it is not required for every wrench to implement this, using this interface will adopt
 * the default behavior automatically. The default behavior is to "precision break" the block
 * being clicked on a shift-click or shift-right-click, depending on the config.
 * @author endreman0
 *
 */
public interface IWrench{
	/**
	 * Fired whenever the wrench is used correctly.
	 * This means that the block at the given coordinates (the target of the hit) does implement
	 * {@link com.endreman0.endermechanics.api.IWrenchBreakable IWrenchBreakable} and that it's
	 * {@link com.endreman0.endermechanics.api.IWrenchBreakable#getTier() tier} is less than or
	 * equal to the tier returned by this wrench.
	 * The primary use for this method would be to decrement power.
	 * NOTE: IF THE METHOD RETURNS FALSE, {@link #onInvalidUse(EntityPlayer, int, int, int) onInvalidUse()} WILL BE CALLED.
	 * @param player The player using the wrench
	 * @param x The x-coordinate of the block that is being broken
	 * @param y The y-coordinate of the block that is being broken
	 * @param z The z-coordinate of the block that is being broken
	 * @return Whether or not the break was successful. IF NOT, DO NOT CHANGE ANYTHING.
	 */
	public boolean onValidUse(EntityPlayer player, int x, int y, int z);
	/**
	 * Fired whenever the wrench is used incorrectly.
	 * This means the block at the given coordinates is not an
	 * {@link com.endreman0.endermechanics.api.IWrenchBreakable IWrenchBreakable}, it's
	 * {@link com.endreman0.endermechanics.api.IWrenchBreakable#getTier() tier} is greater than
	 * this wrench's, or {@link #onValidUse(EntityPlayer, int, int, int) onValidUse()} already
	 * returned false.
	 * @param player The player using the wrench
	 * @param x The x-coordinate of the block that is being clicked
	 * @param y The y-coordinate of the block that is being clicked
	 * @param z The z-coordinate of the block that is being clicked
	 */
	public void onInvalidUse(EntityPlayer player, int x, int y, int z);
	/**
	 * Get the tier of the wrench.
	 * This will be compared to the tier of the block being clicked.
	 * If the wrench's tier is greater than or equal to the tier of the block, the hit is
	 * considered valid.
	 * @param stack The ItemStack of the wrench
	 * @return the tier of the wrench represented by the given item
	 */
	public int getTier(ItemStack stack);
}

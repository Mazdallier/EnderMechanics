package com.endreman0.endermechanics.api;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

/**
 * Implement on any tile entities that fit in a Machine Frame. Any methods will be run automatically by the frame.
 * @author endreman0
 */
public interface IFramableMachine extends ISidedInventory, IPowerHandler{
	public ItemStack getRenderedStack();
	public void setInFrame(boolean inFrame);
	public boolean isActive();
	public long ticks();
}

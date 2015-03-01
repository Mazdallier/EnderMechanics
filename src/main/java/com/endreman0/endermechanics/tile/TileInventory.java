package com.endreman0.endermechanics.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;

import com.endreman0.endermechanics.api.IPowerHandler;
import com.endreman0.endermechanics.util.Utility;

public abstract class TileInventory extends TileEM implements ISidedInventory, IPowerHandler{
	protected ItemStack[] inv;
	protected int power;
	protected int maxPower;
	protected long ticks;
	protected boolean inFrame = false;
	
	public TileInventory(int invSlots, int maxPower){
		inv = new ItemStack[invSlots];
		this.power=0;
		this.maxPower = maxPower;
		ticks=0;
	}
	/**
	 * Called every tick in updateEntity().
	 * Instead of overriding updateEntity, use this method.
	 * None of my abstract machine templates override this, so it's safe to not call super.tick().
	 * Do whatever you want.
	 */
	protected void tick(){}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		tick();
		ticks++;
	}
	public long ticks(){return ticks;}
	
	//NBT and packets
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		power = nbt.getInteger("power");
		NBTTagList list = nbt.getTagList("Inventory", 10);
		for(int i=0;i<list.tagCount();i++){
			NBTTagCompound tag = list.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if(slot >= 0 && slot < inv.length){
				inv[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setInteger("power", power);
		NBTTagList list = new NBTTagList();
		for(int i=0; i<inv.length; i++){
			ItemStack stack = inv[i];
			if(stack!=null){
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte)i);
				stack.writeToNBT(tag);
				list.appendTag(tag);
			}
		}
		nbt.setTag("Inventory", list);
	}
	//IInventory
	@Override
	public ItemStack decrStackSize(int slot, int amount){
		ItemStack stack = getStackInSlot(slot);
		if(stack!=null){
			if(stack.stackSize <= amount){
				setInventorySlotContents(slot, null);
			}else{
				stack = stack.splitStack(amount);
				if(stack.stackSize==0){
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot){
		ItemStack stack = getStackInSlot(slot);
		if(stack!=null){
			setInventorySlotContents(slot, null);
		}
		return stack;
	}
	
	@Override public int getSizeInventory(){return inv.length;}
	@Override public ItemStack getStackInSlot(int slot){return inv[slot];}
	@Override public void setInventorySlotContents(int slot, ItemStack stack){inv[slot]=stack;}
	@Override public String getInventoryName(){return getBlockType().getUnlocalizedName();}//Use block name as GUI name
	@Override public boolean hasCustomInventoryName(){return false;}
	@Override public int getInventoryStackLimit(){return 64;}
	@Override public boolean isUseableByPlayer(EntityPlayer player){return player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;}
	@Override public void openInventory(){}
	@Override public void closeInventory(){}
	@Override public boolean isItemValidForSlot(int slot, ItemStack stack){return true;}
	
	//IPowerHandler
	@Override
	public int insert(ForgeDirection from, int amount, boolean actual){
		if(amount<0) return extract(from, -amount, actual);
		if(!canInsert(from)) return 0;
		int amt = Math.min(amount, getMaxPower(from)-getPower(from));
		if(actual) power+=amt;
		return amt;
	}
	@Override
	public int extract(ForgeDirection from, int amount, boolean actual){
		if(amount<0) return insert(from, -amount, actual);
		if(!canExtract(from)) return 0;
		int amt = Math.min(amount, getPower(from));
		if(actual) power-=amt;
		return amt;
	}
	@Override public boolean canInsert(ForgeDirection from){return true;}
	@Override public boolean canExtract(ForgeDirection from){return true;}
	@Override public int getPower(ForgeDirection from){return power;}
	@Override public int getMaxPower(ForgeDirection from){return maxPower;}
	@Override public int[] getAccessibleSlotsFromSide(int side){return null;}
	@Override public boolean canInsertItem(int slot, ItemStack stack, int side){return inFrame && isItemValidForSlot(slot, stack);}
	@Override public boolean canExtractItem(int slot, ItemStack stack, int side){return inFrame && Utility.canConsume(inv[slot], stack);}
}

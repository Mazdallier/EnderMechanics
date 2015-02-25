package com.endreman0.endermechanics.util;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

import com.endreman0.endermechanics.tile.TileRiftNode;

public class NodeNetwork{
	protected ItemStack[] inv = new ItemStack[10];
	protected int power = 0;
	protected int maxPower = 10000;
	protected List<TileRiftNode> nodes;
	public NodeNetwork(TileRiftNode node){
		nodes = new LinkedList<TileRiftNode>();
		addNode(node);
	}
	public void addNode(TileRiftNode node){if(!nodes.contains(node)){nodes.add(node); node.markDirty();}}
	public void removeNode(TileRiftNode node){nodes.remove(node); node.markDirty();}
	public int nodes(){return nodes.size();}
	public TileRiftNode getMaster(){return nodes.get(0);}
	
	//IInventory
	public int getSizeInventory(){return inv.length;}
	public ItemStack getStackInSlot(int slot){return inv[slot];}
	public void setInventorySlotContents(int slot, ItemStack stack){inv[slot]=stack;}
	public int getInventoryStackLimit(){return 64;}
	public boolean isItemValidForSlot(int slot, ItemStack stack){return true;}
	
	//IPowerHandler
	public int insert(ForgeDirection from, int amount, boolean actual){
		if(amount<0) return extract(from, -amount, actual);
		if(!canInsert(from)) return 0;
		int amt = Math.min(amount, getMaxPower(from)-power);
		if(actual) power+=amt;
		return amt;
	}
	public int extract(ForgeDirection from, int amount, boolean actual){
		if(amount<0) return insert(from, -amount, actual);
		if(!canExtract(from)) return 0;
		int amt = Math.min(amount, power);
		if(actual) power-=amt;
		return amt;
	}
	public boolean canInsert(ForgeDirection from){return true;}
	public boolean canExtract(ForgeDirection from){return true;}
	public int getPower(ForgeDirection from){return power;}
	public int getMaxPower(ForgeDirection from){return maxPower;}
}

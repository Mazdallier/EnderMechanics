package com.endreman0.endermechanics.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.minecraftforge.common.util.ForgeDirection;

import com.endreman0.endermechanics.tile.TileEnderNode;

public class EnderNodeNetwork implements IPowerHandler{
	int power = 0;
	int maxPower = 10000;
	private List<TileEnderNode> nodes;
	public EnderNodeNetwork(TileEnderNode node){
		nodes = new LinkedList<TileEnderNode>();
		addNode(node);
	}
	public void addNode(TileEnderNode node){if(!nodes.contains(node)) nodes.add(node);}
	public void removeNode(TileEnderNode node){if(!nodes.contains(node)) nodes.remove(node);}
	public int nodes(){return nodes.size();}
	public TileEnderNode getMaster(){return nodes.get(0);}
	
	//IPowerHandler
	@Override
	public int insert(ForgeDirection from, int amount, boolean actual) {
		int amt = Math.min(amount, getMaxPower(from)-power);
		if(actual) power+=amt;
		return amt;
	}
	@Override
	public int extract(ForgeDirection from, int amount, boolean actual) {
		int amt = Math.min(amount, power);
		if(actual) power-=amt;
		return amt;
	}
	@Override public boolean canInsert(ForgeDirection from){return true;}
	@Override public boolean canExtract(ForgeDirection from){return true;}
	@Override
	public int getPower(ForgeDirection from){
		int netPower = power;
		Iterator<TileEnderNode> iterator = nodes.iterator();
		while(iterator.hasNext()){
			netPower+=iterator.next().getThisPower(from);
		}
		return netPower;
	}
	@Override
	public int getMaxPower(ForgeDirection from) {
		int netPower = maxPower;
		Iterator<TileEnderNode> iterator = nodes.iterator();
		while(iterator.hasNext()){
			netPower+=iterator.next().getThisMaxPower(from);
		}
		return netPower;
	}
}

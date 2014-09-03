package com.endreman0.endermechanics.tile;

import java.util.LinkedList;
import java.util.List;

public class EnderNodeNetwork{
	private List<TileEntityEnderNode> nodes;
	public EnderNodeNetwork(TileEntityEnderNode node){
		nodes = new LinkedList<TileEntityEnderNode>();
		addNode(node);
	}
	public void addNode(TileEntityEnderNode node){
		if(node!=null){
			nodes.add(node);
		}
	}
	public void removeNode(TileEntityEnderNode node){
		if(node!=null && nodes.contains(node)){
			nodes.remove(node);
		}
	}
	public int nodes(){
		return nodes.size();
	}
}

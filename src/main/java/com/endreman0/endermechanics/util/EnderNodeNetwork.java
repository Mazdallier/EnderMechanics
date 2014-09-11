package com.endreman0.endermechanics.util;

import java.util.LinkedList;
import java.util.List;

import com.endreman0.endermechanics.tile.TileEnderNode;

public class EnderNodeNetwork{
	private List<TileEnderNode> nodes;
	public EnderNodeNetwork(TileEnderNode node){
		nodes = new LinkedList<TileEnderNode>();
		addNode(node);
	}
	public void addNode(TileEnderNode node){
		if(node!=null){
			nodes.add(node);
		}
	}
	public void removeNode(TileEnderNode node){
		if(node!=null && nodes.contains(node)){
			nodes.remove(node);
		}
	}
	public int nodes(){
		return nodes.size();
	}
}

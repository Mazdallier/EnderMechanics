package com.endreman0.endermechanics.tile;

import com.endreman0.endermechanics.block.BlockEnderNode;
import com.endreman0.endermechanics.util.EnderNodeNetwork;
import com.endreman0.endermechanics.util.Utility;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEnderNode extends TileEntity{
	public EnderNodeNetwork network;
	public long renderTicks;
	public byte ticks;
	private int range = Utility.nodeRange;
	private int cycle = Utility.nodeUpdate;
	private int scan = 2*range + 1;//Scan time; 1 tick per layer. One layer for the layer this is in, "range" layers above, and "range" layers below.
	public TileEnderNode(){
		super();
		renderTicks=0;//This constantly goes up.
		ticks = 0;//This wraps to the cycle length.
	}
	//TODO save the master's coordinates, and make it look there for a network before looking elsewhere.
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(ticks<scan && (network==null || network.nodes()<=1)){
			scanNetwork(ticks);
		}
		renderTicks++;
		ticks = (byte)(renderTicks % cycle);
	}
	public void scanNetwork(int layer){
		if(worldObj!=null){
			scan: for(int i=-range;i<=range;i++){
				for(int j=-range;j<=range;j++){
					TileEntity tile = worldObj.getTileEntity(xCoord+i, yCoord+(layer-range), zCoord+j);
					if(tile instanceof TileEnderNode && tile!=this && ((TileEnderNode)tile).network!=null){
						this.network = ((TileEnderNode)tile).network;
						network.addNode(this);
						break scan;
					}
				}
			}
		}
		if(this.network==null && layer==scan-1){//Last layer, and still nothing
			this.network = new EnderNodeNetwork(this);
		}
	}
	
	//TODO figure out why the network is always null on server side.
	public void breakBlock(){
		if(network!=null){
			network.removeNode(this);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		if(network==null) return;
		TileEnderNode master = network.getMaster();
		if(master==null){
			nbt.setInteger("masterX", xCoord);
			nbt.setInteger("masterY", yCoord);
			nbt.setInteger("masterZ", zCoord);
		}else{
			nbt.setInteger("masterX", master.xCoord);
			nbt.setInteger("masterY", master.yCoord);
			nbt.setInteger("masterZ", master.zCoord);
		}
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		TileEntity tile = worldObj.getTileEntity(nbt.getInteger("masterX"), nbt.getInteger("masterY"), nbt.getInteger("masterZ"));
		if(tile instanceof TileEnderNode && tile!=this){
			network = ((TileEnderNode)tile).network;
		}else{
			network = new EnderNodeNetwork(this);
		}
	}
	@Override
	public Packet getDescriptionPacket(){
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}
	@Override public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet){readFromNBT(packet.func_148857_g());}
}

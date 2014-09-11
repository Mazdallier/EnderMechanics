package com.endreman0.endermechanics.tile;

import com.endreman0.endermechanics.block.BlockEnderNode;
import com.endreman0.endermechanics.util.EnderNodeNetwork;
import com.endreman0.endermechanics.util.LogHelper;
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
		boolean done = false;
		if(worldObj!=null){
			for(int i=-range;i<=range;i++){
				for(int j=-range;j<=range;j++){
					TileEntity tile = worldObj.getTileEntity(xCoord+i, yCoord+(layer-range), zCoord+j);
					if(tile instanceof TileEnderNode && tile!=this && ((TileEnderNode)tile).network!=null){
						this.network = ((TileEnderNode)tile).network;
						network.addNode(this);
						done = true;
						break;
					}
					if(done){break;}
				}
				if(done){break;}
			}
		}else{
			LogHelper.info("Null World");
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
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
	}
	@Override
	public Packet getDescriptionPacket(){
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}
	@Override public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet){readFromNBT(packet.func_148857_g());}
}

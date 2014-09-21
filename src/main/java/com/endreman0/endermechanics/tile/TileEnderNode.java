package com.endreman0.endermechanics.tile;

import com.endreman0.endermechanics.util.EnderNodeNetwork;
import com.endreman0.endermechanics.util.Utility;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEnderNode extends TileEM{
	public EnderNodeNetwork network;
	public long renderTicks;
	public byte ticks;
	private int range = Utility.nodeRange;
	private int cycle = Utility.nodeUpdate;
	private int scan = 2*range + 1;//Scan time; 1 tick per layer. One layer for the layer this is in, "range" layers above, and "range" layers below.
	private int masterX, masterY, masterZ;
	public TileEnderNode(){
		super(1000);
		renderTicks=0;//This constantly goes up.
		ticks = 0;//This wraps to the cycle length.
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(ticks<scan && (network==null || network.nodes()<=1)) scanNetwork(ticks);//If it's in the scan cycle and the network is incomplete, scan.
		if(!(worldObj.getTileEntity(masterX, masterY, masterZ) instanceof TileEnderNode)) createFromTile(null);//If the master is broken, reset.
		renderTicks++;
		ticks = (byte)(renderTicks % cycle);
	}
	public void scanNetwork(int layer){
		scan: for(int i=-range;i<=range;i++){
			for(int j=-range;j<=range;j++){
				TileEntity tile = worldObj.getTileEntity(xCoord+i, yCoord+(layer-range), zCoord+j);
				if(tile instanceof TileEnderNode && !tile.equals(this)){
					createFromTile((TileEnderNode)tile);
					network.addNode(this);
					break scan;
				}
			}
		}
		if(this.network==null && layer==scan-1){//Last layer, and still nothing
			createFromTile(this);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setInteger("masterX", masterX);
		nbt.setInteger("masterY", masterY);
		nbt.setInteger("masterZ", masterZ);
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		if(network!=null) return;
		createFromTile((TileEnderNode)worldObj.getTileEntity(nbt.getInteger("masterX"), nbt.getInteger("masterY"), nbt.getInteger("masterZ")));
		if(network!=null) network.addNode(this);
	}
	@Override
	public void invalidate(){
		super.invalidate();
		if(network!=null) network.removeNode(this);
	}
	public EnderNodeNetwork getNetworkFromMaster(){
		if(network==null) network = new EnderNodeNetwork(this);
		return network;
	}
	private void createFromTile(TileEnderNode tile){
		if(tile==null){
			masterX = xCoord; masterY = yCoord; masterZ = zCoord;
			network = null;
		}else{
			masterX = tile.xCoord; masterY = tile.yCoord; masterZ = tile.zCoord;
			network = tile.getNetworkFromMaster();
		}
		markDirty();
	}
	public int getThisPower(ForgeDirection from){return power;}
	public int getThisMaxPower(ForgeDirection from){return maxPower;}
	@Override public int insert(ForgeDirection from, int amount, boolean actual){return network!=null ? network.insert(from, amount, actual) : 0;}
	@Override public int extract(ForgeDirection from, int amount, boolean actual){return network!=null ? network.extract(from, amount, actual) : 0;}
	@Override public boolean canInsert(ForgeDirection from){return network!=null ? network.canInsert(from) : false;}
	@Override public boolean canExtract(ForgeDirection from){return network!=null ? network.canExtract(from) : false;}
	@Override public int getPower(ForgeDirection from){return network!=null ? network.getPower(from) : 0;}
	@Override public int getMaxPower(ForgeDirection from){return network!=null ? network.getMaxPower(from) : 0;}
}

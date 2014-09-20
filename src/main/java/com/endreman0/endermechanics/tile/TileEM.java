package com.endreman0.endermechanics.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.endreman0.endermechanics.util.IPowerHandler;
import com.endreman0.endermechanics.util.IWrenchBreakable;

public abstract class TileEM extends TileEntity implements IPowerHandler, IWrenchBreakable{
	protected int power;
	protected int maxPower;
	
	public TileEM(int maxPower){
		this.power=0;
		this.maxPower = maxPower;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		power = nbt.getInteger("power");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setInteger("power", power);
	}
	
	@Override
	public Packet getDescriptionPacket(){
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}
	
	@Override public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet){readFromNBT(packet.func_148857_g());}//func_148857_g gets NBT from packet
	
	@Override
	public void breakWithWrench(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
	}
	@Override
	public int insert(ForgeDirection from, int amount, boolean actual){
		int amt = Math.min(amount, getMaxPower(from)-power);
		if(canInsert(from)){
			if(actual) power+=amt;
			return amt;
		}
		return 0;
	}
	@Override
	public int extract(ForgeDirection from, int amount, boolean actual){
		int amt = Math.min(amount, power);
		if(canExtract(from)){
			if(actual) power-=amt;
			return amt;
		}
		return 0;
	}
	@Override public boolean canInsert(ForgeDirection from){return true;}
	@Override public boolean canExtract(ForgeDirection from){return true;}
	@Override public int getPower(ForgeDirection from){return power;}
	@Override public int getMaxPower(ForgeDirection from){return maxPower;}
}

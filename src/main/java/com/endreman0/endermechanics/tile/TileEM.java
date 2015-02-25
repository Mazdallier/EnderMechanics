package com.endreman0.endermechanics.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEM extends TileEntity{
	public TileEM(){super();}
	@Override
	public Packet getDescriptionPacket(){
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);//this.writetoNBT contains machine and buses, but super.writeToNBT does not.
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}
	@Override public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet){readFromNBT(packet.func_148857_g());}//Get NBT from packet
}

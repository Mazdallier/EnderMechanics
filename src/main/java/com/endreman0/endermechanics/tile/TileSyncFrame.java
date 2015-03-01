package com.endreman0.endermechanics.tile;

import com.endreman0.endermechanics.util.Log;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileSyncFrame extends TileEM{
	private ItemStack machine;
	private TileEntity tile;
	public byte[] buses = new byte[6];
	public boolean insertMachine(ItemStack machine){
		if(this.machine==null && machine!=null && machine.getItem() instanceof ItemBlock){
			Block block = Block.getBlockFromItem(machine.getItem());
			if(block.hasTileEntity(machine.getItemDamage())){
				tile = block.createTileEntity(worldObj, machine.getItemDamage());
				markDirty();
				return true;
			}
		}
		return false;
	}
	public TileEntity machine(){return tile;}
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		if(machine!=null) nbt.setTag("machine", machine.writeToNBT(new NBTTagCompound()));
		nbt.setByteArray("buses", buses);
		Log.info("Writing NBT to: " + nbt);
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		Log.info("Reading NBT from: " + nbt);
		if(nbt.hasKey("machine")) machine = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("machine"));
		if(tile==null && machine!=null) tile = Block.getBlockFromItem(machine.getItem()).createTileEntity(worldObj, machine.getItemDamage());
		buses = nbt.getByteArray("buses");
	}
}

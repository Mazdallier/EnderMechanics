package com.endreman0.endermechanics.multipart;

import java.util.ArrayList;
import java.util.List;

import com.endreman0.endermechanics.block.ModBlocks;
import com.endreman0.endermechanics.render.RenderMachineFrame;
import com.endreman0.endermechanics.tile.TileMachineFrame;
import com.endreman0.endermechanics.util.IWrenchBreakable;
import com.endreman0.endermechanics.util.Utility;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.minecraft.McMetaPart;
import codechicken.multipart.minecraft.PartMetaAccess;

public class PartMachineFrame extends McMetaPart implements IWrenchBreakable{
	RenderMachineFrame render = new RenderMachineFrame();
	//Tile Entity fields
	public byte machine;
	public byte[] buses = new byte[6];
	
	public PartMachineFrame() {
		super();
	}
	public PartMachineFrame(int meta){
		super(meta);
	}
	@Override public Cuboid6 getBounds(){return new Cuboid6(0.125, 0.125, 0.125, 0.875, 0.875, 0.875);}
	@Override public Block getBlock(){return ModBlocks.machineFrame;}
	@Override public String getType(){return Utility.RESOURCE_PREFIX + ":machineFrame";}
	
	@Override
	public void renderDynamic(Vector3 pos, float frame, int pass){
		super.renderDynamic(pos, frame, pass);
		render.renderTileEntityAt(world().getTileEntity((int)pos.x, (int)pos.y, (int)pos.z), pos.x, pos.y, pos.z, 0);
	}
	@Override
	public void invalidateConvertedTile(){
		super.invalidateConvertedTile();
		TileMachineFrame tile = (TileMachineFrame)world().getTileEntity(x(), y(), z());
		this.machine = tile.machine;
		this.buses = tile.buses;
	}
	@Override
	public void load(NBTTagCompound nbt){
		super.load(nbt);
		machine = nbt.getByte("machine");
		buses = nbt.getByteArray("buses");
	}
	@Override
	public void save(NBTTagCompound nbt){
		super.save(nbt);
		nbt.setByte("machine", machine);
		nbt.setByteArray("buses", buses);
	}
	@Override
	public void writeDesc(MCDataOutput packet){
		super.writeDesc(packet);
		packet.writeByte(machine);
		packet.writeByteArray(buses);
	}
	
	@Override
	public void readDesc(MCDataInput packet){
		super.readDesc(packet);
		machine = packet.readByte();
		buses = packet.readByteArray(6);
	}
	@Override
	public void breakWithWrench(World world, int x, int y, int z) {
		tile().dropItems(getBlock().getDrops(world, x, y, z, meta, 0));
		tile().remPart(this);
	}
}

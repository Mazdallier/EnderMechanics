package com.endreman0.endermechanics.multipart;

import com.endreman0.endermechanics.Utility;
import com.endreman0.endermechanics.block.ModBlocks;
import com.endreman0.endermechanics.render.RenderMachineFrame;
import com.endreman0.endermechanics.tile.TileEntityMachineFrame;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.nbt.NBTTagCompound;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.minecraft.McMetaPart;
import codechicken.multipart.minecraft.PartMetaAccess;

public class PartMachineFrame extends McMetaPart{
	RenderMachineFrame rend = new RenderMachineFrame();
	//Tile Entity fields
	public int machine;
	public int[] buses = new int[6];
	
	public PartMachineFrame() {
		super();
	}
	public PartMachineFrame(int meta){
		super(meta);
	}
	@Override
	public Cuboid6 getBounds() {
		return new Cuboid6(0.125F, 0.125F, 0.125F, 0.875F, 0.875F, 0.875F);
	}

	@Override
	public Block getBlock() {
		return ModBlocks.blockMachineFrame;
	}

	@Override
	public String getType() {
		return "endermech:blockMachineFrame";
	}
	@Override
	public void onWorldJoin(){
		super.onWorldJoin();
		rend.func_147497_a(TileEntityRendererDispatcher.instance);
	}
	@Override
	public void renderDynamic(Vector3 pos, float frame, int pass){
		rend.renderTileEntityAt(world().getTileEntity((int)pos.x, (int)pos.y, (int)pos.z), pos.x, pos.y, pos.z, 0);
	}
	@Override
	public void invalidateConvertedTile(){
		TileEntityMachineFrame tile = (TileEntityMachineFrame)world().getTileEntity(x(), y(), z());
		this.machine = tile.machine;
		this.buses = tile.buses;
	}
	@Override
	public void load(NBTTagCompound nbt){
		machine = nbt.getInteger("machine");
		buses = nbt.getIntArray("buses");
	}
	@Override
	public void save(NBTTagCompound nbt){
		nbt.setInteger("machine", machine);
		nbt.setIntArray("buses", buses);
	}
}

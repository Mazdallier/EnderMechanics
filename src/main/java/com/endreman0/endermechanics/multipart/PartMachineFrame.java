package com.endreman0.endermechanics.multipart;

import java.util.ArrayList;
import java.util.List;

import com.endreman0.endermechanics.IWrenchBreakable;
import com.endreman0.endermechanics.Utility;
import com.endreman0.endermechanics.block.ModBlocks;
import com.endreman0.endermechanics.render.RenderMachineFrame;
import com.endreman0.endermechanics.tile.TileEntityMachineFrame;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.minecraft.McMetaPart;
import codechicken.multipart.minecraft.PartMetaAccess;

public class PartMachineFrame extends McMetaPart implements IWrenchBreakable{
	RenderMachineFrame render = new RenderMachineFrame();
	//Tile Entity fields
	public int machine;
	public int[] buses = new int[6];
	
	public PartMachineFrame() {
		super();
	}
	public PartMachineFrame(int meta){
		super(meta);
	}
	@Override public Cuboid6 getBounds(){return new Cuboid6(0.125, 0.125, 0.125, 0.875, 0.875, 0.875);}

	@Override public Block getBlock(){return ModBlocks.machineFrame;}
	
	public void asdf(){
		
	}

	@Override public String getType(){return "endermechanics:machineFrame";}
	@Override
	public void onWorldJoin(){
		super.onWorldJoin();
		render.func_147497_a(TileEntityRendererDispatcher.instance);
	}
	@Override
	public void renderDynamic(Vector3 pos, float frame, int pass){
		render.renderTileEntityAt(world().getTileEntity((int)pos.x, (int)pos.y, (int)pos.z), pos.x, pos.y, pos.z, 0);
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
	@Override
	public void breakWithWrench(World world, int x, int y, int z) {
		tile().dropItems(super.getDrops());
		world.setBlockToAir(x, y, z);
	}
}

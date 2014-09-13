package com.endreman0.endermechanics.block;

import com.endreman0.endermechanics.EnderMechanics;
import com.endreman0.endermechanics.tile.TileGeneratorFurnace;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class BlockGeneratorFurnace extends BlockMachineEM{
	protected BlockGeneratorFurnace(){
		super(Material.iron, "generatorFurnace");
	}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float partialX, float partialY, float partialZ){
		TileGeneratorFurnace tile = (TileGeneratorFurnace)world.getTileEntity(x, y, z);
		if(tile == null || player.isSneaking()){
			return false;
		}
		FluidStack bucket = FluidContainerRegistry.getFluidForFilledItem(player.inventory.getCurrentItem());
		if(bucket!=null && bucket.getFluid().equals(FluidRegistry.LAVA) && tile.fill(ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.LAVA, 1000), false)==1000){
			tile.fill(ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.LAVA, 1000), true);
			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
			return true;
		}else{
			player.openGui(EnderMechanics.instance, 0, world, x, y, z);
			return true;
		}
	}
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TileGeneratorFurnace();}
}

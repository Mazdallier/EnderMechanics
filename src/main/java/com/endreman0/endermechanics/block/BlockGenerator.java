package com.endreman0.endermechanics.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.endreman0.endermechanics.EnderMechanics;
import com.endreman0.endermechanics.api.IWrenchBreakable;
import com.endreman0.endermechanics.tile.generator.*;
import com.endreman0.endermechanics.util.Utility;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGenerator extends MultiBlockEM{
	public BlockGenerator(){
		super(Material.iron, "generator");
		guiIndices = new int[]{0, 1, 2, 3, 4, -1};
		tiers = new int[]{0, 1, 2, 2, 3, Integer.MAX_VALUE};
	}
	public String[] names(){return new String[]{"generatorFurnace", "generatorLiving", "generatorTool", "generatorPotion",
			"generatorNetherStar", "generatorCreative"};}
	@Override public TileEntity createNewTileEntity(World world, int meta){
		switch(meta){
			case(0): return new TileGeneratorFurnace();
			case(1): return new TileGeneratorLiving();
			case(2): return new TileGeneratorTool();
			case(3): return new TileGeneratorPotion();
			case(4): return new TileGeneratorNetherStar();
			case(5): return new TileGeneratorCreative();
			default: return null;
		}
	}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float partialX, float partialY, float partialZ){
		if(!player.isSneaking() && world.getBlockMetadata(x, y, z)==0){
			TileGeneratorFurnace tile = (TileGeneratorFurnace)world.getTileEntity(x, y, z);
			FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(player.inventory.getCurrentItem());
			if(fluid!=null && fluid.getFluid().equals(FluidRegistry.LAVA) && tile.fill(ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.LAVA, 1000), false)==1000){
				tile.fill(ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.LAVA, 1000), true);
				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
				return true;
			}
		}
		return super.onBlockActivated(world, x, y, z, player, side, partialX, partialY, partialZ);
	}
}

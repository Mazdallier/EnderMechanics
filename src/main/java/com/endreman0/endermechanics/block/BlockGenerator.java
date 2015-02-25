package com.endreman0.endermechanics.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.endreman0.endermechanics.tile.generator.*;

public class BlockGenerator extends MultiBlockEM{
	public BlockGenerator(){
		super(Material.iron, "generator");
		guiIndices = new int[]{0, 1, 2, 3, 4, -1};
		tiers = new int[]{0, 1, 2, 2, 3, Integer.MAX_VALUE};
		setBlockBounds(0.125F, 0.125F, 0.125F, 0.875F, 0.875F, 0.875F);
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
	@Override public boolean isOpaqueCube(){return false;}
	@Override public boolean renderAsNormalBlock(){return false;}
	@Override public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side){return world.getBlockMetadata(x, y, z)==6;}
}

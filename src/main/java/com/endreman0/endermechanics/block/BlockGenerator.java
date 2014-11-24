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

public class BlockGenerator extends BlockEM implements ITileEntityProvider, IWrenchBreakable{
	public static final String[] names = {"Furnace", "Living", "Tool", "Potion", "NetherStar", "Creative"};
	private final int[] guiIndices = {0, 1, 2, 3, 4, -1};
	private int[] tiers = {0, 1, 2, 2, 3, Integer.MAX_VALUE};
	private IIcon[] icons = new IIcon[names.length];
	public BlockGenerator(){super(Material.iron, "generator");}
	@Override @SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register){
		String baseName = Utility.RESOURCE_PREFIX + ":" + getBasicName();
		for(int i=0;i<icons.length;i++) icons[i] = register.registerIcon(baseName + names[i]);
	}
	@Override public int damageDropped(int meta){return meta;}
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list){
		for(int i=0;i<icons.length;i++) list.add(new ItemStack(item, 1, i));
	}
	@Override @SideOnly(Side.CLIENT) public IIcon getIcon(int side, int meta){return icons[meta];}
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
		int meta = world.getBlockMetadata(x, y, z);
		if(player.isSneaking()) return false;
		
		//Metadata-specific activity. For example, Infernal Reactors will take any lava buckets they are right-clicked with.
		if(meta==0){
			TileGeneratorFurnace tile = (TileGeneratorFurnace)world.getTileEntity(x, y, z);
			FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(player.inventory.getCurrentItem());
			if(fluid!=null && fluid.getFluid().equals(FluidRegistry.LAVA) && tile.fill(ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.LAVA, 1000), false)==1000){
				tile.fill(ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.LAVA, 1000), true);
				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
				return true;
			}
		}
		
		if(guiIndices[meta]==-1) return false;
		player.openGui(EnderMechanics.instance, guiIndices[meta], world, x, y, z);
		return true;
	}
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta){
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}
	private void dropItems(World world, int x, int y, int z){
		IInventory inventory = (IInventory)world.getTileEntity(x, y, z);
		for(int i=0;i<inventory.getSizeInventory();i++){
			ItemStack stack = inventory.getStackInSlotOnClosing(i);//Returns item and zeroes out the slot
			if(stack!=null && stack.stackSize>0){
				EntityItem entity = new EntityItem(world, x, y, z, stack.copy());
				entity.motionY=0.2F;
				world.spawnEntityInWorld(entity);
			}
		}
	}
	@Override
	public void breakWithWrench(World world, int x, int y, int z) {
		dropBlockAsItem(world, x, y, z, new ItemStack(this.getItem(world, x, y, z), 1, world.getBlockMetadata(x, y, z)));
		dropItems(world, x, y, z);
		world.setBlockToAir(x, y, z);
	}
	@Override public int getTier(int meta){return tiers[meta];}
}

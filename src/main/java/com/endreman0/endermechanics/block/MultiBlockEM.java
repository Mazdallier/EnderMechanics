package com.endreman0.endermechanics.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.endreman0.endermechanics.EnderMechanics;
import com.endreman0.endermechanics.api.IWrenchBreakable;
import com.endreman0.endermechanics.util.Utility;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class MultiBlockEM extends BlockEM implements ITileEntityProvider, IWrenchBreakable{
	protected String[] names;
	protected int[] guiIndices, tiers;
	protected IIcon[] icons;
	public MultiBlockEM(Material material, String name){
		super(material, name);
		names = names();
		icons = new IIcon[names.length];
	}
	public abstract String[] names();
	@Override @SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register){
		String baseName = Utility.RESOURCE_PREFIX + ":";
		for(int i=0;i<icons.length;i++) icons[i] = register.registerIcon(baseName + names[i]);
	}
	@Override @SideOnly(Side.CLIENT) public IIcon getIcon(int side, int meta){return icons[meta];}
	@Override public int damageDropped(int meta){return meta;}
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list){
		for(int i=0;i<icons.length;i++) list.add(new ItemStack(item, 1, i));
	}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float partialX, float partialY, float partialZ){
		int meta = world.getBlockMetadata(x, y, z);
		if(player.isSneaking()) return false;
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

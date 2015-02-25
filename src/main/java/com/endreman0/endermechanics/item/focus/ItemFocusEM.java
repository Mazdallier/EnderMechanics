package com.endreman0.endermechanics.item.focus;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.endreman0.endermechanics.item.ItemMetadataEM;
import com.endreman0.endermechanics.tile.TileRiftNode;

public class ItemFocusEM extends ItemMetadataEM{
	public ItemFocusEM(){
		super("focus", 1);
		this.maxStackSize = 1;
	}
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float partialX, float partialY, float partialZ){
		TileEntity tile = player.worldObj.getTileEntity(x, y, z);
		if(tile instanceof TileRiftNode){
			TileRiftNode node = (TileRiftNode)tile;
			if(node.insertFocus(stack)){
				player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				return true;
			}
		}
		return false;
	}
}

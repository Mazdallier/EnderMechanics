package com.endreman0.endermechanics.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import com.endreman0.endermechanics.api.IWrench;
import com.endreman0.endermechanics.api.IWrenchBreakable;
import com.endreman0.endermechanics.util.Utility;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWrench extends ItemMetadataEM implements IWrench{
	ItemWrench(){
		super("wrench", 4);
	}
	@Override @SideOnly(Side.CLIENT) public boolean isFull3D(){return true;}//This makes it render in your hand, like a sword or tool.
	
	
	
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event){
		//Determine if it is a wrench being used to break a block.
		if(!event.entityPlayer.isSneaking()) return;
		if(!event.action.equals(Utility.wrenchKey ? event.action.LEFT_CLICK_BLOCK : event.action.RIGHT_CLICK_BLOCK)) return;
		ItemStack stack = event.entityPlayer.getHeldItem();
		if(stack==null) return;
		if(!(stack.getItem() instanceof IWrench)) return;
		IWrench wrench = (IWrench)stack.getItem();
		
		try{//If it is a wrench being shift-clicked, now determine whether the use is valid.
			Block block = event.world.getBlock(event.x, event.y, event.z);
			if(!(block instanceof IWrenchBreakable)) throw new Exception();
			IWrenchBreakable machine = (IWrenchBreakable)block;
			int meta = event.world.getBlockMetadata(event.x, event.y, event.z);
			if((machine).getTier(meta) > wrench.getTier(stack)) throw new Exception();
			if(!wrench.onValidUse(event.entityPlayer, event.x, event.y, event.z)) throw new Exception();
			//If we've reached this point, the use is valid, so break the block.
			machine.breakWithWrench(event.world, event.x, event.y, event.z);
		}catch(Exception ex){
			//If there is an exception thrown, it means that the use is invalid.
			wrench.onInvalidUse(event.entityPlayer, event.x, event.y, event.z);
		}
	}
	@Override public boolean onValidUse(EntityPlayer player, int x, int y, int z){return true;}
	@Override public void onInvalidUse(EntityPlayer player, int x, int y, int z){}
	@Override public int getTier(ItemStack stack){return stack.getItemDamage();}
}

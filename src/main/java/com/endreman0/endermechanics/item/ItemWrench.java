package com.endreman0.endermechanics.item;

import codechicken.multipart.TileMultipart;

import com.endreman0.endermechanics.IWrenchBreakable;
import com.endreman0.endermechanics.LogHelper;
import com.endreman0.endermechanics.block.ModBlocks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class ItemWrench extends ItemEM{
	public ItemWrench(){
		super();
		setUnlocalizedName("wrench");
	}
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event){
		LogHelper.info("PlayerInteractionEvent");
		if(event.action.equals(event.action.LEFT_CLICK_BLOCK)){
			LogHelper.info("Left Click Block");
			if(event.entityPlayer.inventory.getCurrentItem()!=null && event.entityPlayer.inventory.getCurrentItem().getItem().equals(ModItems.wrench)){
				LogHelper.info("Wrench");
				if(event.world.getBlock(event.x, event.y, event.z) instanceof IWrenchBreakable){
					LogHelper.info("Breaking");
					IWrenchBreakable machine = (IWrenchBreakable)event.world.getBlock(event.x, event.y, event.z);
					LogHelper.info(machine);
					machine.breakWithWrench(event.world, event.x, event.y, event.z);
				}
			}
		}
	}
}

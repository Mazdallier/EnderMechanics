package com.endreman0.endermechanics.item;

import net.minecraft.block.Block;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import com.endreman0.endermechanics.util.IWrenchBreakable;
import com.endreman0.endermechanics.util.LogHelper;
import com.endreman0.endermechanics.util.Utility;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWrench extends ItemMetadataEM{
	public ItemWrench(){
		super(4);
		setUnlocalizedName("wrench");
	}
	@Override @SideOnly(Side.CLIENT) public boolean isFull3D(){return true;}//This makes it render in your hand, like a sword or tool.
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event){
		if(event.action.equals(Utility.wrenchKey ? event.action.LEFT_CLICK_BLOCK : event.action.RIGHT_CLICK_BLOCK)){
			if(event.entityPlayer.inventory.getCurrentItem()!=null && event.entityPlayer.inventory.getCurrentItem().getItem().equals(ModItems.wrench)){
				if(event.entityPlayer.isSneaking()){
					Block block = event.world.getBlock(event.x, event.y, event.z);
					LogHelper.info("Wrench Shift-Clicking " + Utility.className(block).substring(5));//Cut off "Block" to return "Dirt", "GeneratorFurnace" etc.
					if(block instanceof IWrenchBreakable){
						IWrenchBreakable machine = (IWrenchBreakable)block;
						machine.breakWithWrench(event.world, event.x, event.y, event.z);
					}
				}
			}
		}
	}
}

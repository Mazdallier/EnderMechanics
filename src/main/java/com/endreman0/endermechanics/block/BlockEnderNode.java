package com.endreman0.endermechanics.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;

import com.endreman0.endermechanics.api.IWrenchBreakable;
import com.endreman0.endermechanics.item.ModItems;
import com.endreman0.endermechanics.tile.TileEnderNode;
import com.endreman0.endermechanics.util.Utility;

public class BlockEnderNode extends BlockEM implements ITileEntityProvider, IWrenchBreakable{
	protected BlockEnderNode() {
		super(Material.iron, "enderNode");
		setBlockBounds(0.125F, 0.125F, 0.125F, 0.875F, 0.875F, 0.875F);
	}
	@Override
	public void breakWithWrench(World world, int x, int y, int z) {
		dropBlockAsItem(world, x, y, z, new ItemStack(this.getItem(world, x, y, z)));
		world.setBlockToAir(x, y, z);
	}
	@Override public int getTier(int meta){return 1;}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float partialX, float partialY, float partialZ){
		super.onBlockActivated(world, x, y, z, player, meta, partialX, partialY, partialZ);
		if(world.isRemote) return false;
		if(player.getCurrentEquippedItem()!=null && player.getCurrentEquippedItem().getItem().equals(ModItems.wrench)){//Debug print when right-clicked with wrench
			TileEnderNode tile = (TileEnderNode)world.getTileEntity(x, y, z);
			Utility.addChat(player, "Power: " + Utility.powerString(tile.getNodePower()) + "/" + Utility.powerString(tile.getMaxPower(ForgeDirection.UNKNOWN)));
			if(tile.network()!=null)
				Utility.addChat(player, "Network: " + Utility.powerString(tile.getNetworkPower(ForgeDirection.UNKNOWN)) + " over " + tile.network().nodes() + " nodes");
			return true;
		}
		return false;
	}
	@Override public boolean isOpaqueCube(){return false;}
	@Override public boolean renderAsNormalBlock(){return false;}
	@Override public int getRenderType(){return -1;}
	@Override public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side){return false;}
	@Override public boolean hasTileEntity(int metadata){return true;}
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TileEnderNode();}
}

package com.endreman0.endermechanics.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.endreman0.endermechanics.api.IWrenchBreakable;
import com.endreman0.endermechanics.item.focus.ItemFocusEM;
import com.endreman0.endermechanics.tile.TileRiftNode;

public class BlockRiftNode extends BlockEM implements ITileEntityProvider, IWrenchBreakable{
	protected BlockRiftNode(){
		super(Material.iron, "riftNode");
		setBlockBounds(0.125F, 0.125F, 0.125F, 0.875F, 0.875F, 0.875F);
	}
	@Override
	public void breakWithWrench(World world, int x, int y, int z) {
		dropBlockAsItem(world, x, y, z, new ItemStack(this.getItem(world, x, y, z)));
		TileRiftNode tile = (TileRiftNode) world.getTileEntity(x, y, z);
		if(tile!=null && tile.focus()!=null) world.spawnEntityInWorld(new EntityItem(world, x, y, z, tile.focus()));
		world.setBlockToAir(x, y, z);
	}
	@Override public int getTier(int meta){return 1;}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float partialX, float partialY, float partialZ){
		super.onBlockActivated(world, x, y, z, player, meta, partialX, partialY, partialZ);
		if(world.isRemote) return false;
		if(player.getCurrentEquippedItem()!=null && player.getCurrentEquippedItem().getItem() instanceof ItemFocusEM){
			if(((TileRiftNode)world.getTileEntity(x, y, z)).insertFocus(player.getCurrentEquippedItem())){
				player.setCurrentItemOrArmor(0, null);//Clear the stack in hand
				return true;
			}
		}
		return false;
	}
	@Override public boolean isOpaqueCube(){return false;}
	@Override public boolean renderAsNormalBlock(){return false;}
	@Override public int getRenderType(){return -1;}
	@Override public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side){return false;}
	@Override public boolean hasTileEntity(int metadata){return true;}
	@Override public TileEntity createNewTileEntity(World world, int meta){return new TileRiftNode();}
}

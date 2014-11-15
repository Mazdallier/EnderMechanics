package com.endreman0.endermechanics.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.endreman0.endermechanics.api.IPowerHandler;

public class TileGeneratorCreative extends TileMachine{
	public TileGeneratorCreative(){super(1, 10000);}
	@Override
	public void tick(){
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
			if(tile instanceof IPowerHandler && ((IPowerHandler)tile).canInsert(dir.getOpposite())){
				IPowerHandler machine = (IPowerHandler)tile;
				machine.insert(dir.getOpposite(), machine.getMaxPower(dir.getOpposite()), true);
			}
		}
	}
}

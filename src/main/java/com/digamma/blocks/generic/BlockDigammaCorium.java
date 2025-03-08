package com.digamma.blocks.generic;

import com.hbm.lib.HBMSoundHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockDigammaCorium extends BlockHazardDigammaAddon{
    public BlockDigammaCorium(Material mat, String s) {
        super(mat, s);
    }

    public BlockDigammaCorium(String s) {
        super(s);
    }

    public BlockDigammaCorium(Material mat, SoundType type, String s) {
        super(mat, type, s);
    }

    public BlockDigammaCorium(SoundType type, String s) {
        super(type, s);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);
            if (worldIn.getTotalWorldTime() % 10 == 0 && worldIn.rand.nextInt(5) == 0) {
                worldIn.playSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, HBMSoundHandler.voiceSounds[worldIn.rand.nextInt(8)], SoundCategory.PLAYERS, 0.44F, 1.0F, false);
            }
    }
}

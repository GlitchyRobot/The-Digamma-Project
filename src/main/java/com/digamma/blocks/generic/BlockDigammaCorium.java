package com.digamma.blocks.generic;

import com.digamma.blocks.ModBlocks;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.util.ContaminationUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
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

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
            if (world.getTotalWorldTime() % 10 == 0 && world.rand.nextInt(3) == 0) {
                world.playSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, HBMSoundHandler.voiceSounds[7], SoundCategory.PLAYERS, 1.0F, 1.0F, false);
            }
    }

}

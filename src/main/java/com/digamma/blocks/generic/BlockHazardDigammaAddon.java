package com.digamma.blocks.generic;

import com.digamma.blocks.ModBlocks;
import com.digamma.items.ModItems;
import com.digamma.main.MainRegistry;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.modules.ItemHazardModule;
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

import java.util.Random;

public class BlockHazardDigammaAddon extends BlockHazardAddon{
    public BlockHazardDigammaAddon(Material mat, String s) {
        super(mat, s);
    }

    public BlockHazardDigammaAddon(String s) {
        super(s);
    }

    public BlockHazardDigammaAddon(Material mat, SoundType type, String s) {
        super(mat, type, s);
    }

    public BlockHazardDigammaAddon(SoundType type, String s) {
        super(type, s);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entity) {
        if(entity instanceof EntityLivingBase)
            this.module.applyEffects((EntityLivingBase)entity, 0.5F, 0, false, EnumHand.MAIN_HAND);
            ContaminationUtil.contaminate((EntityLivingBase) entity, ContaminationUtil.HazardType.DIGAMMA, ContaminationUtil.ContaminationType.DIGAMMA, 0.05F);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity){
        if(entity instanceof EntityLivingBase)
            this.module.applyEffects((EntityLivingBase)entity, 0.5F, 0, false, EnumHand.MAIN_HAND);
            ContaminationUtil.contaminate((EntityLivingBase) entity, ContaminationUtil.HazardType.DIGAMMA, ContaminationUtil.ContaminationType.DIGAMMA, 0.05F);
    }

    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if(this == ModBlocks.block_digamma_corium) {
            if (world.getTotalWorldTime() % 10 == 0 && world.rand.nextInt(3) == 0) {
                world.playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, HBMSoundHandler.voiceSounds[7], SoundCategory.PLAYERS, 0.2F, 1.0F);
            }
        }
    }

}

package com.digamma.items.special;

import com.hbm.blocks.generic.BlockClean;
import com.hbm.entity.effect.EntityFalloutUnderGround;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.I18nUtil;
import com.digamma.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemContaminatingAddon extends ItemHazardAddon {

    private int burntime;
    private int falloutBallRadius = 0;

    public ItemContaminatingAddon(String s, float radiation, int radius){
        super(s);
        this.addRadiation(radiation);
        this.falloutBallRadius = radius;
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem){
        boolean m = this.module.onEntityItemUpdate(entityItem);
        if(entityItem != null && !entityItem.world.isRemote && (entityItem.onGround || entityItem.isBurning())) {
            if(isCleanGround(new BlockPos(entityItem.posX, entityItem.posY, entityItem.posZ), entityItem.world)){
                return false;
            }
            if(falloutBallRadius > 1){
                EntityFalloutUnderGround falloutBall = new EntityFalloutUnderGround(entityItem.world);
                falloutBall.posX = entityItem.posX;
                falloutBall.posY = entityItem.posY+0.5F;
                falloutBall.posZ = entityItem.posZ;
                falloutBall.setScale(falloutBallRadius);
                entityItem.world.spawnEntity(falloutBall);
            }
            entityItem.setDead();
            return true;
        }
        return false || m;
    }

    public static boolean isCleanGround(BlockPos pos, World world){
        Block b = world.getBlockState(pos.down()).getBlock();
        boolean isClean = b instanceof BlockClean;
        if(isClean){
            BlockClean.getUsed(b, pos.down(), world);
        }
        return isClean;
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flagIn){
        super.addInformation(stack, world, list, flagIn);
        if(falloutBallRadius > 1){
            list.add(TextFormatting.DARK_GREEN + "["+I18nUtil.resolveKey("trait.contaminating")+"]");
            list.add(TextFormatting.GREEN + " "+I18nUtil.resolveKey("trait.contaminating.radius", falloutBallRadius));
        }
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return burntime;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
        if (stack.getItem() != null && stack.getItem() == ModItems.digammeal) {
            if (player.world.isRemote && target instanceof EntityLivingBase && target.isEntityAlive()) {
                player.swingArm(hand);
            }
            if (!player.world.isRemote) {
                if (target instanceof EntityLivingBase && target.isEntityAlive()) {
                    ContaminationUtil.contaminate((EntityLivingBase) target, ContaminationUtil.HazardType.DIGAMMA, ContaminationUtil.ContaminationType.DIGAMMA, 0.250F);
                    stack.shrink(1);
                }
            }
        }
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
    }
}
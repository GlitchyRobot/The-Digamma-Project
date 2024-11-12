package com.digamma.items.special;

import com.digamma.items.ModItems;
import com.hbm.explosion.ExplosionChaos;
import com.hbm.modules.ItemHazardModule;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemDigammite  extends ItemHazardAddon {
    public ItemDigammite(String s) {
        super(s);
    }
    @Override
    public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flagIn) {
        super.addInformation(stack, world, list, flagIn);
        list.add(TextFormatting.RED + "[" + I18nUtil.resolveKey("trait.drop") + "]");
    }
    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        if(entityItem != null) {

            ItemStack stack = entityItem.getItem();

            if(entityItem.onGround || entityItem.isBurning()) {

                if (stack.getItem() != null && stack.getItem() == ModItems.digammite) {

                    if (!entityItem.world.isRemote) {
                        double x = entityItem.posX;
                        double y = entityItem.posY;
                        double z = entityItem.posZ;

                        List<Entity> entitiesInRange = entityItem.world.getEntitiesWithinAABB(Entity.class,
                                new AxisAlignedBB(
                                        x - 25, y - 25, z - 25,
                                        x + 25, y + 25, z + 25
                                ));
                        // Loop through each entity found within the radius
                        for (Entity entity : entitiesInRange) {
                            if(entity instanceof EntityLivingBase)
                            ContaminationUtil.contaminate((EntityLivingBase) entity, ContaminationUtil.HazardType.DIGAMMA, ContaminationUtil.ContaminationType.DIGAMMA, 0.333F);
                        }
                        ExplosionChaos.floater(entityItem.world, (int)entityItem.posX, (int)entityItem.posY, (int)entityItem.posZ, 25, 75);
                        ExplosionChaos.move(entityItem.world, (int)entityItem.posX, (int)entityItem.posY, (int)entityItem.posZ, 25, 0, 75, 0);
                    }
                }
                entityItem.setDead();
                return true;
            }
        }
        return false;
    }
}
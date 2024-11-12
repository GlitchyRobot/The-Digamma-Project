package com.digamma.items.special;

import com.digamma.items.ModItems;
import com.digamma.main.MainRegistry;
import com.hbm.interfaces.IItemHazard;
import com.hbm.modules.ItemHazardModule;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.List;

public class ItemHazardAddon extends Item implements IItemHazard {
    ItemHazardModule module;
    public ItemHazardAddon(String s) {
        this.setUnlocalizedName(s);
        this.setRegistryName(s);
        this.setCreativeTab(MainRegistry.contentTab);
        ModItems.ALL_ITEMS.add(this);
        this.module = new ItemHazardModule();
    }
    @Override
    public ItemHazardModule getModule() {return this.module; }
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
        if(!worldIn.isRemote && entity instanceof EntityLivingBase)
            this.module.applyEffects((EntityLivingBase) entity, stack.getCount(), itemSlot, isSelected, ((EntityLivingBase)entity).getHeldItem(EnumHand.MAIN_HAND) == stack ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
    }
    @Override
    public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flagIn) {
        this.module.addInformation(stack, list, flagIn);
    }
    @Override
    public boolean onEntityItemUpdate(EntityItem item) {
        boolean m = this.module.onEntityItemUpdate(item);
        boolean i = super.onEntityItemUpdate(item);
        return m || i;
    }
}

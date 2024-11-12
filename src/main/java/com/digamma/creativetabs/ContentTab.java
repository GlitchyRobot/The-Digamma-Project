package com.digamma.creativetabs;

import com.digamma.items.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ContentTab extends CreativeTabs {

    public ContentTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack getTabIconItem() {
        if(ModItems.ingot_digammium != null){
            return new ItemStack(ModItems.ingot_digammium);
        }
        return new ItemStack(Items.IRON_PICKAXE);
    }

}
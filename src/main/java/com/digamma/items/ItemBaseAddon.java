package com.digamma.items;

import com.digamma.main.MainRegistry;
import net.minecraft.item.Item;

public class ItemBaseAddon extends Item {

    public ItemBaseAddon(String s){
        this.setUnlocalizedName(s);
        this.setRegistryName(s);
        this.setCreativeTab(MainRegistry.contentTab);
        ModItems.ALL_ITEMS.add(this);
    }

}

package com.digamma.items;

import com.digamma.blocks.ModBlocks;
import com.digamma.blocks.items.ItemBlockHazardAddon;
import com.digamma.items.special.ItemContaminatingAddon;
import com.digamma.items.special.ItemDigammite;
import com.digamma.items.special.ItemHazardAddon;
import com.hbm.interfaces.IItemHazard;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ALL_ITEMS = new ArrayList<Item>();

    public static final Item ingot_digammium = new ItemHazardAddon("ingot_digammium").addRadiation(160).addFire(5).addDigamma(0.5F).addHydroReactivity().toItem();
    public static final Item billet_digammium = new ItemHazardAddon("billet_digammium").addRadiation(106).addFire(5).addDigamma(0.333F).addHydroReactivity().toItem();
    public static final Item nugget_digammium = new ItemHazardAddon("nugget_digammium").addRadiation(16).addFire(5).addDigamma(0.005F).addHydroReactivity().toItem();
    public static final Item digammite = new ItemDigammite("digammite").addRadiation(80).addDigamma(0.25F).toItem();
    public static final Item crystal_radspice = new ItemHazardAddon("crystal_radspice").addRadiation(100000).addFire(5).addHydroReactivity().addBlinding().addToxic(12).toItem();
    public static final Item euphemite = new ItemBaseAddon("euphemite");
    public static final Item digammeal = new ItemContaminatingAddon("digammeal", 500, 500).addDigamma(1.5F).toItem();

    public static void preInit(){
        for(Item item : ALL_ITEMS){
            ForgeRegistries.ITEMS.register(item);
        }

        for(Block block : ModBlocks.ALL_BLOCKS){
            if(block instanceof IItemHazard){
                ForgeRegistries.ITEMS.register(new ItemBlockHazardAddon(block).setRegistryName(block.getRegistryName()));
            } else {
                ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
            }
        }
    }

    public static void init(){
    }

    public static void postInit(){
    }
}

package com.digamma.main;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModEventHandler {

    @SubscribeEvent
    public void craftingRegister(RegistryEvent.Register<IRecipe> e){
        long mem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Memory usage before: " + mem);
        CraftingManager.hack = e;
        CraftingManager.init();
        CraftingManager.hack = null;
        mem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Memory usage after: " + mem);
    }
}

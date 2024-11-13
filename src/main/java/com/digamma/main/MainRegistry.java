package com.digamma.main;

import com.digamma.blocks.ModBlocks;
import com.digamma.creativetabs.ContentTab;
import com.digamma.forgefluid.ModForgeFluids;
import com.digamma.items.ModItems;
import com.hbm.main.ClientProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = MainRegistry.MODID, name = MainRegistry.NAME, version = MainRegistry.VERSION)
public class MainRegistry
{
    public static final String MODID = "digammaproject";
    public static final String NAME = "The Digamma Project";
    public static final String VERSION = "1.0";

    static {
        FluidRegistry.enableUniversalBucket();
    }

    // ingots, nuggets, wires, machine parts
    public static CreativeTabs contentTab = new ContentTab(CreativeTabs.getNextID(), "tabAddon");
    public static ClientProxy proxy;

    private static Logger logger;



    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new ModEventHandlerClient());
        MinecraftForge.EVENT_BUS.register(new ModEventHandler());
        logger = event.getModLog();
        ModForgeFluids.init();
        ModItems.preInit();
        ModBlocks.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}

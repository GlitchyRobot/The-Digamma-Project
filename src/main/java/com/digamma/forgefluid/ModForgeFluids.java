package com.digamma.forgefluid;

import com.digamma.blocks.fluid.DigammalineBlock;
import com.digamma.blocks.fluid.DigammalineFluid;
import com.digamma.blocks.ModBlocks;
import com.hbm.forgefluid.FFUtils;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;

public class ModForgeFluids {
    public static HashMap<Fluid, Integer> fluidColors = new HashMap<Fluid, Integer>();
    public static Fluid digammaline = new DigammalineFluid().setDensity(31200).setViscosity(2000).setTemperature(3000);

    public static void init() {
        if(!FluidRegistry.registerFluid(digammaline))
            digammaline = FluidRegistry.getFluid("digammaline");
        ModBlocks.digammaline_block = new DigammalineBlock(digammaline, ModBlocks.digammaline, "digammaline").setResistance(500F);
        digammaline.setBlock(ModBlocks.digammaline_block);
        FluidRegistry.addBucketForFluid(digammaline);
    }
    public static void setFromRegistry() {
        digammaline = FluidRegistry.getFluid("digammaline");
    }

    @SubscribeEvent
    public static void worldLoad(WorldEvent.Load evt) {
        setFromRegistry();
    }

    public static void registerFluidColors(){
        for(Fluid f : FluidRegistry.getRegisteredFluids().values()){
            fluidColors.put(f, FFUtils.getColorFromFluid(f));
        }
    }

    public static int getFluidColor(Fluid f){
        if(f == null)
            return 0;
        Integer color = fluidColors.get(f);
        if(color == null)
            return 0xFFFFFF;
        return color;
    }
}

package com.digamma.blocks.fluid;

import java.awt.Color;

import com.digamma.main.MainRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class DigammalineFluid extends Fluid {

    public DigammalineFluid(){
        super("digammaline", new ResourceLocation(MainRegistry.MODID, "blocks/forgefluid/digammaline_still"), new ResourceLocation(MainRegistry.MODID, "blocks/forgefluid/digammaline_flowing"), Color.white);
    }

}
package com.digamma.blocks.fluid;

import com.digamma.main.MainRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import java.awt.*;

public class DigammalineFluid extends Fluid {

    public DigammalineFluid(){
        super("digammaline", new ResourceLocation(MainRegistry.MODID, "blocks/forgefluid/digammaline_still"), new ResourceLocation(MainRegistry.MODID, "blocks/forgefluid/digammaline_flowing"), Color.white);
    }

}
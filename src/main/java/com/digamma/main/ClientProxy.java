package com.digamma.main;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import com.digamma.blocks.ModBlocks;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleSuspendedTown;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.digamma.blocks.ModBlocks.digammaline_block;

public class ClientProxy extends ServerProxy {

    public static final ModelResourceLocation IRRELEVANT_MRL = new ModelResourceLocation("digamma:placeholdermodel", "inventory");

    //Drillgon200: This is stupid, but I'm lazy
    public static boolean renderingConstant = false;

    public static Field partialTicksPaused;

    public static final FloatBuffer AUX_GL_BUFFER = GLAllocation.createDirectFloatBuffer(16);
    public static final FloatBuffer AUX_GL_BUFFER2 = GLAllocation.createDirectFloatBuffer(16);

    //Drillgon200: Will I ever figure out how to write better code than this?
    public static final List<Runnable> deferredRenderers = new ArrayList<>();

    @Override
    public File getDataDir() {
        return Minecraft.getMinecraft().mcDataDir;
    }

    @Override
    public void registerRenderInfo()
    {
        if(!Minecraft.getMinecraft().getFramebuffer().isStencilEnabled())
            Minecraft.getMinecraft().getFramebuffer().enableStencil();

        MinecraftForge.EVENT_BUS.register(new ModEventHandlerClient());

        ModelLoader.setCustomStateMapper(digammaline_block, new StateMap.Builder().ignore(BlockFluidClassic.LEVEL).build());
    }

    @SideOnly(Side.CLIENT)
    public static void registerFluidModels() {
        ModelLoader.setCustomStateMapper(digammaline_block, new StateMap.Builder()
                .ignore(BlockLiquid.LEVEL)
                .build());
    }

    //Drillgon200: Sending whole tag compounds to spawn particles can't be efficient...
    @SuppressWarnings("deprecation")
    //mk3, only use this one
    @Override
    public void effectNT(NBTTagCompound data) {
        World world = Minecraft.getMinecraft().world;
        if(world == null)
            return;
        EntityPlayer player = Minecraft.getMinecraft().player;
        Random rand = world.rand;
        String type = data.getString("type");
        double x = data.getDouble("posX");
        double y = data.getDouble("posY");
        double z = data.getDouble("posZ");

        if("schrabfog".equals(type)) {

            ParticleSuspendedTown flash = (ParticleSuspendedTown)new ParticleSuspendedTown.Factory().createParticle(-1, world, x, y, z, 0, 0, 0);
            flash.setRBGColorF(0F, 1F, 1F);
            Minecraft.getMinecraft().effectRenderer.addEffect(flash);
            return;
        }

    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
    }

}

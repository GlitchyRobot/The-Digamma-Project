package com.digamma.main;

import com.digamma.blocks.ModBlocks;
import com.digamma.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModEventHandlerClient {

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        for(Item item : ModItems.ALL_ITEMS) {
            registerModel(item, 0);
        }
        for(Block block : ModBlocks.ALL_BLOCKS) {
            registerBlockModel(block, 0);
        }
    }
    private void registerModel(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
    private void registerBlockModel(Block block, int meta) {
        registerModel(Item.getItemFromBlock(block), meta);
    }

    @SubscribeEvent
    public void textureStitch(TextureStitchEvent.Pre evt) {
        evt.getMap().registerSprite(new ResourceLocation(MainRegistry.MODID, "blocks/forgefluid/digammaline_still"));
		evt.getMap().registerSprite(new ResourceLocation(MainRegistry.MODID, "blocks/forgefluid/digammaline_flowing"));
    }
}

package com.digamma.blocks;

import com.digamma.blocks.generic.BlockDigammaCorium;
import com.digamma.blocks.generic.BlockHazardAddon;
import com.digamma.blocks.generic.BlockHazardDigammaAddon;
import com.hbm.blocks.MaterialGas;
import com.digamma.main.MainRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    //present gui id: 126
    public static List<Block> ALL_BLOCKS = new ArrayList<Block>();

    //public static final Block fatduck = new BlockBase(Material.IRON, "fatduck");

    public static Material materialGas = new MaterialGas();

    public static final Block digammafite_slaked = new BlockHazardDigammaAddon(Material.ROCK, SoundType.STONE, "digammafite_slaked").addRadiation(33F).addDigamma(0.05F).toBlock().setHardness(5.0F).setResistance(6F).setCreativeTab(MainRegistry.contentTab);
    public static final Block digammafite_0 = new BlockHazardDigammaAddon(Material.ROCK, SoundType.STONE, "digammafite_0").addRadiation(50.0F).addDigamma(0.075F).toBlock().setHardness(5.0F).setResistance(6F).setCreativeTab(MainRegistry.contentTab);
    public static final Block digammafite_1 = new BlockHazardDigammaAddon(Material.ROCK, SoundType.STONE, "digammafite_1").addRadiation(66.0F).addDigamma(0.1F).toBlock().setHardness(5.0F).setResistance(6F).setCreativeTab(MainRegistry.contentTab);
    public static final Block digammafite_2 = new BlockHazardDigammaAddon(Material.ROCK, SoundType.STONE, "digammafite_2").addRadiation(133.0F).addDigamma(0.2F).toBlock().setHardness(5.0F).setResistance(6F).setCreativeTab(MainRegistry.contentTab);
    public static final Block digammafite_3 = new BlockHazardDigammaAddon(Material.ROCK, SoundType.STONE, "digammafite_3").addRadiation(200.0F).addFire(2).addDigamma(0.3F).toBlock().setHardness(5.0F).setResistance(7F).setCreativeTab(MainRegistry.contentTab);
    public static final Block digammafite_4 = new BlockHazardDigammaAddon(Material.ROCK, SoundType.STONE, "digammafite_4").addRadiation(266.0F).addFire(10).addDigamma(0.4F).toBlock().setHardness(5.0F).setResistance(8F).setCreativeTab(MainRegistry.contentTab);
    public static final Block digammafite_core = new BlockHazardDigammaAddon(Material.ROCK, SoundType.STONE, "digammafite_core").addRadiation(333.0F).addFire(15).addDigamma(0.5F).toBlock().setHardness(10.0F).setResistance(9F).setCreativeTab(MainRegistry.contentTab);
    public static final Block block_digamma_corium = new BlockDigammaCorium(Material.IRON, SoundType.STONE, "block_digamma_corium").makeBeaconable().addRad3d(15000).addRadiation(1000.0F).addDigamma(3.333F).toBlock().setHardness(100.0F).setResistance(9000F).setCreativeTab(MainRegistry.contentTab);
    public static final Block block_digamma_corium_cobble = new BlockHazardDigammaAddon(Material.ROCK, SoundType.STONE, "block_digamma_corium_cobble").addRadiation(100.0F).addDigamma(0.333F).toBlock().setHardness(100.0F).setResistance(6000F).setCreativeTab(MainRegistry.contentTab);

    public static final Material digammaline = (new MaterialLiquid(MapColor.RED) {
        @Override
        public boolean blocksMovement() {
            return true;
        }
        @Override
        public Material setImmovableMobility() {
            return super.setImmovableMobility();
        }
    }.setImmovableMobility());
    public static Block digammaline_block;

    public static void preInit(){
        for(Block block : ALL_BLOCKS){
            ForgeRegistries.BLOCKS.register(block);
        }
    }

    public static void init(){

    }

    public static void postInit(){

    }
}

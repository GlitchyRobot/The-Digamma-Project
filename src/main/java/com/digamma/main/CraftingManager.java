package com.digamma.main;

import com.digamma.items.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class CraftingManager {

    public static RegistryEvent.Register<IRecipe> hack;
    public static boolean registeringFluids = false;

    public static void init(){
        addCrafting();
        addSmelting();
    }

    public static void addCrafting(){
        addBillet(ModItems.billet_digammium, ModItems.nugget_digammium);
        addBilletByIngot(ModItems.billet_digammium, ModItems.ingot_digammium);
        addShapelessAuto(new ItemStack(ModItems.ingot_digammium, 2), new Object[] { ModItems.billet_digammium, ModItems.billet_digammium, ModItems.billet_digammium });
        addRecipeAuto(new ItemStack(ModItems.ingot_digammium, 1), new Object[] { "###", "###", "###", '#', ModItems.nugget_digammium });
        addRecipeAuto(new ItemStack(ModItems.nugget_digammium, 9), new Object[] { "#", '#', ModItems.ingot_digammium });
    }

    public static void addSmelting(){

    }

    public static void addBillet(Item billet, Item nugget, String... ore){
        for(String o : ore)
            addRecipeAuto(new ItemStack(billet), new Object[] { "###", "###", '#', o });

        addBillet(billet, nugget);
    }

    public static void addBillet(Item billet, Item nugget){
        addRecipeAuto(new ItemStack(billet), new Object[] { "###", "###", '#', nugget });
        addShapelessAuto(new ItemStack(nugget, 6), new Object[] { billet });
    }

    public static void addBilletByIngot(Item billet, Item ingot, String... ore){
        for(String o : ore)
            addShapelessAuto(new ItemStack(billet, 3), new Object[] { o, o });
        addShapelessAuto(new ItemStack(billet, 3), new Object[] { ingot, ingot });
        addShapelessAuto(new ItemStack(ingot, 2), new Object[] { billet, billet, billet });
    }

    public static void addBilletByIngot(Item billet, Item ingot){
        addShapelessAuto(new ItemStack(billet, 3), new Object[] { ingot, ingot });
        addShapelessAuto(new ItemStack(ingot, 2), new Object[] { billet, billet, billet });
    }


    public static void addRecipeAuto(ItemStack output, Object... args){

        boolean shouldUseOD = false;
        boolean patternEnded = false;
        for(int i = 0; i < args.length; i++) {
            if(args[i] instanceof String) {
                if(patternEnded) {
                    shouldUseOD = true;
                    break;
                }
            } else {
                patternEnded = true;
            }
        }

        ResourceLocation loc = getRecipeName(output);
        IRecipe recipe;
        if(shouldUseOD){
            recipe = new ShapedOreRecipe(loc, output, args);
        }else {
            CraftingHelper.ShapedPrimer primer = CraftingHelper.parseShaped(args);
            recipe = new ShapedRecipes(output.getItem().getRegistryName().toString(), primer.width, primer.height, primer.input, output);
        }
        recipe.setRegistryName(loc);
        hack.getRegistry().register(recipe);
    }

    public static void addShapelessAuto(ItemStack output, Object... args) {

        boolean shouldUseOD = false;

        for(int i = 0; i < args.length; i ++) {
            if(args[i] instanceof String) {
                shouldUseOD = true;
                break;
            }
        }

        ResourceLocation loc = getRecipeName(output);
        IRecipe recipe;
        if(shouldUseOD){
            recipe = new ShapelessOreRecipe(loc, output, args);
        }else{
            recipe = new ShapelessRecipes(loc.getResourceDomain(), output, buildInput(args));
        }
        recipe.setRegistryName(loc);
        hack.getRegistry().register(recipe);
    }

    public static ResourceLocation getRecipeName(ItemStack output){
        ResourceLocation loc = new ResourceLocation(MainRegistry.MODID, output.getItem().getRegistryName().getResourcePath());
        int i = 0;
        ResourceLocation r_loc = loc;
        while(net.minecraft.item.crafting.CraftingManager.REGISTRY.containsKey(r_loc)) {
            i++;
            r_loc = new ResourceLocation(MainRegistry.MODID, loc.getResourcePath() + "_" + i);
        }
        return r_loc;
    }

    public static NonNullList<Ingredient> buildInput(Object[] args){
        NonNullList<Ingredient> list = NonNullList.create();
        for(Object obj : args) {
            if(obj instanceof Ingredient) {
                list.add((Ingredient)obj);
            } else {
                Ingredient i = CraftingHelper.getIngredient(obj);
                if(i == null) {
                    i = Ingredient.EMPTY;
                }
                list.add(i);
            }
        }
        return list;
    }
}

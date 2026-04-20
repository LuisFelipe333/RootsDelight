package com.roots.roots_delight.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class BetterBrewingRecipe implements IBrewingRecipe {
    private final net.minecraft.world.item.alchemy.Potion inputPotion;
    private final Item ingredient;
    private final ItemStack output;

    public BetterBrewingRecipe(net.minecraft.world.item.alchemy.Potion inputPotion, Item ingredient, ItemStack output) {
        this.inputPotion = inputPotion;
        this.ingredient = ingredient;
        this.output = output;
    }

    @Override
    public boolean isInput(ItemStack input) {
        // Verifica si la poción de entrada es la que definimos (ej: Agua)
        return PotionUtils.getPotion(input) == this.inputPotion;
    }

    @Override
    public boolean isIngredient(ItemStack ingredient) {
        // Verifica si el ingrediente en la ranura de arriba es el correcto
        return ingredient.getItem() == this.ingredient;
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        if(!isInput(input) || !isIngredient(ingredient)) return ItemStack.EMPTY;
        return output.copy();
    }

}

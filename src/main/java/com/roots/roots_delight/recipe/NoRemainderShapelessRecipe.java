package com.roots.roots_delight.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class NoRemainderShapelessRecipe extends ShapelessRecipe {

    public NoRemainderShapelessRecipe(ResourceLocation id,
                                      String group,
                                      CraftingBookCategory category,
                                      ItemStack result,
                                      NonNullList<Ingredient> ingredients) {
        super(id, group, category, result, ingredients);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer inv) {
        // No devolver nada (ni cubetas, ni botellas, etc.)
        return NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);
    }
}

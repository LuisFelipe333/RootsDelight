package com.roots.roots_delight.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.Nullable;

public class NoRemainderShapelessSerializer implements RecipeSerializer<NoRemainderShapelessRecipe> {

    @Override
    public NoRemainderShapelessRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
        String group = GsonHelper.getAsString(json, "group", "");
        CraftingBookCategory category = CraftingBookCategory.CODEC.byName(
                GsonHelper.getAsString(json, "category", "misc"),
                CraftingBookCategory.MISC
        );

        // Ingredientes (igual que shapeless vanilla)
        NonNullList<Ingredient> ingredients = NonNullList.create();
        for (var el : GsonHelper.getAsJsonArray(json, "ingredients")) {
            Ingredient ing = Ingredient.fromJson(el);
            if (!ing.isEmpty()) ingredients.add(ing);
        }

        if (ingredients.isEmpty()) {
            throw new IllegalArgumentException("No ingredients for shapeless recipe " + recipeId);
        }
        if (ingredients.size() > 9) {
            throw new IllegalArgumentException("Too many ingredients for shapeless recipe " + recipeId);
        }

        // Resultado (usamos helper vanilla)
        ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

        return new NoRemainderShapelessRecipe(recipeId, group, category, result, ingredients);
    }

    @Override
    public @Nullable NoRemainderShapelessRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buf) {
        String group = buf.readUtf();
        CraftingBookCategory category = buf.readEnum(CraftingBookCategory.class);

        int size = buf.readVarInt();
        NonNullList<Ingredient> ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
        for (int i = 0; i < size; i++) {
            ingredients.set(i, Ingredient.fromNetwork(buf));
        }

        ItemStack result = buf.readItem();

        return new NoRemainderShapelessRecipe(recipeId, group, category, result, ingredients);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, NoRemainderShapelessRecipe recipe) {
        buf.writeUtf(recipe.getGroup());
        buf.writeEnum(recipe.category());

        buf.writeVarInt(recipe.getIngredients().size());
        for (Ingredient ing : recipe.getIngredients()) {
            ing.toNetwork(buf);
        }

        buf.writeItem(recipe.getResultItem(null));
    }
}

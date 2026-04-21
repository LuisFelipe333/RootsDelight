package com.roots.roots_delight.item;

import com.roots.roots_delight.config.PozolConfig;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.Random;
import net.minecraft.world.item.Item;

public class PozolBlancoItem extends Item {

    public PozolBlancoItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        // First, let the player actually "eat" the item (reduce stack size, etc.)
        ItemStack resultStack = super.finishUsingItem(stack, level, entity);

        // Only run logic on the Server and if it's a Living Entity (Player/Mob)
        if (!level.isClientSide) {
            float chance = 0.20f; // Default fallback

            try {
                // Try to get the probability from your TOML config
                chance = PozolConfig.POZOL_BLANCO_CHANCE.get().floatValue();
            } catch (IllegalStateException e) {
                // If the config isn't loaded yet, it uses the 0.20f above
            }

            Random rand = new Random();

            // Apply your 1-minute (1200 ticks) Level II (amplifier 1) effects
            applyEffect(entity, MobEffects.JUMP, 1200, 1, chance, rand);
            applyEffect(entity, MobEffects.FIRE_RESISTANCE, 1200, 0, chance, rand);
            applyEffect(entity, MobEffects.MOVEMENT_SPEED, 1200, 1, chance, rand);
            applyEffect(entity, MobEffects.HEAL, 1, 1, chance, rand);
            applyEffect(entity, MobEffects.REGENERATION, 1200, 1, chance, rand);
            applyEffect(entity, MobEffects.DAMAGE_BOOST, 1200, 1, chance, rand);
            applyEffect(entity, MobEffects.NIGHT_VISION, 1200, 0, chance, rand);
            applyEffect(entity, MobEffects.DIG_SPEED, 1200, 1, chance, rand);
        }

        return resultStack;
    }

    private void applyEffect(LivingEntity entity, MobEffect effect, int duration, int amplifier, float chance, Random rand) {
        if (rand.nextFloat() < chance) {
            entity.addEffect(new MobEffectInstance(effect, duration, amplifier));
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK; // Esto cambia el sonido y la animación a bebida
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK; // Sonido al beber
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK; // Sonido por si acaso se activa como comida
    }

}

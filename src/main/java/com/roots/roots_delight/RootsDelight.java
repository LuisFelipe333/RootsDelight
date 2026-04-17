package com.roots.roots_delight;

import com.roots.roots_delight.block.CocolmecaBlock;
import com.roots.roots_delight.item.DrinkItem;
import com.roots.roots_delight.item.PozolCacaoItem;
import com.roots.roots_delight.item.PozolBlancoItem;
import com.roots.roots_delight.recipe.NoRemainderShapelessSerializer;
import com.roots.roots_delight.config.PozolConfig;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(RootsDelight.MODID)
public class RootsDelight
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "roots_delight";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "roots_delight" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "roots_delight" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "roots_delight" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);


    //Metodo para quitar fondo
    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Esto le quita el fondo negro al bloque de cultivo
            ItemBlockRenderTypes.setRenderLayer(COCOLMECA_CROP.get(), RenderType.cutout());
        });
    }

    // 1. Registro del Bloque (El cultivo)
    // Copiamos las propiedades de las papas de Minecraft (Blocks.POTATOES)
        public static final RegistryObject<Block> COCOLMECA_CROP = BLOCKS.register("cocolmeca_crop",
                () -> new CocolmecaBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES)));

        // 2. Registro del Ítem (La raíz)
    // Usamos ItemNameBlockItem para que al usarse en tierra arada coloque el bloque de arriba
    // Al no poner .food(), el objeto NO se podrá comer.
        public static final RegistryObject<Item> RAIZ_COCOLMECA = ITEMS.register("raiz_cocolmeca",
                () -> new ItemNameBlockItem(COCOLMECA_CROP.get(), new Item.Properties()));


    // ========== ÍTEMS BÁSICOS ==========

    public static final RegistryObject<Item> CACAO_TOSTADO = ITEMS.register(
            "cacao_tostado",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> CACAO_MOLIDO = ITEMS.register(
            "cacao_molido",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> CAL = ITEMS.register(
            "cal",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> CUBETA_MAIZ_CAL = ITEMS.register(
            "cubeta_maiz_cal",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> NIXTAMAL = ITEMS.register(
            "nixtamal",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> MASA_POZOL_BLANCO = ITEMS.register(
            "masa_pozol_blanco",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> MASA_POZOL_CACAO = ITEMS.register(
            "masa_pozol_cacao",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> SINKO_PESO = ITEMS.register(
            "sinko_peso",
            () -> new Item(new Item.Properties())
    );


    //========== NO DEVOLVER CUBETA ==========
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);

    public static final RegistryObject<RecipeSerializer<?>> NO_REMAINDER_SHAPELESS =
            RECIPE_SERIALIZERS.register("no_remainder_shapeless", NoRemainderShapelessSerializer::new);





//    //========== EFECTOS COMIDA ==========
//    private static final FoodProperties POZOL_BLANCO_FOOD = new FoodProperties.Builder()
//            .nutrition(5)               // 5 muslitos
//            .saturationMod(0.6f)
//            // 60 segundos = 60 * 20 ticks = 1200
//
//            // Visión nocturna 20%
//            .effect(
//                    () -> new MobEffectInstance(MobEffects.NIGHT_VISION, 1200, 0),
//                    0.20f
//            )
//            // Salto I 20%
//            .effect(
//                    () -> new MobEffectInstance(MobEffects.JUMP, 1200, 0),
//                    0.20f
//            )
//            // Resistencia al fuego 20%
//            .effect(
//                    () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0),
//                    0.20f
//            )
//            // Velocidad I 20%
//            .effect(
//                    () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0),
//                    0.20f
//            )
//            // Curación instantánea I 20% (duración cortita, es instantánea de todos modos)
//            .effect(
//                    () -> new MobEffectInstance(MobEffects.HEAL, 1, 0),
//                    0.20f
//            )
//            // Regeneración I 20%
//            .effect(
//                    () -> new MobEffectInstance(MobEffects.REGENERATION, 1200, 0),
//                    0.20f
//            )
//            // Fuerza I 20%
//            .effect(
//                    () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0),
//                    0.20f
//            )
//            // Prisa minera I 20%
//            .effect(
//                    () -> new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 0),
//                    0.20f
//            )
//            .alwaysEat()
//
//            .build();

    public static final FoodProperties POZOL_BLANCO_FOOD = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.6f)
            .alwaysEat()
            .build();

    public static final FoodProperties POZOL_CACAO_FOOD = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.6f)
            .alwaysEat()
            .build();


//    // Pozol de cacao: 5 de comida, 1 minuto, efectos en nivel II con 15% cada uno
//    private static final FoodProperties POZOL_CACAO_FOOD = new FoodProperties.Builder()
//            .nutrition(5)
//            .saturationMod(0.6f)
//
//            // Salto II 15%
//            .effect(
//                    () -> new MobEffectInstance(MobEffects.JUMP, 1200, 1),
//                    0.15f
//            )
//            // Resistencia al fuego 15%
//            .effect(
//                    () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0),
//                    0.15f
//            )
//            // Velocidad II 15%
//            .effect(
//                    () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 1),
//                    0.15f
//            )
//            // Curación instantánea II 15%
//            .effect(
//                    () -> new MobEffectInstance(MobEffects.HEAL, 1, 1),
//                    0.15f
//            )
//            // Regeneración II 15%
//            .effect(
//                    () -> new MobEffectInstance(MobEffects.REGENERATION, 1200, 1),
//                    0.15f
//            )
//            // Fuerza II 15%
//            .effect(
//                    () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 1),
//                    0.15f
//            )
//            // Caída lenta 15%
//            .effect(
//                    () -> new MobEffectInstance(MobEffects.SLOW_FALLING, 1200, 0),
//                    0.15f
//            )
//            // Prisa minera II 15%
//            .effect(
//                    () -> new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 1),
//                    0.15f
//            )
//            .alwaysEat()
//            .build();


    // ========== COMIDAS ==========

    public static final RegistryObject<Item> POZOL_BLANCO = ITEMS.register(
            "pozol_blanco",
            () -> new PozolBlancoItem(new Item.Properties().food(POZOL_BLANCO_FOOD))
    );


    public static final RegistryObject<Item> POZOL_CACAO = ITEMS.register("pozol_cacao",
            () -> new PozolCacaoItem(new Item.Properties().food(POZOL_CACAO_FOOD)));


    //Cheve
    public static final RegistryObject<Item> CHEVE = ITEMS.register(
            "cheve",
            () -> new DrinkItem(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(0)              // no da comida
                            .saturationMod(0.0f)
                            .alwaysEat()                // se puede tomar aunque no tengas hambre
                            .effect(
                                    () -> new MobEffectInstance(
                                            MobEffects.CONFUSION, // Náusea
                                            20 * 60,              // 1 minuto (20 ticks * 60)
                                            0                     // nivel 1
                                    ),
                                    1.0f                        // 100% de probabilidad
                            )
                            .build()
                    )
            )
    );



    //TAB CREATIVO
    public static final RegistryObject<CreativeModeTab> POZOL_TAB =
            CREATIVE_MODE_TABS.register("roots_tab", () -> CreativeModeTab.builder()
                    // Icono de la pestaña (lo que se ve en el botón)
                    .icon(() -> POZOL_CACAO.get().getDefaultInstance())
                    // Título de la pestaña (texto, va por lang)
                    .title(Component.translatable("itemGroup.roots_delight.roots_tab"))
                    // Aquí agregas todo lo que quieres que salga en la pestaña
                    .displayItems((parameters, output) -> {
                        output.accept(CACAO_TOSTADO.get());
                        output.accept(CACAO_MOLIDO.get());
                        output.accept(CAL.get());
                        output.accept(CUBETA_MAIZ_CAL.get());
                        output.accept(NIXTAMAL.get());
                        output.accept(MASA_POZOL_BLANCO.get());
                        output.accept(MASA_POZOL_CACAO.get());
                        output.accept(POZOL_BLANCO.get());
                        output.accept(POZOL_CACAO.get());
                        output.accept(CHEVE.get());

                    })
                    .build());








    public RootsDelight(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        context.registerConfig(ModConfig.Type.COMMON, com.roots.roots_delight.config.PozolConfig.SPEC, "roots_delight-common.toml");


        // Register the commonSetup method for modloading
        modEventBus.addListener(this::clientSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        RECIPE_SERIALIZERS.register(modEventBus);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        //modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
//        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

//        ModLoadingContext.get().registerConfig(
//                ModConfig.Type.COMMON,
//                RootsDelightConfig.COMMON_SPEC
//        );

    }

    @SubscribeEvent
    public void onItemSmelted(PlayerEvent.ItemSmeltedEvent event) {
        ItemStack result = event.getSmelting();  // Lo que el jugador se lleva del horno

        // ¿Es nuestro nixtamal?
        if (result.is(NIXTAMAL.get())) {
            Player player = event.getEntity();
            int count = result.getCount(); // Número de nixtamales que se llevó

            // Crear esa misma cantidad de cubetas vacías
            ItemStack buckets = new ItemStack(Items.BUCKET, count);

            // Intentar meterlas al inventario
            if (!player.addItem(buckets)) {
                // Si no caben, las tiramos al suelo
                player.drop(buckets, false);
            }
        }
    }






}

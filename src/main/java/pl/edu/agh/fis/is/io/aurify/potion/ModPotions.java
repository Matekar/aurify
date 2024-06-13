package pl.edu.agh.fis.is.io.aurify.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.edu.agh.fis.is.io.aurify.AurifyMod;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, AurifyMod.MODID);
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, AurifyMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AurifyMod.MODID);

    public static final RegistryObject<Potion> BLINDNESS_POTION = POTIONS.register("blindness_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 200, 0)));

    public static final RegistryObject<Potion> BLINDNESS_POTION_2 = POTIONS.register("blindness_potion_2",
            () -> new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 200, 1)));

    public static final RegistryObject<MobEffect> CUSTOM_POTION_EFFECT = EFFECTS.register("custom_potion_effect",
            AUPotionEffect::new);

    public static final RegistryObject<Item> CUSTOM_SPLASH_POTION_ITEM = ITEMS.register("custom_splash_potion_item",
            () -> new AUSplashPotion(new Potion(new MobEffectInstance(MobEffects.HARM, 200, 1)),
                    new Item.Properties().stacksTo(1),1.5f));
    public static final RegistryObject<Item> CUSTOM_LINGERING_POTION_ITEM = ITEMS.register("custom_lingering_potion_item",
            () -> new AULingeringPotion(new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 200, 1)),
                    new Item.Properties().stacksTo(1),50,200,7,1.5f));
    public static final RegistryObject<Item> CUSTOM_POTION_ITEM_2 = ITEMS.register("custom_potion_item_2",
            () -> new AUPotion(new Potion(new MobEffectInstance(MobEffects.HEALTH_BOOST, 2000, 2)),
                    new Item.Properties().stacksTo(1),32));

    public static final RegistryObject<Potion> RECALL_POTION
            = POTIONS.register("recall_potion",
            () -> new Potion(new MobEffectInstance(AUEffects.RECALL.get(), 0, 0)));

    public static final RegistryObject<Potion> ANCHORED_RECALL_POTION =
            POTIONS.register("anchored_recall_potion",
                    () -> new Potion(new MobEffectInstance(AUEffects.ANCHORED_RECALL.get(), 0, 0)));

    public static final RegistryObject<Potion> BEGINNING_POTION =
            POTIONS.register("beginning_potion",
                    () -> new Potion(new MobEffectInstance(AUEffects.END_RECALL.get(), 0, 0)));

    public static final RegistryObject<Potion> GRAVITY_POTION = POTIONS.register("gravity_potion",
            () -> new Potion(new MobEffectInstance(AUEffects.GRAVITY.get(), 200, 0)));

    public static final RegistryObject<Potion> THUNDEROUS_POTION = POTIONS.register("thunderous_potion",
            () -> new Potion(new MobEffectInstance(AUEffects.THUNDEROUS.get(), 1)));

    public static final RegistryObject<Potion> EXPLOSIVE_POTION = POTIONS.register("explosive_potion",
            ()-> new Potion(new MobEffectInstance(AUEffects.EXPLOSIVE.get(), 1, 2)));

    public static final RegistryObject<Potion> FATAL_POISON_POTION = POTIONS.register("fatal_poison_potion",
            () -> new Potion(new MobEffectInstance(AUEffects.FATAL_POISON.get(), 400)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
        EFFECTS.register(eventBus);
        ITEMS.register(eventBus);
    }
}

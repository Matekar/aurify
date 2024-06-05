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

    public static final RegistryObject<Potion> DARKNESS_POTION = POTIONS.register("darkness_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.DARKNESS, 200, 1)));

    public static final RegistryObject<MobEffect> CUSTOM_POTION_EFFECT = EFFECTS.register("custom_potion_effect",
            AUPotionEffect::new);

    public static final RegistryObject<Item> CUSTOM_SPLASH_POTION_ITEM = ITEMS.register("custom_splash_potion_item",
            () -> new AUSplashPotion(new Potion(new MobEffectInstance(MobEffects.HARM, 200, 1)),
                    new Item.Properties().stacksTo(1),1.5f));
    public static final RegistryObject<Item> CUSTOM_LINGERING_POTION_ITEM = ITEMS.register("custom_lingering_potion_item",
            () -> new AULingeringPotion(new Potion(new MobEffectInstance(MobEffects.DARKNESS, 200, 1)),
                    new Item.Properties().stacksTo(1),50,200,7,1.5f));
    public static final RegistryObject<Item> CUSTOM_POTION_ITEM_2 = ITEMS.register("custom_potion_item_2",
            () -> new AUPotion(new Potion(new MobEffectInstance(MobEffects.HEALTH_BOOST, 2000, 2)),
                    new Item.Properties().stacksTo(1),32));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
        EFFECTS.register(eventBus);
        ITEMS.register(eventBus);
    }
}

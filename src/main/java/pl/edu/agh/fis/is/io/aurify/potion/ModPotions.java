package pl.edu.agh.fis.is.io.aurify.potion;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.edu.agh.fis.is.io.aurify.AurifyMod;
import pl.edu.agh.fis.is.io.aurify.effects.AUEffects;

public class ModPotions{
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, AurifyMod.MODID);

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

    public static void register(IEventBus eventBus){
        POTIONS.register(eventBus);
    }
}

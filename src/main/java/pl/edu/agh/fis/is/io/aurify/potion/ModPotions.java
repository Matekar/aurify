package pl.edu.agh.fis.is.io.aurify.potion;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.edu.agh.fis.is.io.aurify.AurifyMod;

public class ModPotions {
    public final static DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, AurifyMod.MODID);

    public static final RegistryObject<Potion> DARKNESS_POTION = POTIONS.register("darkness_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.DARKNESS, 200, 1)));

    public static void register(IEventBus eventBus) { POTIONS.register(eventBus); }
}

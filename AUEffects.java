package pl.edu.agh.fis.is.io.aurify.effects;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.edu.agh.fis.is.io.aurify.AurifyMod;

public class AUEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, AurifyMod.MODID);

    public static final RegistryObject<InstantenousMobEffect> RECALL
            = MOB_EFFECTS.register("recall", () -> new RecallEffect(MobEffectCategory.NEUTRAL, 3124687, Level.OVERWORLD));
    public static final RegistryObject<InstantenousMobEffect> ANCHORED_RECALL
            = MOB_EFFECTS.register("anchored_recall", () -> new RecallEffect(MobEffectCategory.NEUTRAL, 0xFF0000, Level.NETHER));
    public static final RegistryObject<InstantenousMobEffect> END_RECALL
            = MOB_EFFECTS.register("end_recall", () -> new RecallEffect(MobEffectCategory.NEUTRAL, 0xFF00FF, Level.END));

    public static final RegistryObject<MobEffect> GRAVITY = MOB_EFFECTS.register(
            "gravity", () -> new GravityEffect(MobEffectCategory.BENEFICIAL, 3124687)
    );

    public static final RegistryObject<MobEffect> THUNDEROUS = MOB_EFFECTS.register(
            "thunderous", () -> new ThunderousEffect(MobEffectCategory.HARMFUL, 14745599)
    );

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}

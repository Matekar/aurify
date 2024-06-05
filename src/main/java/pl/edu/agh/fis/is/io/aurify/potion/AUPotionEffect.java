package pl.edu.agh.fis.is.io.aurify.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class AUPotionEffect extends MobEffect { //TODO: TO TYLKO TEMPLATKA

    public AUPotionEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFFFFF); // KOLOR U NAS TYLKO DO PARTICLI
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        //  TUTAJ USTAWIAMY EFEKTY
        if (this == ModPotions.CUSTOM_POTION_EFFECT.get()) {
            entity.heal(1.0F * (amplifier + 1));
        }
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // ZWRACA TRUE JEZELI MAMY NAKLADAC EFEKT NA KAZDY TICK
        return duration % 20 == 0;
    }
}
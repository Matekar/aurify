package pl.edu.agh.fis.is.io.aurify.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class FatalPoisonEffect extends MobEffect {
    public FatalPoisonEffect(MobEffectCategory mobEffectCategory, int color){
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier){
        livingEntity.hurt(livingEntity.damageSources().magic(), 1.0F);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier){
        int k;
        k = 25 >> amplifier;
        if(k > 0){
            return duration % k == 0;
        } else{
            return true;
        }
    }
}

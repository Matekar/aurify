package pl.edu.agh.fis.is.io.aurify.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class GravityEffect extends MobEffect {
    public GravityEffect(MobEffectCategory mobEffectCategory, int color){
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier){
        pLivingEntity.setDeltaMovement(pLivingEntity.getDeltaMovement().x() * 0.75,
                ((pLivingEntity.getDeltaMovement().y() / 0.98 + 0.08) - 0.02) * 0.9,
                pLivingEntity.getDeltaMovement().z() * 0.75);

        pLivingEntity.fallDistance = (float)(pLivingEntity.fallDistance * 1.4);


        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier){
        return true;
    }
}

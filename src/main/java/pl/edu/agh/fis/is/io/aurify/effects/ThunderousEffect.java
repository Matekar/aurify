package pl.edu.agh.fis.is.io.aurify.effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import pl.edu.agh.fis.is.io.aurify.utils.Utils;

public class ThunderousEffect extends MobEffect {
    public ThunderousEffect(MobEffectCategory mobEffectCategory, int color){
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier){
        if(!livingEntity.level().isClientSide()){
            ServerLevel serverLevel = (ServerLevel) livingEntity.level();
            Utils.lightning(livingEntity, serverLevel, amplifier);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier){
        return true;
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }
}

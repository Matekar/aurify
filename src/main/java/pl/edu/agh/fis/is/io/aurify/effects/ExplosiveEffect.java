package pl.edu.agh.fis.is.io.aurify.effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import pl.edu.agh.fis.is.io.aurify.utils.Utils;

public class ExplosiveEffect extends MobEffect {
    public ExplosiveEffect(MobEffectCategory mobEffectCategory, int color){
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier){
        if(!livingEntity.level().isClientSide()){
            ServerLevel serverLevel = (ServerLevel) livingEntity.level();
            if(!livingEntity.isSpectator()){
                if(livingEntity.level().dimension() == ServerLevel.NETHER){
                    Utils.explode(serverLevel, livingEntity.blockPosition(), amplifier, true);
                }
                else{
                    Utils.explode(serverLevel, livingEntity.blockPosition(), amplifier);
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier){
        return duration == 1;
    }

    @Override
    public boolean isInstantenous(){
        return true;
    }

}

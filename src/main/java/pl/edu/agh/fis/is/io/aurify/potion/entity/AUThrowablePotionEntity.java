package pl.edu.agh.fis.is.io.aurify.potion.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.entity.AreaEffectCloud;

import javax.annotation.Nullable;
import java.util.List;

public class AUThrowablePotionEntity extends ThrownPotion {

    private static final EntityDataAccessor<Integer> DURATION = SynchedEntityData.defineId(AUThrowablePotionEntity.class, EntityDataSerializers.INT);
    private final Potion potion;
    private int duration;
    private int delay;
    private float radius;
    private float throwStrength;
    private boolean isLingering;

//    public CustomThrownLingeringPotionEntity(EntityType<? extends ThrownPotion> entityType, Level level) {
//        super(entityType, level);
//    }

    public AUThrowablePotionEntity(Level level, LivingEntity thrower, int delay, int duration, float radius, float throwStrength, Potion potion, boolean pIsLingering) {
        super(level, thrower);
        this.duration = duration;
        this.radius = radius;
        this.throwStrength = throwStrength;
        this.entityData.set(DURATION, duration);
        this.potion = potion;
        this.delay = delay;
        this.isLingering =pIsLingering;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DURATION, 0);
    }

    @Override
    protected void onHit(HitResult result) {
//        super.onHit(result);
        if (!this.level().isClientSide) {
            int i = potion.hasInstantEffects() ? 2007 : 2002;
            this.level().levelEvent(i, this.blockPosition(), PotionUtils.getColor(potion));
            this.discard();
        }

        if (!this.level().isClientSide) {
            List<MobEffectInstance> effects = potion.getEffects();
            if (!effects.isEmpty()) {
                if(isLingering){
                    createEffectCloud(effects);
                }else
                {
                    applySplash(effects, result.getType() == HitResult.Type.ENTITY ? ((EntityHitResult)result).getEntity() : null);
                }
            }
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.remove(RemovalReason.DISCARDED);
        }
    }

    private void createEffectCloud(List<MobEffectInstance> effects) {
        AreaEffectCloud cloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
        cloud.setOwner((LivingEntity) this.getOwner());
        cloud.setRadius(this.radius);
        cloud.setRadiusOnUse(-0.5F);
        cloud.setWaitTime(this.delay);
        cloud.setDuration(this.duration);
        for (MobEffectInstance effect : effects) {
            cloud.addEffect(new MobEffectInstance(effect));
        }
        this.level().addFreshEntity(cloud);
    }
    private void applySplash(List<MobEffectInstance> pEffectInstances, @Nullable Entity pTarget) {
        AABB aabb = this.getBoundingBox().inflate(4.0D, 2.0D, 4.0D);
        List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, aabb);
        if (!list.isEmpty()) {
            Entity entity = this.getEffectSource();

            for(LivingEntity livingentity : list) {
                if (livingentity.isAffectedByPotions()) {
                    double d0 = this.distanceToSqr(livingentity);
                    if (d0 < 16.0D) {
                        double d1;
                        if (livingentity == pTarget) {
                            d1 = 1.0D;
                        } else {
                            d1 = 1.0D - Math.sqrt(d0) / 4.0D;
                        }

                        for(MobEffectInstance mobeffectinstance : pEffectInstances) {
                            MobEffect mobeffect = mobeffectinstance.getEffect();
                            if (mobeffect.isInstantenous()) {
                                mobeffect.applyInstantenousEffect(this, this.getOwner(), livingentity, mobeffectinstance.getAmplifier(), d1);
                            } else {
                                int i = mobeffectinstance.mapDuration((p_267930_) -> {
                                    return (int)(d1 * (double)p_267930_ + 0.5D);
                                });
                                MobEffectInstance mobeffectinstance1 = new MobEffectInstance(mobeffect, i, mobeffectinstance.getAmplifier(), mobeffectinstance.isAmbient(), mobeffectinstance.isVisible());
                                if (!mobeffectinstance1.endsWithin(20)) {
                                    livingentity.addEffect(mobeffectinstance1, entity);
                                }
                            }
                        }
                    }
                }
            }
        }

    }
    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide && --this.duration <= 0) {
            this.remove(RemovalReason.DISCARDED);
        } else if (this.level().isClientSide) {
            this.level().addParticle(ParticleTypes.ENTITY_EFFECT, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }


    public float getThrowStrength() {
        return throwStrength;
    }
}
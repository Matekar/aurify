package pl.edu.agh.fis.is.io.aurify.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class Utils {
    public static void lightning(LivingEntity livingEntity, ServerLevel serverLevel, int amplifier){
        lightning(livingEntity, serverLevel);
        for (int i = 0; i < amplifier; i++){
            Random random = new Random();
            BlockPos entityPos = livingEntity.blockPosition();
            BlockPos blockPos = entityPos.offset(random.nextInt(amplifier) - (amplifier / 2), random.nextInt(amplifier) - (amplifier / 2), random.nextInt(amplifier) - (amplifier / 2));
            LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
            lightningBolt.moveTo(Vec3.atBottomCenterOf(blockPos));
            lightningBolt.setCause(livingEntity instanceof ServerPlayer ? (ServerPlayer) livingEntity : null);
            serverLevel.addFreshEntity(lightningBolt);
        }
    }

    public static void lightning(LivingEntity livingEntity, ServerLevel serverLevel){
        BlockPos entityPos = livingEntity.blockPosition();
        LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
        lightningBolt.moveTo(Vec3.atBottomCenterOf(entityPos));
        lightningBolt.setCause(livingEntity instanceof ServerPlayer ? (ServerPlayer) livingEntity : null);
        serverLevel.addFreshEntity(lightningBolt);
    }
}

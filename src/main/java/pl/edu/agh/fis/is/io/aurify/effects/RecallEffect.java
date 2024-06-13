package pl.edu.agh.fis.is.io.aurify.effects;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RecallEffect extends InstantenousMobEffect {
    final private ResourceKey<Level> assignedLevel;
    public RecallEffect(MobEffectCategory mobEffectCategory, int color, ResourceKey<Level> assignedLevel) {
        super(mobEffectCategory, color);
        this.assignedLevel = assignedLevel;
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity pSource, @Nullable Entity pIndirectSource, @NotNull LivingEntity pLivingEntity, int pAmplifier, double pHealth) {
        if (!pLivingEntity.level().isClientSide() && pLivingEntity.level().dimension() == assignedLevel) {
            if (pLivingEntity instanceof ServerPlayer player) {
                Level world = player.level();
                ResourceKey<Level> playerSpawnPointLevel = player.getRespawnDimension();
                BlockPos respawnPos = player.getRespawnPosition();

                //TODO: Najprawdopodobniej te ify można uprościć
                if (assignedLevel == Level.END && world.dimension() == Level.END) {
                    player.changeDimension((ServerLevel) world);
                    return;
                }

                if (playerSpawnPointLevel != assignedLevel) {
                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200));
                    return;
                }

                if (respawnPos != null)
                    player.teleportTo(respawnPos.getX(), respawnPos.getY(), respawnPos.getZ());
                else if (assignedLevel != Level.NETHER) {
                    player.teleportTo(world.getSharedSpawnPos().getX(), world.getSharedSpawnPos().getY(), world.getSharedSpawnPos().getZ());
                }
            }
        }

        super.applyInstantenousEffect(pSource, pIndirectSource, pLivingEntity, pAmplifier, pHealth);
    }
}

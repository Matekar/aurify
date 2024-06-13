package pl.edu.agh.fis.is.io.aurify.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;
import pl.edu.agh.fis.is.io.aurify.effects.AUEffects;

public class PoisonSwordItem extends SwordItem {

    public PoisonSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack pStack, @NotNull LivingEntity pTarget, @NotNull LivingEntity pAttacker){
        pTarget.addEffect(new MobEffectInstance(new MobEffectInstance(AUEffects.FATAL_POISON.get(), 400)), pAttacker);

        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}

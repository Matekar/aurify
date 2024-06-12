package pl.edu.agh.fis.is.io.aurify.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFood {
    public static final FoodProperties VITALITY_NECTAR =
            new FoodProperties.Builder()
                    .nutrition(10)
                    .saturationMod(0.2f)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 50), 1.0f).alwaysEat()
                    .build();

    public static final FoodProperties MONSTER_REMNANT =
            new FoodProperties.Builder()
                    .nutrition(10)
                    .saturationMod(0.2f)
                    .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 200, 50), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 2), 1.0F)
                    .alwaysEat()
                    .build();
}

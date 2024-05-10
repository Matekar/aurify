package pl.edu.agh.fis.is.io.aurify.utils;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import pl.edu.agh.fis.is.io.aurify.potions.AUPotions;

@Mod.EventBusSubscriber(modid = "aurify", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AUPotionBrewing {

    @SubscribeEvent
    public static void onPotionBrew(PotionBrewEvent.Post event) {
        if (isSplashIngredient(event.getItem(3))) {
            for (int i = 0; i <= 2; i++) {
                if (isCustomPotion(event.getItem(i))) {
                    event.setItem(i, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD));
                }
            }
        }
    }

    // TODO: Ogólnie to będzie do uogólnienia,
    // będzie można zrobić osobny rejestr dla tych potek
    // i sprawdzać czy ta wykorzystywana do niego należy
    private static boolean isCustomPotion(ItemStack itemStack) {
        if (PotionUtils.getPotion(itemStack) == AUPotions.RECALL_POTION.get()) return true;
        if (PotionUtils.getPotion(itemStack) == AUPotions.BEGINNING_POTION.get()) return true;
        return PotionUtils.getPotion(itemStack) == AUPotions.ANCHORED_RECALL_POTION.get();
    }

    private static boolean isSplashIngredient(ItemStack itemStack) {
        return itemStack.getItem() == Items.GUNPOWDER;
    }
}
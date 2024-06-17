package pl.edu.agh.fis.is.io.aurify.item;

import pl.edu.agh.fis.is.io.aurify.AurifyMod;
import pl.edu.agh.fis.is.io.aurify.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import pl.edu.agh.fis.is.io.aurify.potion.ModPotions;

public class ModCreativeModTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AurifyMod.MODID);

    public static final RegistryObject<CreativeModeTab> AURIFY_TAB = CREATIVE_MODE_TABS.register("aurify_tab", () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.HERB.get()))
            .title(Component.translatable("creativetab.aurify_tab"))
            .displayItems((pParameters, pOutput)->{
                pOutput.accept(ModBlocks.BREWING_CAULDRON.get());
                pOutput.accept(ModBlocks.NAUTICAL_CAULDRON.get());
                pOutput.accept(ModBlocks.TUNGSTEN_CAULDRON.get());

                pOutput.accept(ModItems.HERB.get());
                pOutput.accept(ModItems.SICKLE.get());
                pOutput.accept(ModItems.TUNGSTEN_INGOT.get());
                pOutput.accept(ModItems.RAW_TUNGSTEN_ORE.get());
                pOutput.accept(ModBlocks.HERB_PLANT.get());
                pOutput.accept(ModBlocks.TUNGSTEN_BLOCK.get());
                pOutput.accept(ModBlocks.TUNGSTEN_ORE_BLOCK.get());

                pOutput.accept(ModItems.VITALITY_NECTAR.get());
                pOutput.accept(ModItems.MONSTER_REMNANT.get());
                pOutput.accept(ModItems.AMBROSIA.get());
                pOutput.accept(ModItems.DARK_POISON.get());
                pOutput.accept(ModItems.END_HEART.get());
                pOutput.accept(ModItems.EXPLOSIVE_ACCELERATOR.get());
                pOutput.accept(ModItems.MYSTICAL_COMPOUND.get());

                pOutput.accept(ModItems.POWERSHELL.get());

                pOutput.accept(ModItems.POISON_SWORD.get());

                pOutput.accept(ModPotions.BLINDNESS_POTION.get());
                pOutput.accept(ModPotions.RECALL_POTION.get());
                pOutput.accept(ModPotions.ANCHORED_RECALL_POTION.get());
                pOutput.accept(ModPotions.BEGINNING_POTION.get());
                pOutput.accept(ModPotions.GRAVITY_POTION.get());
                pOutput.accept(ModPotions.THUNDEROUS_POTION.get());
                pOutput.accept(ModPotions.EXPLOSIVE_POTION.get());
                pOutput.accept(ModPotions.FATAL_POISON_POTION.get());
                pOutput.accept(ModPotions.DOLPHINS_GRACE_POTION.get());
            })
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

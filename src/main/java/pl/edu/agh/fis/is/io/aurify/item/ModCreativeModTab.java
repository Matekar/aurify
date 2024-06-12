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

public class ModCreativeModTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AurifyMod.MODID);

    public static final RegistryObject<CreativeModeTab> AURIFY_TAB = CREATIVE_MODE_TABS.register("aurify_tab", () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.HERB.get()))
            .title(Component.translatable("creativetab.aurify_tab"))
            .displayItems((pParameters, pOutput)->{
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
            })
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

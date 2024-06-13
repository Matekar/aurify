package pl.edu.agh.fis.is.io.aurify.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.edu.agh.fis.is.io.aurify.AurifyMod;
import pl.edu.agh.fis.is.io.aurify.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AurifyMod.MODID);

    public static final RegistryObject<BlockEntityType<AUCauldronEntity>> BREWING_CAULDRON_ENTITY =
            BLOCK_ENTITIES.register("brewing_cauldron",
                    () -> BlockEntityType.Builder.of(AUCauldronEntity::new, ModBlocks.BREWING_CAULDRON.get(), ModBlocks.NAUTICAL_CAULDRON.get(), ModBlocks.TUNGSTEN_CAULDRON.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}

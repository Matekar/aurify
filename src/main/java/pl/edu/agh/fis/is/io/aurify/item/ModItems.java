package pl.edu.agh.fis.is.io.aurify.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pl.edu.agh.fis.is.io.aurify.AurifyMod;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.edu.agh.fis.is.io.aurify.AurifyMod;
import pl.edu.agh.fis.is.io.aurify.item.custom.PoisonSwordItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AurifyMod.MODID);

    public static final RegistryObject<Item> POISON_SWORD = ITEMS.register("poison_sword",
            () -> new PoisonSwordItem(Tiers.GOLD, 2, 3f, new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}

package pl.edu.agh.fis.is.io.aurify.item;

import pl.edu.agh.fis.is.io.aurify.AurifyMod;
import pl.edu.agh.fis.is.io.aurify.item.custom.SickleItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AurifyMod.MODID);

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HERB = ITEMS.register("herb",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SICKLE = ITEMS.register("sickle",
            ()-> new SickleItem(new Item.Properties().durability(128)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

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

    public static final RegistryObject<Item> HERB = ITEMS.register("herb",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TUNGSTEN_INGOT = ITEMS.register("tungsten_ingot",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_TUNGSTEN_ORE = ITEMS.register("raw_tungsten_ore",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SICKLE = ITEMS.register("sickle",
            ()-> new SickleItem(new Item.Properties().durability(128)));

//////////////////////////////
    public static final RegistryObject<Item> VITALITY_NECTAR = ITEMS.register("vitality_nectar",
            ()-> new Item(new Item.Properties().food(ModFood.VITALITY_NECTAR)));

    public static final RegistryObject<Item> MONSTER_REMNANT = ITEMS.register("monster_remnant",
            ()-> new Item(new Item.Properties().food(ModFood.MONSTER_REMNANT)));

    public static final RegistryObject<Item> AMBROSIA = ITEMS.register("ambrosia",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DARK_POISON = ITEMS.register("dark_poison",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> END_HEART = ITEMS.register("end_heart",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EXPLOSIVE_ACCELERATOR = ITEMS.register("explosive_accelerator",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MYSTICAL_COMPOUND = ITEMS.register("mystical_compound",
            ()-> new Item(new Item.Properties()));

////////////////////////////////

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

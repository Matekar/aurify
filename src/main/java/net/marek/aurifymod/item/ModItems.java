package net.marek.aurifymod.item;

import net.marek.aurifymod.AurifyMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AurifyMod.MOD_ID);

    public static final RegistryObject<Item> MAGICDUST = ITEMS.register("magic_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> OCEANICCORE = ITEMS.register("oceanic_core",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWERSHELL = ITEMS.register("power_shell",
            () -> new Item(new Item.Properties()));
    public static void register(IEventBus eventbus){
        ITEMS.register(eventbus);
    }
}

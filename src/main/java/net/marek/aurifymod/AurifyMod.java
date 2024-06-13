<<<<<<<< Updated upstream:src/main/java/pl/edu/agh/fis/is/io/aurify/AurifyMod.java
package pl.edu.agh.fis.is.io.aurify;

import com.mojang.logging.LogUtils;
========
package net.marek.aurifymod;

import com.mojang.logging.LogUtils;
import net.marek.aurifymod.item.ModItems;
import net.marek.aurifymod.loot.ModLootModifiers;
import net.minecraft.world.item.CreativeModeTab;
>>>>>>>> Stashed changes:src/main/java/net/marek/aurifymod/AurifyMod.java
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import pl.edu.agh.fis.is.io.aurify.effects.AUEffects;
import pl.edu.agh.fis.is.io.aurify.item.ModItems;
import pl.edu.agh.fis.is.io.aurify.potions.AUPotions;

// The value here should match an entry in the META-INF/mods.toml file
<<<<<<<< Updated upstream:src/main/java/pl/edu/agh/fis/is/io/aurify/AurifyMod.java
@Mod(AurifyMod.MODID)
public class AurifyMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "aurify";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public AurifyMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //Register potions
        AUEffects.register(modEventBus);
        AUPotions.register(modEventBus);

========
@Mod(AurifyMod.MOD_ID)
public class AurifyMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "aurifymod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public AurifyMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
>>>>>>>> Stashed changes:src/main/java/net/marek/aurifymod/AurifyMod.java
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

<<<<<<<< Updated upstream:src/main/java/pl/edu/agh/fis/is/io/aurify/AurifyMod.java
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        // Register Items
        ModItems.register(modEventBus);
========
        ModLootModifiers.register(modEventBus);
>>>>>>>> Stashed changes:src/main/java/net/marek/aurifymod/AurifyMod.java
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
<<<<<<<< Updated upstream:src/main/java/pl/edu/agh/fis/is/io/aurify/AurifyMod.java
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.MAGICDUST);
            event.accept(ModItems.OCEANICCORE);
========
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.MAGICDUST);
            event.accept(ModItems.OCEANICCORE);
            event.accept(ModItems.POWERSHELL);
>>>>>>>> Stashed changes:src/main/java/net/marek/aurifymod/AurifyMod.java
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}

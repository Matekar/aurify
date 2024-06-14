package pl.edu.agh.fis.is.io.aurify.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import pl.edu.agh.fis.is.io.aurify.AurifyMod;


@Mod.EventBusSubscriber(modid = AurifyMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();



        generator.addProvider(event.includeServer(), new ModGlobalLootModifiersProvider(packOutput));
    }
}
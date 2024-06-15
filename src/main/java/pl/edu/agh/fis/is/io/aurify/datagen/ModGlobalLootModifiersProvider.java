package pl.edu.agh.fis.is.io.aurify.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import pl.edu.agh.fis.is.io.aurify.AurifyMod;
import pl.edu.agh.fis.is.io.aurify.item.ModItems;
import pl.edu.agh.fis.is.io.aurify.loot.AddItemModifier;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, AurifyMod.MODID);
    }

    @Override
    protected void start() {


        add("power_shell_from_guardian", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/guardian")).build() }, ModItems.POWERSHELL.get()));



    }
}
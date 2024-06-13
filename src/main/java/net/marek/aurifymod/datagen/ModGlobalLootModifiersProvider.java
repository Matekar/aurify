package net.marek.aurifymod.datagen;

import net.marek.aurifymod.AurifyMod;
import net.marek.aurifymod.item.ModItems;
import net.marek.aurifymod.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, AurifyMod.MOD_ID);
    }

    @Override
    protected void start() {
        add("power_shell_from_guardian", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("entities/guardian")).build() }, ModItems.POWERSHELL.get()));

        }
    }


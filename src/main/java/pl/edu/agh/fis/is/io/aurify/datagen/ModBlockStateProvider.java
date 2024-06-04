package pl.edu.agh.fis.is.io.aurify.datagen;

import pl.edu.agh.fis.is.io.aurify.AurifyMod;
import pl.edu.agh.fis.is.io.aurify.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
        public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
            super(output, AurifyMod.MODID, exFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {
            //blockWithItem(ModBlocks.SAPPHIRE_BLOCK);

            simpleBlockWithItem(ModBlocks.HERB_PLANT.get(), models().cross(blockTexture(ModBlocks.HERB_PLANT.get()).getPath(),
                    blockTexture(ModBlocks.HERB_PLANT.get())).renderType("cutout"));
            simpleBlockWithItem(ModBlocks.POTTED_HERB_PLANT.get(), models().singleTexture("potted_herb_plant", new ResourceLocation("flower_pot_cross"), "plant",
                    blockTexture(ModBlocks.HERB_PLANT.get())).renderType("cutout"));
        }




        private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
            simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
        }
    }

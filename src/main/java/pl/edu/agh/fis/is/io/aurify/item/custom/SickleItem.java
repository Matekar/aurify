package pl.edu.agh.fis.is.io.aurify.item.custom;


import net.minecraft.core.HolderSet;
import pl.edu.agh.fis.is.io.aurify.AurifyMod;
import pl.edu.agh.fis.is.io.aurify.block.ModBlocks;
import pl.edu.agh.fis.is.io.aurify.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.BlockTypes;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SickleItem extends Item {

    public SickleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext){
        if(!pContext.getLevel().isClientSide()){
            Level level = pContext.getLevel();
            BlockPos pos = pContext.getClickedPos();
            BlockState state = pContext.getLevel().getBlockState(pos);
            Player player = pContext.getPlayer();
            assert ModBlocks.HERB_PLANT.getKey() != null;
            if(state.is(ModBlocks.HERB_PLANT.get())){
                level.destroyBlock(pos, false);
                assert player != null;
                player.addItem(new ItemStack(ModItems.HERB.get().asItem()));
                pContext.getItemInHand().hurtAndBreak(1, player, p -> p.broadcastBreakEvent(pContext.getHand()));
            }
        }
        return InteractionResult.SUCCESS;
    }
}

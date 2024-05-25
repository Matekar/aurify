package pl.edu.agh.fis.is.io.aurify.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BottleItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.edu.agh.fis.is.io.aurify.block.entity.AUCauldronEntity;

import java.util.logging.Logger;

public class AUCauldron extends BaseEntityBlock {
    private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape SHAPE = Shapes.join(Shapes.block(), Shapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), INSIDE), BooleanOp.ONLY_FIRST);

    public AUCauldron(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public VoxelShape getInteractionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return INSIDE;
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void entityInside(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Entity pEntity) {
        if (!pLevel.isClientSide && pEntity instanceof ItemEntity) {
            ItemEntity itemEntity = (ItemEntity) pEntity;
            ItemStack itemStack = itemEntity.getItem();
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof AUCauldronEntity) {
                if (((AUCauldronEntity) blockEntity).getFluidHandler().getFluidInTank(0).getAmount() > 0) {
                    itemEntity.discard();
                    AUCauldronEntity cauldronEntity = (AUCauldronEntity) blockEntity;
                    ItemStack remaining = cauldronEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).map(handler -> {
                        ItemStack tempIS = itemStack.copy();

                        for (int i = 0; i < handler.getSlots(); i++) {
                            tempIS = handler.insertItem(i, tempIS, false);
                            if (tempIS.isEmpty()) {
                                return ItemStack.EMPTY;
                            }
                        }

                        return tempIS;
                    }).orElse(itemStack.copy());

                    // Drop remaining items if there are any
                    if (!remaining.isEmpty()) {
                        ItemEntity remainingItemEntity = new ItemEntity(pLevel, pPos.getX() + 0.5, pPos.getY() + 1, pPos.getZ() + 0.5, remaining);
                        pLevel.addFreshEntity(remainingItemEntity);
                    }
                }
            }
        }
        super.entityInside(pState, pLevel, pPos, pEntity);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new AUCauldronEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level pLevel, @NotNull BlockState pState, @NotNull BlockEntityType<T> pBlockEntityType) {
        return super.getTicker(pLevel, pState, pBlockEntityType);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getItemInHand(hand);
        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof AUCauldronEntity) {
            AUCauldronEntity cauldronEntity = (AUCauldronEntity) blockEntity;

            if (!heldItem.isEmpty() && heldItem.getItem() instanceof BucketItem) {
                Fluid bucketFluid = ((BucketItem) heldItem.getItem()).getFluid();

                if (bucketFluid != Fluids.EMPTY) {
                    int fillAmount = 1000; // Amount of fluid in one bucket (1000 mB)
                    FluidStack fluidStack = new FluidStack(bucketFluid, fillAmount);

                    IFluidHandler fluidHandler = cauldronEntity.getFluidHandler();
                    int filled = fluidHandler.fill(fluidStack, IFluidHandler.FluidAction.EXECUTE);

                    if (filled == fillAmount) {
                        if (!player.isCreative()) {
                            player.setItemInHand(hand, new ItemStack(Items.BUCKET));
                        }
                        if (fluidStack.getFluid() == Fluids.LAVA) {
                            level.playSound(null, pos, SoundEvents.BUCKET_EMPTY_LAVA, SoundSource.BLOCKS, 1.0F, 1.0F);
                        } else {
                            level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                        }
                        return InteractionResult.SUCCESS;
                    } else {
                        return InteractionResult.FAIL;
                    }
                }
            }

            if (!heldItem.isEmpty() && heldItem.getItem() == Items.GLASS_BOTTLE && cauldronEntity.getStoredPotion() != null) {
                int amount = cauldronEntity.getFluidHandler().getFluidInTank(0).getAmount();

                if (heldItem.getCount() != 1) heldItem.setCount(heldItem.getCount() - 1);
                else player.setItemInHand(hand, ItemStack.EMPTY);

                cauldronEntity.getFluidHandler().drain(333, IFluidHandler.FluidAction.EXECUTE);

                BlockPos above = pos.above();

                ItemStack potionStack = new ItemStack(Items.POTION);
                PotionUtils.setPotion(potionStack, cauldronEntity.getStoredPotion());
                ItemEntity potionEntity = new ItemEntity(
                        level,
                        above.getX() + 0.5,
                        above.getY(),
                        above.getZ() + 0.5,
                        potionStack
                );

                potionEntity.setDeltaMovement(0, 0, 0);
                level.addFreshEntity(potionEntity);

                if (cauldronEntity.getFluidHandler().getFluidInTank(0).getAmount() < 333) {
                    cauldronEntity.getFluidHandler().drain(333, IFluidHandler.FluidAction.EXECUTE);
                    cauldronEntity.emptyPotion();
                    cauldronEntity.clearContent();
                }

                return InteractionResult.SUCCESS;
            }

            if (heldItem.getItem() == Items.GLASS_BOTTLE) {
                return InteractionResult.CONSUME;
            }

            if (heldItem.getItem() == Items.AIR) {
                IFluidHandler fluidHandler = ((AUCauldronEntity) blockEntity).getFluidHandler();
                int fluidAmountInCauldron = fluidHandler.getFluidInTank(0).getAmount();

                if (fluidAmountInCauldron > 0) {
                    fluidHandler.drain(fluidAmountInCauldron, IFluidHandler.FluidAction.EXECUTE);
                    cauldronEntity.emptyPotion();
                    cauldronEntity.clearContent();
                    level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    return InteractionResult.SUCCESS;
                }

                return InteractionResult.FAIL;
            }
        }

        return super.use(state, level, pos, player, hand, hit);
    }
}

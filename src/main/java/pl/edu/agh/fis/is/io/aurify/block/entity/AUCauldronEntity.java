package pl.edu.agh.fis.is.io.aurify.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AUCauldronEntity extends BlockEntity implements Container {
    public static final int INVENTORY_SIZE = 2;
    private final ItemStackHandler inventory = new ItemStackHandler(INVENTORY_SIZE) {
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
//            ItemStack remaining = stack.copy();
//            remaining.setCount(1); // Only allow one item to be inserted
//
//            for (int i = 0; i < getSlots(); i++) {
//                ItemStack currentStack = getStackInSlot(i);
//                if (currentStack.isEmpty()) {
//                    if (!simulate) {
//                        setStackInSlot(i, remaining);
//                    }
//                    return ItemStack.EMPTY;
//                } else if (ItemStack.isSameItemSameTags(currentStack, remaining)) {
//                    int space = Math.min(getSlotLimit(i) - currentStack.getCount(), remaining.getCount());
//                    if (space > 0) {
//                        if (!simulate) {
//                            currentStack.grow(space);
//                            setStackInSlot(i, currentStack);
//                        }
//                        remaining.shrink(space);
//                    }
//                    if (remaining.isEmpty()) {
//                        return ItemStack.EMPTY;
//                    }
//                }
//            }
//
//            return remaining;
            return super.insertItem(slot, stack, simulate);
        }
    };

    public AUCauldronEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BREWING_CAULDRON.get(), pos, state);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        inventory.deserializeNBT(pTag.getCompound("Inventory"));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("Inventory", inventory.serializeNBT());
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        Containers.dropContents(this.level, this.worldPosition, this);
    }

    @Override
    public int getContainerSize() {
        return INVENTORY_SIZE;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            if (!inventory.getStackInSlot(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public @NotNull ItemStack getItem(int pSlot) {
        return inventory.getStackInSlot(pSlot);
    }

    @Override
    public @NotNull ItemStack removeItem(int pSlot, int pAmount) {
        return inventory.extractItem(pSlot, pAmount, false);
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int pSlot) {
        ItemStack stack = inventory.getStackInSlot(pSlot);
        inventory.setStackInSlot(pSlot, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setItem(int pSlot, @NotNull ItemStack pStack) {
        inventory.setStackInSlot(pSlot, pStack);
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return true;
    }

    @Override
    public void clearContent() {
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            inventory.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return ModBlockEntities.BREWING_CAULDRON.get();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return LazyOptional.of(() -> inventory).cast();
        }
        return super.getCapability(cap, side);
    }
}

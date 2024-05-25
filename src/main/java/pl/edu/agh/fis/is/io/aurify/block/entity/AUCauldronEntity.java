package pl.edu.agh.fis.is.io.aurify.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.edu.agh.fis.is.io.aurify.potion.ModPotions;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class AUCauldronEntity extends BlockEntity implements Container {
    public static final int INVENTORY_SIZE = 2;
    public static final int FLUID_CAPACITY = 1000;

    private Potion storedPotion = null;
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
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return isItemAllowedInSlot(slot, stack.getItem());
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            ItemStack result = super.insertItem(slot, stack, simulate);
            storedPotion = checkRecipe();

            return result;
        }
    };

    private final FluidTank fluidTank = new FluidTank(FLUID_CAPACITY) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
        }
    };

    private final LazyOptional<IFluidHandler> fluidHandler = LazyOptional.of(() -> fluidTank);
    private final LazyOptional<ItemStackHandler> itemHandler = LazyOptional.of(() -> inventory);

    private final Set<Item> allowedItemSet1 = Set.of(
            Items.FERN,
            Items.NETHER_WART
    );

    private final Set<Item> allowedItemSet2 = Set.of(
            Items.SCULK_SHRIEKER,
            Items.APPLE
    );

    private final Map<Set<Item>, Potion> RecipeMap = Map.of(
            Set.of(Items.NETHER_WART, Items.SCULK_SHRIEKER), ModPotions.DARKNESS_POTION.get(),
            Set.of(Items.FERN, Items.APPLE), Potions.LONG_REGENERATION
            );

    public AUCauldronEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BREWING_CAULDRON.get(), pos, state);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        fluidTank.readFromNBT(pTag.getCompound("FluidTank"));
        inventory.deserializeNBT(pTag.getCompound("Inventory"));

//        if (pTag.getBoolean("Potion")) {
//            checkRecipe();
//        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("FluidTank", fluidTank.writeToNBT(new CompoundTag()));
        pTag.put("Inventory", inventory.serializeNBT());

//        if (storedPotion != null) pTag.putBoolean("Potion", true);
//        else pTag.putBoolean("Potion", false);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();

        fluidHandler.invalidate();
        itemHandler.invalidate();
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

    public IFluidHandler getFluidHandler() {
        return fluidTank;
    }
    public IItemHandler getInventory() { return inventory; }

    public Potion getStoredPotion() { return storedPotion; }
    public void emptyPotion() { storedPotion = null; }

    private boolean isItemAllowedInSlot(int slot, Item item) {
        if (slot == 0) {
            return allowedItemSet1.contains(item);
        }

        if (slot == 1) {
            return  allowedItemSet2.contains(item);
        }

        return false;
    }

    public Potion checkRecipe() {
        Set<Item> inv = new HashSet<>();
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            inv.add(inventory.getStackInSlot(i).getItem());
        }

        return RecipeMap.get(inv);
    }
}

package fr.obelouix.obecraft.block;

import fr.obelouix.obecraft.energy.ObecraftEnergyStorage;
import fr.obelouix.obecraft.registry.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicInteger;

public class IronGeneratorBlockEntity extends BlockEntity {
    public static final int MAX_CAPACITY = 24000; // Max capacity;
    public static final int IRON_GENERATOR_SEND = 250;       // Power to send out per tick
    public static final int IRON_GENERATOR_GENERATE = 50;    // Generation per tick

    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    private final ObecraftEnergyStorage energyStorage = createEnergy();
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);
    private int counter;

    public IronGeneratorBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockRegistry.IRON_GENERATOR_BE.get(), pWorldPosition, pBlockState);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        handler.invalidate();
        energy.invalidate();
    }

    public void tickServer() {

        ItemStack stack = itemHandler.getStackInSlot(0);
        int burnTime = ForgeHooks.getBurnTime(stack, RecipeType.SMELTING);

        if (counter > 0) {
            energyStorage.addEnergy(burnTime / (IRON_GENERATOR_GENERATE * 12));
            counter--;
            setChanged();
        }

        if (counter <= 0) {
            if (burnTime > 0) {
                itemHandler.extractItem(0, 1, false);
                counter = burnTime / 12;
                setChanged();
            }
        }

        BlockState blockState = level.getBlockState(worldPosition);
        if (blockState.getValue(BlockStateProperties.POWERED) != counter > 0) {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, counter > 0),
                    Block.UPDATE_ALL);
        }


        sendOutPower();
    }

    private void sendOutPower() {
        AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
        if (capacity.get() > 0) {
            for (Direction direction : Direction.values()) {
                BlockEntity be = level.getBlockEntity(worldPosition.relative(direction));
                if (be != null) {
                    boolean doContinue = be.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).map(handler -> {
                                if (handler.canReceive()) {
                                    int received = handler.receiveEnergy(Math.min(capacity.get(), IRON_GENERATOR_SEND), false);
                                    capacity.addAndGet(-received);
                                    energyStorage.consumeEnergy(received);
                                    setChanged();
                                    return capacity.get() > 0;
                                } else {
                                    return true;
                                }
                            }
                    ).orElse(true);
                    if (!doContinue) {
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("Inventory")) {
            itemHandler.deserializeNBT(tag.getCompound("Inventory"));
        }
        if (tag.contains("Energy")) {
            energyStorage.deserializeNBT(tag.get("Energy"));
        }
        if (tag.contains("Info")) {
            counter = tag.getCompound("Info").getInt("Counter");
        }
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("Inventory", itemHandler.serializeNBT());
        tag.put("Energy", energyStorage.serializeNBT());

        CompoundTag infoTag = new CompoundTag();
        infoTag.putInt("Counter", counter);
        tag.put("Info", infoTag);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) <= 0) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private ObecraftEnergyStorage createEnergy() {
        return new ObecraftEnergyStorage(MAX_CAPACITY, 0) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }
}

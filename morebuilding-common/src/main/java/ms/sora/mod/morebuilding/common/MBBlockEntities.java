package ms.sora.mod.morebuilding.common;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import ms.sora.mod.morebuilding.common.blockentity.BackpackBlockEntity;
import ms.sora.mod.morebuilding.common.screen.BackpackScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.feature_flags.FeatureFlags;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.math.BlockPos;

/**
 * MBBlockEntities is a manager class of block entities.
 * <p>
 * This class contains block entities, screen handlers and registers of them.
 *
 * @since 0.1.0
 */
@SuppressWarnings("unused")
public final class MBBlockEntities {
    /**
     * A register of block entities
     */
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(MBInfo.MOD_ID, RegistryKeys.BLOCK_ENTITY_TYPE);
    /**
     * A register of screen handlers
     */
    public static final DeferredRegister<ScreenHandlerType<?>> SCREEN_HANDLERS = DeferredRegister.create(MBInfo.MOD_ID, RegistryKeys.SCREEN_HANDLER_TYPE);

    /**
     * Registers block entities from items.
     *
     * @param name   the name of type of block entities
     * @param sup    the block entity supplier that is constructor in most situations
     * @param blocks the block suppliers
     * @param <T>    the block entity type
     *
     * @return the supplier of registered type of block entities
     */
    @SafeVarargs
    private static <T extends BlockEntity, U extends Block> RegistrySupplier<BlockEntityType<T>> register(String name, BiFunction<BlockPos, BlockState, T> sup, RegistrySupplier<U>... blocks) {
        return register(name, () -> BlockEntityType.Builder.create(sup::apply, Arrays.stream(blocks).map(Supplier::get).toList().toArray(Block[]::new)));
    }

    /**
     * Registers block entities from items.
     *
     * @param name  the name of type of block entities
     * @param sup   the block entity supplier that is constructor in most situations
     * @param items the suppliers of what can be converted into a block entity item, intended to register
     * @param <T>   the block entity type
     *
     * @return the supplier of registered type of block entities
     */
    @SafeVarargs
    private static <T extends BlockEntity> RegistrySupplier<BlockEntityType<T>> register(String name, BiFunction<BlockPos, BlockState, T> sup, Supplier<ItemConvertible>... items) {
        return register(name, () -> BlockEntityType.Builder.create(sup::apply, Arrays.stream(items).map(item -> Block.getBlockFromItem(item.get().asItem())).toList().toArray(Block[]::new)));
    }

    /**
     * Registers block entities from builder of block entity type.
     *
     * @param name the name of type of block entities
     * @param sup  the builder supplier to register
     * @param <T>  the block entity type
     *
     * @return the supplier of registered type of block entities
     */
    private static <T extends BlockEntity> RegistrySupplier<BlockEntityType<T>> register(String name, Supplier<BlockEntityType.Builder<T>> sup) {
        return BLOCK_ENTITIES.register(name, () -> sup.get().build(null));
    }

    /**
     * The Backpack
     */
    public static final RegistrySupplier<BlockEntityType<BackpackBlockEntity>> BACKPACK = register("backpack", BackpackBlockEntity::new, MBBlocks.BACKPACK);

    /**
     * A type of screen handler of the Backpack
     */
    public static final RegistrySupplier<ScreenHandlerType<BackpackScreenHandler>> BACKPACK_SCREEN_HANDLER = SCREEN_HANDLERS.register("backpack", () -> new ScreenHandlerType<>(BackpackScreenHandler::new, FeatureFlags.DEFAULT_SET));


}

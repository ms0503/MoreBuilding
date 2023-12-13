package ms.sora.mod.morebuilding.common;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.function.Supplier;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;

/**
 * MBItems is a manager class of items.
 * <p>
 * This class contains
 *
 * @since 0.1.0-beta0
 */
@SuppressWarnings("unused")
public final class MBItems {
    /**
     * A item register
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MBInfo.MOD_ID, RegistryKeys.ITEM);

    private static <T extends Item> RegistrySupplier<T> register(String name, Supplier<T> sup) {
        return ITEMS.register(name, sup);
    }

    @SuppressWarnings("UnstableApiUsage")
    private static RegistrySupplier<Item> register(String name, Item.Settings settings) {
        return register(name, () -> new Item(settings.arch$tab(MBCore.MORE_BUILDING_TAB)));
    }

    private static RegistrySupplier<Item> register(String name) {
        return register(name, new Item.Settings());
    }
}

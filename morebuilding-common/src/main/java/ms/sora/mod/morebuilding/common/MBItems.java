package ms.sora.mod.morebuilding.common;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.function.Supplier;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;

/**
 * More Building items
 */
@SuppressWarnings("unused")
public class MBItems {
    /**
     * Item registry
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MoreBuildingInfo.MOD_ID, RegistryKeys.ITEM);

    private RegistrySupplier<Item> register(String name, Supplier<Item> sup) {
        return ITEMS.register(name, sup);
    }

    private RegistrySupplier<Item> register(String name, Item.Settings settings) {
        return register(name, () -> new Item(settings));
    }
}

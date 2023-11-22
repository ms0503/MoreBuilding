package ms.sora.mod.morebuilding.common;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import java.lang.reflect.InvocationTargetException;
import ms.sora.mod.morebuilding.MoreBuildingCore;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;

/**
 * More Building items
 */
@SuppressWarnings({"UnstableApiUsage", "unchecked", "unused"})
public class MBItems {
    /**
     * Item registry
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MoreBuildingInfo.MOD_ID, RegistryKeys.ITEM);

    private static <T extends Item> RegistrySupplier<Item> register(String name, Item.Settings settings) {
        return ITEMS.register(name, () -> {
            try {
                return ((Class<T>) Item.class).getDeclaredConstructor(Item.Settings.class).newInstance(settings.arch$tab(MoreBuildingCore.MORE_BUILDING_TAB));
            } catch(InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static <T extends Item> RegistrySupplier<Item> register(String name) {
        return register(name, new Item.Settings());
    }
}

package ms.sora.mod.morebuilding.common.blockentity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

/**
 * A simple {@code Inventory} implementation with only default methods + an item list getter.
 * <p>
 * Originally by Juuz
 */
@SuppressWarnings("unused")
public interface ImplementedInventory extends Inventory {
    /**
     * Creates an inventory from the item list.
     *
     * @param items the item list
     *
     * @return the inventory
     */
    static ImplementedInventory of(DefaultedList<ItemStack> items) {
        return () -> items;
    }

    /**
     * Creates a new inventory with the specified size.
     *
     * @param size the size of this inventory
     *
     * @return the inventory
     */
    static ImplementedInventory ofSize(int size) {
        return of(DefaultedList.ofSize(size, ItemStack.EMPTY));
    }

    /**
     * Retrieves the item list of this inventory. Must return the same instance every time it's called.
     *
     * @return the item list of this inventory
     */
    DefaultedList<ItemStack> getItems();

    /**
     * Returns the inventory size.
     *
     * @return the inventory size
     */
    @Override
    default int size() {
        return getItems().size();
    }

    /**
     * Checks if the inventory is empty.
     *
     * @return {@code true} if this inventory has only empty stacks, {@code false} otherwise
     */
    @Override
    default boolean isEmpty() {
        for(int i = 0; i < size(); i++) {
            ItemStack stack = getStack(i);
            if(!stack.isEmpty()) return false;
        }
        return true;
    }

    /**
     * Retrieves the item in the slot.
     *
     * @param slot the number of slot
     *
     * @return the item stack
     */
    @Override
    default ItemStack getStack(int slot) {
        return getItems().get(slot);
    }

    /**
     * Removes items from an inventory slot.
     *
     * @param slot   the slot to remove from
     * @param amount how many items to remove. If there are fewer items in the slot than what are requested, takes all
     *               items in that slot.
     *
     * @return the removed item stack
     */
    @Override
    default ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(getItems(), slot, amount);
        if(!result.isEmpty()) markDirty();
        return result;
    }

    /**
     * Removes all items from an inventory slot.
     *
     * @param slot the slot to remove from
     *
     * @return the removed item stack
     */
    @Override
    default ItemStack removeStack(int slot) {
        return Inventories.removeStack(getItems(), slot);
    }

    /**
     * Replaces the current stack in an inventory slot with the provided stack.
     *
     * @param slot  the inventory slot of which to replace the item stack
     * @param stack the replacing item stack. If the stack is too big for this inventory
     *              ({@link Inventory#getMaxCountPerStack()}), it gets resized to this inventory's maximum amount.
     */
    @Override
    default void setStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if(stack.getMaxCount() < stack.getCount()) stack.setCount(stack.getMaxCount());
    }

    /**
     * Clears the inventory.
     */
    @Override
    default void clear() {
        getItems().clear();
    }

    /**
     * Marks the state as dirty. Must be called after changes in the inventory, so that the game can properly save the
     * inventory contents and notify neighboring blocks of inventory changes.
     */
    @Override
    default void markDirty() {
        // Override if you want behavior.
    }

    /**
     * Checks if player can use the inventory.
     *
     * @param player the player
     *
     * @return {@code true} if the player can use the inventory, {@code false} otherwise
     */
    @Override
    default boolean canPlayerUse(PlayerEntity player) {
        return true;
    }
}

package ms.sora.mod.morebuilding.common.screen;

import ms.sora.mod.morebuilding.common.MBBlockEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

/**
 * BackpackScreenHandler is a class of screen handler for the Backpack.
 * <p>
 * This class contains properties and behaviors of screen handler for the Backpack.
 *
 * @since 0.1.0
 */
public class BackpackScreenHandler extends ScreenHandler {
    private final Inventory INVENTORY;

    /**
     * Constructs a screen handler with empty inventory.
     *
     * @param syncId    the sync id
     * @param inventory the player inventory
     */
    public BackpackScreenHandler(int syncId, PlayerInventory inventory) {
        this(syncId, inventory, new SimpleInventory(9));
    }

    /**
     * Constructs a screen handler.
     *
     * @param syncId          the sync id
     * @param playerInventory the player inventory
     * @param inventory       the inventory of this
     */
    public BackpackScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(MBBlockEntities.BACKPACK_SCREEN_HANDLER.get(), syncId);
        checkSize(inventory, 9);
        INVENTORY = inventory;
        inventory.onOpen(playerInventory.player);
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++) addSlot(new Slot(inventory, j + i * 3, 62 + j * 18, 17 + i * 18));
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 9; j++) addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        for(int i = 0; i < 9; i++) addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
    }

    /**
     * Checks if player can use this.
     *
     * @param player the player
     *
     * @return {@code true} if player can use this, {@code false} otherwise
     */
    @Override
    public boolean canUse(PlayerEntity player) {
        return INVENTORY.canPlayerUse(player);
    }

    /**
     * Does quick-transferring items.
     *
     * @param player    the player
     * @param fromIndex the slot index
     *
     * @return the slot item
     */
    @Override
    public ItemStack quickTransfer(PlayerEntity player, int fromIndex) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = slots.get(fromIndex);
        if(slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if(fromIndex < INVENTORY.size()) {
                if(!insertItem(originalStack, INVENTORY.size(), slots.size(), true)) return ItemStack.EMPTY;
            } else if(!insertItem(originalStack, 0, INVENTORY.size(), false)) return ItemStack.EMPTY;
            if(originalStack.isEmpty()) slot.setStack(ItemStack.EMPTY);
            else slot.markDirty();
        }
        return newStack;
    }
}

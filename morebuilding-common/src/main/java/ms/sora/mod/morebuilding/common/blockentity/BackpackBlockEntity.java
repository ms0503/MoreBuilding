package ms.sora.mod.morebuilding.common.blockentity;

import ms.sora.mod.morebuilding.common.MBBlockEntities;
import ms.sora.mod.morebuilding.common.MBInfo;
import ms.sora.mod.morebuilding.common.screen.BackpackScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

/**
 * BackpackBlockEntity is a class of block entity of the Backpack.
 * <p>
 * This class contains properties and behaviors of block entity of the Backpack.
 *
 * @since 0.1.0
 */
@SuppressWarnings("unused")
public class BackpackBlockEntity extends LootableContainerBlockEntity implements ImplementedInventory, Nameable, NamedScreenHandlerFactory {
    /**
     * A number of columns
     */
    public static final int COLUMNS = 3;

    /**
     * A number of rows
     */
    public static final int ROWS = 3;

    /**
     * A container size
     */
    public static final int CONTAINER_SIZE = COLUMNS * ROWS;

    /**
     * A key of what contains items
     */
    public static final String ITEMS_KEY = "Items";

    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);

    /**
     * Constructs a block entity of the Backpack.
     *
     * @param pos   the position of Backpack block
     * @param state the state of Backpack block
     */
    public BackpackBlockEntity(BlockPos pos, BlockState state) {
        super(MBBlockEntities.BACKPACK.get(), pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory inventory) {
        return new BackpackScreenHandler(syncId, inventory, this);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(this.getCachedState().getBlock().getTranslationKey());
    }

    /**
     * Reads this block entity's custom data from the given NBT compound.
     *
     * @param nbt the NBT to read from
     */
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.readInventoryNbt(nbt);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if(!this.serializeLootTable(nbt)) Inventories.writeNbt(nbt, this.inventory, false);
    }

    /**
     * Reads inventory from NBT compound.
     *
     * @param nbt the NBT compound
     */
    public void readInventoryNbt(NbtCompound nbt) {
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if(!this.deserializeLootTable(nbt) && nbt.contains("Items", NbtElement.LIST_TYPE))
            Inventories.readNbt(nbt, this.inventory);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("block.%s.backpack".formatted(MBInfo.MOD_ID));
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.getItems();
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inventory = list;
    }
}

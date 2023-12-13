package ms.sora.mod.morebuilding.common.block;

import java.util.List;
import ms.sora.mod.morebuilding.common.MBBlockEntities;
import ms.sora.mod.morebuilding.common.MBBlocks;
import ms.sora.mod.morebuilding.common.MBInfo;
import ms.sora.mod.morebuilding.common.blockentity.BackpackBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

/**
 * BackpackBlock is a block class of the Backpack.
 * <p>
 * This class contains properties and behaviors of the Backpack block.
 *
 * @since 0.1.0
 */
public class BackpackBlock extends BlockWithEntity implements Waterloggable {
    /**
     * An identifier for dynamic drop
     */
    public static final Identifier CONTENTS_DYNAMIC_DROP_ID = new Identifier(MBInfo.MOD_ID, "contents");

    /**
     * A property that specifies which direction this is facing
     */
    public static final DirectionProperty FACING = Properties.FACING;

    /**
     * A property that specifies if this is waterlogged
     */
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    /**
     * Constructs the Backpack block.
     *
     * @param settings the block settings
     */
    public BackpackBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    private static VoxelShape getShape(Direction dir, double[][] base, double[][]... parts) {
        if(parts.length < 1) throw new IllegalArgumentException("Too few parts.");
        if(base.length < 3) throw new IllegalArgumentException("Base part data is invalid.");
        if(base[0].length < 2) throw new IllegalArgumentException("Base part X-coordinate data is invalid.");
        if(base[1].length < 2) throw new IllegalArgumentException("Base part Y-coordinate data is invalid.");
        if(base[2].length < 2) throw new IllegalArgumentException("Base part Z-coordinate data is invalid.");
        VoxelShape shape;
        switch(dir) {
            case EAST ->
                shape = Block.createCuboidShape(base[2][0], base[1][0], 16.0d - base[0][1], base[2][1], base[1][1], 16.0d - base[0][0]);
            case NORTH ->
                shape = Block.createCuboidShape(16.0d - base[0][1], base[1][0], 16.0d - base[2][1], 16.0d - base[0][0], base[1][1], 16.0d - base[2][0]);
            case SOUTH ->
                shape = Block.createCuboidShape(base[0][0], base[1][0], base[2][0], base[0][1], base[1][1], base[2][1]);
            case WEST ->
                shape = Block.createCuboidShape(16.0d - base[2][1], base[1][0], base[0][0], 16.0d - base[2][0], base[1][1], base[0][1]);
            default -> shape = VoxelShapes.fullCube();
        }
        return getShape(dir, shape, parts);
    }

    private static VoxelShape getShape(Direction dir, VoxelShape base, double[][]... parts) {
        if(parts.length < 1) throw new IllegalArgumentException("Too few parts.");
        VoxelShape[] shapes = new VoxelShape[parts.length];
        for(int i = 0; i < parts.length; i++) {
            if(parts[i].length < 3)
                throw new IllegalArgumentException("Part data is invalid. Part number is %d of %d.".formatted(i, parts.length - 1));
            if(parts[i][0].length < 2)
                throw new IllegalArgumentException("Part X-coordinate data is invalid. Part number is %d of %d.".formatted(i, parts.length - 1));
            if(parts[i][1].length < 2)
                throw new IllegalArgumentException("Part Y-coordinate data is invalid. Part number is %d of %d.".formatted(i, parts.length - 1));
            if(parts[i][2].length < 2)
                throw new IllegalArgumentException("Part Z-coordinate data is invalid. Part number is %d of %d.".formatted(i, parts.length - 1));
            switch(dir) {
                case EAST ->
                    shapes[i] = Block.createCuboidShape(parts[i][2][0], parts[i][1][0], 16.0d - parts[i][0][1], parts[i][2][1], parts[i][1][1], 16.0d - parts[i][0][0]);
                case NORTH ->
                    shapes[i] = Block.createCuboidShape(16.0d - parts[i][0][1], parts[i][1][0], 16.0d - parts[i][2][1], 16.0d - parts[i][0][0], parts[i][1][1], 16.0d - parts[i][2][0]);
                case SOUTH ->
                    shapes[i] = Block.createCuboidShape(parts[i][0][0], parts[i][1][0], parts[i][2][0], parts[i][0][1], parts[i][1][1], parts[i][2][1]);
                case WEST ->
                    shapes[i] = Block.createCuboidShape(16.0d - parts[i][2][1], parts[i][1][0], parts[i][0][0], 16.0d - parts[i][2][0], parts[i][1][1], parts[i][0][1]);
                default -> shapes[i] = VoxelShapes.fullCube();
            }
        }
        return VoxelShapes.union(base, shapes);
    }

    /**
     * Creates block entity of the Backpack.
     *
     * @param pos   the position of this block
     * @param state the state of this block
     *
     * @return the block entity of the Backpack
     */
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BackpackBlockEntity(pos, state);
    }

    /**
     * Gives render type of this.
     *
     * @param state the state of this block
     *
     * @return the render type of this
     */
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.isClient) return ActionResult.SUCCESS;
        else if(player.isSpectator()) return ActionResult.CONSUME;
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if(blockEntity instanceof BackpackBlockEntity backpack) {
            player.openHandledScreen(backpack);
            return ActionResult.CONSUME;
        }
        return ActionResult.PASS;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite()).with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
    }

    /**
     * Adds block properties.
     *
     * @param builder the builder
     */
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if(blockEntity instanceof BackpackBlockEntity backpack) {
            if(!world.isClient && player.isCreative() && !backpack.isEmpty()) {
                ItemStack stack = new ItemStack(MBBlocks.BACKPACK.get());
                backpack.writeNbtToStack(stack);
                if(backpack.hasCustomName()) stack.setCustomName(backpack.getCustomName());
                ItemEntity entity = new ItemEntity(world, (double) pos.getX() + 0.5d, (double) pos.getY() + 0.5d, (double) pos.getZ() + 0.5d, stack);
                entity.setToDefaultPickupDelay();
                world.spawnEntity(entity);
            } else backpack.checkLootInteraction(player);
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        BlockEntity blockEntity = builder.getOptionalParameter(LootContextParameters.BLOCK_ENTITY);
        if(blockEntity instanceof BackpackBlockEntity backpack)
            return super.getDroppedStacks(state, builder.withDynamicDrop(CONTENTS_DYNAMIC_DROP_ID, consumer -> {
                for(int i = 0; i < backpack.size(); i++) consumer.accept(backpack.getStack(i));
            }));
        return super.getDroppedStacks(state, builder);
    }

    /**
     * Called when the player placed the block.
     *
     * @param world     the world
     * @param pos       the position of this
     * @param state     the state of this
     * @param placer    the entity who places this
     * @param itemStack the item stack of this
     */
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if(itemStack.hasCustomName()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof BackpackBlockEntity backpack) backpack.setCustomName(itemStack.getName());
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof BackpackBlockEntity) world.updateComparators(pos, state.getBlock());
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        super.appendTooltip(stack, world, tooltip, options);
        NbtCompound nbtCompound = BlockItem.getBlockEntityNbtFromStack(stack);
        if(nbtCompound != null) {
            if(nbtCompound.contains("LootTable", NbtElement.STRING_TYPE)) tooltip.add(Text.literal("???????"));
            if(nbtCompound.contains("Items", NbtElement.LIST_TYPE)) {
                DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(27, ItemStack.EMPTY);
                Inventories.readNbt(nbtCompound, defaultedList);
                int i = 0;
                int j = 0;
                for(ItemStack itemStack : defaultedList)
                    if(!itemStack.isEmpty()) {
                        j++;
                        if(i <= 4) {
                            i++;
                            MutableText mutableText = itemStack.getName().copy();
                            mutableText.append(" x").append(String.valueOf(itemStack.getCount()));
                            tooltip.add(mutableText);
                        }
                    }
                if(0 < j - i)
                    tooltip.add(Text.translatable("container.%s.%s.more".formatted(MBInfo.MOD_ID, MBBlocks.BACKPACK.getId().getPath()), j - i).formatted(Formatting.ITALIC));
            }
        }
    }

    /**
     * Gives outline shape of this.
     *
     * @param state the state of this
     * @param world the world
     * @param pos   the position of this
     * @param ctx   the shape context
     *
     * @return the outline shape
     */
    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(FACING);
        VoxelShape base = getCollisionShape(state, world, pos, ctx);
        double[] strapRightX = { 4.0d, 6.0d };
        double[] strapLeftX = { 10.0d, 12.0d };
        double[] strapY = { 2.0d, 12.0d };
        double[] strapBottomY = { 0.0d, 2.0d };
        double[] strapTopY = { 12.0d, 14.0d };
        double[] strapZ = { 12.0d, 14.0d };
        double[] strapEdgeZ = { 10.0d, 14.0d };
        double[][] strapRight = { strapRightX, strapY, strapZ };
        double[][] strapBottomRight = { strapRightX, strapBottomY, strapEdgeZ };
        double[][] strapTopRight = { strapRightX, strapTopY, strapEdgeZ };
        double[][] strapLeft = { strapLeftX, strapY, strapZ };
        double[][] strapBottomLeft = { strapLeftX, strapBottomY, strapEdgeZ };
        double[][] strapTopLeft = { strapLeftX, strapTopY, strapEdgeZ };
        return getShape(dir, base, strapRight, strapBottomRight, strapTopRight, strapLeft, strapBottomLeft, strapTopLeft);
    }

    /**
     * Gives collision shape of this.
     *
     * @param state the state of this
     * @param world the world
     * @param pos   the position of this
     * @param ctx   the shape context
     *
     * @return the culling shape
     */
    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(FACING);
        double[] baseX = { 2.0d, 14.0d };
        double[] baseY = { 0.0d, 14.0d };
        double[] baseZ = { 2.0d, 10.0d };
        double[][] base = { baseX, baseY, baseZ };
        double[] topX = { 2.0d, 14.0d };
        double[] topY = { 14.0d, 16.0d };
        double[] topZ = { 4.0d, 10.0d };
        double[][] top = { topX, topY, topZ };
        return getShape(dir, base, top);
    }

    /**
     * Checks if this block has comparator output.
     *
     * @param state the state of this block
     *
     * @return {@code true} if this block has comparator output, {@code false} otherwise
     */
    @Override
    @SuppressWarnings("deprecation")
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    /**
     * Gives comparator output.
     *
     * @param state the state of this block
     * @param world the world
     * @param pos   the position of this block
     *
     * @return the comparator output
     */
    @Override
    @SuppressWarnings("deprecation")
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        ItemStack stack = super.getPickStack(world, pos, state);
        world.getBlockEntity(pos, MBBlockEntities.BACKPACK.get()).ifPresent(backpack -> backpack.writeNbtToStack(stack));
        return stack;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if(state.get(WATERLOGGED)) world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
}

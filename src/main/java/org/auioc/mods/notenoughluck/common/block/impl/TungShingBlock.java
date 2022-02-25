package org.auioc.mods.notenoughluck.common.block.impl;

import org.auioc.mods.arnicalib.api.game.block.HBlockMaterial;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TungShingBlock extends HorizontalDirectionalBlock {

    private static final VoxelShape SHAPE_NORTH = Block.box(5, 3, 14, 11, 11, 16);
    private static final VoxelShape SHAPE_SOUTH = Block.box(5, 3, 0, 11, 11, 2);
    private static final VoxelShape SHAPE_WEST = Block.box(14, 3, 5, 16, 11, 11);
    private static final VoxelShape SHAPE_EAST = Block.box(0, 3, 5, 2, 11, 11);

    public TungShingBlock() {
        super(
            BlockBehaviour.Properties
                .of(
                    (new HBlockMaterial())
                        .color(MaterialColor.COLOR_YELLOW)
                        .destroyOnPush()
                        .flammable()
                        .build()
                )
                .noDrops()
        );
        this.registerDefaultState(
            this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case NORTH: {
                return SHAPE_NORTH;
            }
            case SOUTH: {
                return SHAPE_SOUTH;
            }
            case WEST: {
                return SHAPE_WEST;
            }
            case EAST: {
                return SHAPE_EAST;
            }
            default: {
                return Shapes.block();
            }
        }
    }

}

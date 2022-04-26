package fr.obelouix.obecraft.datagen;

import fr.obelouix.obecraft.registry.BlockRegistry;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Arrays;
import java.util.List;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, "obecraft", existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        List.of(BlockRegistry.SILVER_ORE.get()//,
                //BlockRegistry.DEEPSLATE_SILVER_ORE.get()
        ).forEach(this::simpleBlock);

        registerIronGenerator();
    }

    private void registerIronGenerator() {

        ModelFile standardModel = models().cube("iron_generator",
                modLoc("block/iron_machinery_sides"),
                modLoc("block/iron_machinery_sides"),
                modLoc("block/iron_generator_front"),
                modLoc("block/iron_machinery_sides"),
                modLoc("block/iron_machinery_sides"),
                modLoc("block/iron_machinery_sides"));

        ModelFile poweredModel = models().cube("iron_generator",
                modLoc("block/iron_machinery_sides"),
                modLoc("block/iron_machinery_sides"),
                modLoc("block/iron_generator_front"),
                modLoc("block/iron_machinery_sides"),
                modLoc("block/iron_machinery_sides"),
                modLoc("block/iron_machinery_sides"));

        createFacingMultiTextureBlock(BlockRegistry.IRON_GENERATOR.get(), "iron_generator", standardModel);

    }

    private void createMultiTextureBlock(Block block, String name, ResourceLocation down, ResourceLocation up, ResourceLocation north, ResourceLocation south, ResourceLocation east, ResourceLocation west) {
        ModelFile model = models().cube(name, down, up, north, south, east, west);
        simpleBlock(block, model);
    }

    private void createFacingMultiTextureBlock(Block block, String name, ModelFile modelFile) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        for (Direction direction : Direction.values()) {
            applyRotation(builder.partialState().with(BlockStateProperties.FACING, direction)
                    .modelForState().modelFile(modelFile), direction);
        }
    }

    private void applyRotation(ConfiguredModel.Builder<?> builder, Direction direction) {
        switch (direction) {
            case DOWN -> builder.rotationX(90);
            case UP -> builder.rotationX(-90);
            case SOUTH -> builder.rotationY(180);
            case WEST -> builder.rotationY(270);
            case EAST -> builder.rotationY(90);
        }

        builder.addModel();
    }

    private ModelFile createModel(String name, ResourceLocation down, ResourceLocation up, ResourceLocation north, ResourceLocation south, ResourceLocation east, ResourceLocation west) {
        return models().cube(name, down, up, north, south, east, west);
    }

    private List<ModelFile> createModelFiles(ModelFile... modelFiles) {
        return Arrays.stream(modelFiles).toList();
    }
}

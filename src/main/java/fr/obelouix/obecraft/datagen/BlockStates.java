package fr.obelouix.obecraft.datagen;

import fr.obelouix.obecraft.registry.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProvider {
    public BlockStates(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, "obecraft", existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(BlockRegistry.SILVER_ORE.get());
    }
}

package fr.obelouix.obecraft.datagen;

import fr.obelouix.obecraft.registry.BlockRegistry;
import fr.obelouix.obecraft.registry.TagsRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTags extends BlockTagsProvider {
    public BlockTags(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, "obecraft", existingFileHelper);
    }

    @Override
    protected void addTags() {

        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockRegistry.SILVER_ORE.get())
                .add(BlockRegistry.DEEPSLATE_SILVER_ORE.get());

        tag(net.minecraft.tags.BlockTags.NEEDS_IRON_TOOL)
                .add(BlockRegistry.SILVER_ORE.get())
                .add(BlockRegistry.DEEPSLATE_SILVER_ORE.get());

        tag(TagsRegistry.SILVER_ORE)
                .add(BlockRegistry.SILVER_ORE.get())
                .add(BlockRegistry.DEEPSLATE_SILVER_ORE.get());

    }

    @Override
    public String getName() {
        return "Obecraft Tags";
    }
}

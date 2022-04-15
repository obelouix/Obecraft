package fr.obelouix.obecraft.datagen;

import fr.obelouix.obecraft.registry.BlockRegistry;
import fr.obelouix.obecraft.registry.TagsRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTags extends ItemTagsProvider {
    public ItemTags(DataGenerator generator, BlockTags blockTags, ExistingFileHelper existingFileHelper) {
        super(generator, blockTags, "obecraft", existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(TagsRegistry.SILVER_ORE_ITEM)
                .add(BlockRegistry.SILVER_ORE.get().asItem());
    }

    @Override
    public String getName() {
        return "Obecraft Tags";
    }

}

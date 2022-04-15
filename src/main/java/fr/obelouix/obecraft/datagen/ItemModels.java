package fr.obelouix.obecraft.datagen;

import fr.obelouix.obecraft.registry.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {
    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, "obecraft", existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(BlockRegistry.SILVER_ORE.get().getRegistryName().getPath(), modLoc("block/silver_ore"));
    }
}

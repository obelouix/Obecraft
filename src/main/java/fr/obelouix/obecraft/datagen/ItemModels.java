package fr.obelouix.obecraft.datagen;

import fr.obelouix.obecraft.registry.BlockRegistry;
import fr.obelouix.obecraft.registry.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {
    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, "obecraft", existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(BlockRegistry.SILVER_ORE.get().getRegistryName().getPath(), modLoc("block/silver_ore"));
        withExistingParent(ItemRegistry.RAW_SILVER.get().getRegistryName().getPath(), modLoc("item/raw_silver"));
        withExistingParent(ItemRegistry.SILVER_INGOT.get().getRegistryName().getPath(), modLoc("item/silver_ingot"));
    }
}

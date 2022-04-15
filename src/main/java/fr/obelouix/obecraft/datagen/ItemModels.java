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
        registerSingleTexture(ItemRegistry.RAW_SILVER.get().getRegistryName().getPath(), "item/raw_silver");
        registerSingleTexture(ItemRegistry.SILVER_INGOT.get().getRegistryName().getPath(), "item/silver_ingot");
    }

    private void registerSingleTexture(String name, String textureLocation) {
        singleTexture(name, mcLoc("item/generated"), "layer0", modLoc(textureLocation));
    }

}

package fr.obelouix.obecraft.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelProvider;

public class Item extends ItemModelProvider {
    public Item(DataGenerator generator) {
        super(generator, "obecraft", null);
    }

    @Override
    protected void registerModels() {

    }


}

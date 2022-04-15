package fr.obelouix.obecraft.datagen;

import fr.obelouix.obecraft.registry.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;

public class LootTables extends BaseLootTableProvider  {
    public LootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addTables() {
        createSilkTouchTable("silver_ore", BlockRegistry.SILVER_ORE.get(), BlockRegistry.SILVER_ORE.get().asItem(), 1, 3);
    }

}

package fr.obelouix.obecraft.datagen;

import fr.obelouix.obecraft.registry.BlockRegistry;
import fr.obelouix.obecraft.registry.ItemRegistry;
import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {
    public LootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addTables() {
        lootTables.put(BlockRegistry.SILVER_ORE.get(),
                createSilkTouchTable("silver_ore_silktouch", BlockRegistry.SILVER_ORE.get(), ItemRegistry.RAW_SILVER.get(), 1, 4));

        lootTables.put(BlockRegistry.DEEPSLATE_SILVER_ORE.get(),
                createSilkTouchTable("deepslate_silver_ore_silktouch", BlockRegistry.DEEPSLATE_SILVER_ORE.get(), ItemRegistry.RAW_SILVER.get(), 1, 4));
    }

}

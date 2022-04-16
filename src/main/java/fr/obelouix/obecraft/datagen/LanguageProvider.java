package fr.obelouix.obecraft.datagen;

import fr.obelouix.obecraft.registry.BlockRegistry;
import fr.obelouix.obecraft.registry.ItemRegistry;
import net.minecraft.data.DataGenerator;

import java.util.Locale;

public class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider {

    final String language;

    public LanguageProvider(DataGenerator generator, String language) {
        super(generator, "obecraft", language);
        this.language = language;
    }

    @Override
    protected void addTranslations() {
        switch (language.toLowerCase(Locale.ROOT)){
            case "fr_fr", "fr_ca" -> {
                add("itemGroup." + "obecraft_blocks", "blocs d'Obecraft");
                add("itemGroup." + "obecraft_items", "Items d'Obecraft");
                add(BlockRegistry.SILVER_ORE.get(), "Minerai d'argent");
                add(BlockRegistry.DEEPSLATE_SILVER_ORE.get(), "Minerai d'argent des abîmes");
                add(ItemRegistry.RAW_SILVER.get(), "Argent brut");
                add(ItemRegistry.SILVER_INGOT.get(), "Lingot d'argent");
            }
            default -> {
                add("itemGroup." + "obecraft_blocks", "Obecraft blocks");
                add("itemGroup." + "obecraft_items", "Obecraft items");
                add(BlockRegistry.SILVER_ORE.get(), "Silver ore");
                add(BlockRegistry.DEEPSLATE_SILVER_ORE.get(), "Deepslate silver ore");
                add(ItemRegistry.RAW_SILVER.get(), "Raw silver");
                add(ItemRegistry.SILVER_INGOT.get(), "Silver ingot");
            }
        }

    }
}

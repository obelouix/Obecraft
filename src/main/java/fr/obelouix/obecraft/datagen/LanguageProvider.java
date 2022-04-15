package fr.obelouix.obecraft.datagen;

import fr.obelouix.obecraft.registry.BlockRegistry;
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
            }
            default -> {
                add("itemGroup." + "obecraft_blocks", "Obecraft blocks");
                add("itemGroup." + "obecraft_items", "Obecraft items");
                add(BlockRegistry.SILVER_ORE.get(), "Silver ore");
            }
        }

    }
}

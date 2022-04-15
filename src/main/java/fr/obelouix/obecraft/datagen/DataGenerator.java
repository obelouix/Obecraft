package fr.obelouix.obecraft.datagen;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        net.minecraft.data.DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new Recipes(generator));
            generator.addProvider(new LootTables(generator));
            BlockTags blockTags = new BlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new ItemTags(generator, blockTags, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
            generator.addProvider(new BlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new ItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new LanguageProvider(generator, "en_us"));
            generator.addProvider(new LanguageProvider(generator, "fr_fr"));
            generator.addProvider(new LanguageProvider(generator, "fr_ca"));
        }
    }

}

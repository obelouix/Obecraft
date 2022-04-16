package fr.obelouix.obecraft.world.gen;

import fr.obelouix.obecraft.registry.BlockRegistry;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OreGenerator {

    public static Holder<PlacedFeature> SILVER_ORE_OVERWORLD_GEN;
    public static Holder<PlacedFeature> DEEPSLATE_SILVER_ORE_OVERWORLD_GEN;
    public static Holder<PlacedFeature> NETHER_OREGEN;
    public static Holder<PlacedFeature> END_OREGEN;

    public static void registerConfiguredFeatures() {

        OreConfiguration SILVER_ORE_OVERWORLD = overworlConfiguration(BlockRegistry.SILVER_ORE.get(), 4);

        SILVER_ORE_OVERWORLD_GEN = registerPlacedOreFeature("overworld_silver_ore", SILVER_ORE_OVERWORLD, 100,
                VerticalAnchor.absolute(0), VerticalAnchor.absolute(75));


        OreConfiguration DEEPSLATE_SILVER_ORE_OVERWORLD = deepslateConfiguration(BlockRegistry.DEEPSLATE_SILVER_ORE.get(), 4);

        DEEPSLATE_SILVER_ORE_OVERWORLD_GEN = registerPlacedOreFeature("overworld_deepslate_silver_ore", DEEPSLATE_SILVER_ORE_OVERWORLD, 100,
                VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64));
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> @NotNull Holder<PlacedFeature> registerPlacedOreFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
    }

    private static @NotNull Holder<PlacedFeature> registerPlacedOreFeature(String registryName, OreConfiguration oreConfiguration, int countPlacement, VerticalAnchor min, VerticalAnchor max) {
        return registerPlacedOreFeature(registryName, new ConfiguredFeature<>(Feature.ORE, oreConfiguration),
                CountPlacement.of(countPlacement), InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(min, max));
    }

    public static void onBiomeLoadingEvent(@NotNull BiomeLoadingEvent event) {
        switch (event.getCategory()) {
            case NETHER -> {
            }
            case THEEND -> {
            }
            default -> List.of(SILVER_ORE_OVERWORLD_GEN, DEEPSLATE_SILVER_ORE_OVERWORLD_GEN)
                    .forEach(placedFeatureHolder -> event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, placedFeatureHolder));
        }
    }

    private static OreConfiguration overworlConfiguration(@NotNull Block block, int i) {
        return new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, block.defaultBlockState(), i);
    }

    private static OreConfiguration deepslateConfiguration(@NotNull Block block, int i) {
        return new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, block.defaultBlockState(), i);
    }

}

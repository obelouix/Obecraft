package fr.obelouix.obecraft.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagsRegistry {

    public static final TagKey<Block> SILVER_ORE = BlockTags.create(BlockRegistry.SILVER_ORE.getId());
    public static final TagKey<Item> SILVER_ORE_ITEM = ItemTags.create(BlockRegistry.SILVER_ORE.getId());

}

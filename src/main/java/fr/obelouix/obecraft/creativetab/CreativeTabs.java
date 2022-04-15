package fr.obelouix.obecraft.creativetab;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class CreativeTabs {
    
    public static final CreativeModeTab OBECRAFT_ITEMS = new CreativeModeTab("obecraft_items") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return Items.IRON_INGOT.getDefaultInstance();
        }
    };    public static final CreativeModeTab OBECRAFT_BLOCKS = new CreativeModeTab("obecraft_blocks") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return Items.IRON_BLOCK.getDefaultInstance();
        }
    };
    
}

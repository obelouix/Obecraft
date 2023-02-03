package fr.obelouix.obecraft.creativetab;

import fr.obelouix.obecraft.ObecraftBase;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class CreativeTabs {
    
    public static CreativeTabs INSTANCE;
    private static String item_group = "item_group." + ObecraftBase.MOD_ID + ".";

    public static void init(IEventBus bus) {
        INSTANCE = new CreativeTabs();
        bus.addListener(INSTANCE::registerCreativeTab);
    }

    public void registerCreativeTab(CreativeModeTabEvent.Register event) {
        // Blocks Tab
        event.registerCreativeModeTab(ObecraftBase.setID("blocks"), builder ->
        builder.title(Component.translatable(item_group + "blocks"))
        .icon(() -> new ItemStack(Items.IRON_BLOCK))
        .displayItems((enableFlags, populator, hasPermission) -> {
            populator.accept(new ItemStack(Items.IRON_BLOCK));
        }));
    }


    // public static void init() {
    //     INSTANCE = CreativeModeTabEvent.Register((event) -> {
    //         event.registerCrea
    //     });
    // }
    // public static final CreativeModeTab OBECRAFT_ITEMS = new CreativeModeTab("obecraft_items") {
    //     @Override
    //     public @NotNull ItemStack makeIcon() {
    //         return Items.IRON_INGOT.getDefaultInstance();
    //     }
    // };    public static final CreativeModeTab OBECRAFT_BLOCKS = new CreativeModeTab("obecraft_blocks") {
    //     @Override
    //     public @NotNull ItemStack makeIcon() {
    //         return Items.IRON_BLOCK.getDefaultInstance();
    //     }
    // };
    
}

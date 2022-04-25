package fr.obelouix.obecraft.registry;

import fr.obelouix.obecraft.Obecraft;
import fr.obelouix.obecraft.block.IronGeneratorBlock;
import fr.obelouix.obecraft.block.IronGeneratorBlockEntity;
import fr.obelouix.obecraft.block.container.IronGeneratorContainer;
import fr.obelouix.obecraft.creativetab.CreativeTabs;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegistry {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "obecraft");
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "obecraft");
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, "obecraft");
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, "obecraft");
    private static final BlockBehaviour.Properties DEFAULT_ORE_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(2f).requiresCorrectToolForDrops();

    public static final RegistryObject<Block> SILVER_ORE = register("silver_ore", () -> new Block(DEFAULT_ORE_PROPERTIES), CreativeTabs.OBECRAFT_BLOCKS);
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = register("deepslate_silver_ore", () -> new Block(DEFAULT_ORE_PROPERTIES), CreativeTabs.OBECRAFT_BLOCKS);

    public static final RegistryObject<IronGeneratorBlock> IRON_GENERATOR = BLOCKS.register("iron_generator", IronGeneratorBlock::new);
    public static final RegistryObject<BlockEntityType<IronGeneratorBlockEntity>> IRON_GENERATOR_BE = BLOCK_ENTITIES.register("iron_generator", () -> BlockEntityType.Builder.of(IronGeneratorBlockEntity::new, IRON_GENERATOR.get()).build(null));

    public static final RegistryObject<MenuType<IronGeneratorContainer>> IRON_GENERATOR_CONTAINER = CONTAINERS.register("powergen",
            () -> IForgeMenuType.create((windowId, inv, data) -> new IronGeneratorContainer(windowId, data.readBlockPos(), inv, inv.player)));

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        // register a new block here
        Obecraft.getLOGGER().info("HELLO from Register Block");
        //registerBlockItem("test", () -> new BlockItem(test.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> supplier, CreativeModeTab tab) {
        RegistryObject<T> block = BLOCKS.register(name, supplier);
        registerBlockItem(name, block, tab);
        return block;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void registerBus(IEventBus iEventBus){
        BLOCKS.register(iEventBus);
        ITEMS.register(iEventBus);
        BLOCK_ENTITIES.register(iEventBus);
        CONTAINERS.register(iEventBus);
    }

}

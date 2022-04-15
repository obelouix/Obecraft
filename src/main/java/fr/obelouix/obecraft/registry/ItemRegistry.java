package fr.obelouix.obecraft.registry;

import fr.obelouix.obecraft.Obecraft;
import fr.obelouix.obecraft.creativetab.CreativeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistry {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "obecraft");

    public static final RegistryObject<Item> silverIngot = register("silver_ingot", () -> new Item(new Item.Properties().tab(CreativeTabs.OBECRAFT_ITEMS)));
    public static final RegistryObject<Item> rawSilver = register("raw_silver", () -> new Item(new Item.Properties().tab(CreativeTabs.OBECRAFT_ITEMS)));


    public ItemRegistry(IEventBus iEventBus) {
        ITEMS.register(iEventBus);
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
        // register a new block here
        Obecraft.getLOGGER().info("HELLO from Register Item");
    }

    private static RegistryObject<Item> register(String name, Supplier<? extends Item> supplier){
       return ITEMS.register(name, supplier);
    }

}

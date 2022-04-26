package fr.obelouix.obecraft.properties;

import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class Properties {

    public static final IntegerProperty ENERGY_STORAGE_PERCENTAGE = IntegerProperty.create("battery state", 0, 100);

}

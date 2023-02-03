package fr.obelouix.obecraft;

import net.minecraft.resources.ResourceLocation;

public interface ObecraftBase {
    String MOD_NAME = "Obecraft";
    String MOD_ID = "obecraft";
    String MOD_VERSION = "1.0.0";
   
    static Obecraft instance(){
        return Obecraft.instance;
    }
    
    /**
     * This method is used to create a ResourceLocation from a String
     * @param id the id of the ResourceLocation
     * @return a ResourceLocation
     */
    public static ResourceLocation setID(String id) {
        return new ResourceLocation(MOD_ID, id);
    }



}

package tfar.smeltingitemsenchantsthem;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import tfar.smeltingitemsenchantsthem.crafting.DisabledRecipe;

public class ModRecipeSerializers {
	
    public static void register(RegistryEvent.Register<IRecipeSerializer<?>> e) {
        e.getRegistry().register(DisabledRecipe.DISABLED.setRegistryName(new ResourceLocation(SmeltingItemsEnchantsThem.MODID,"disabled")));
    }
}

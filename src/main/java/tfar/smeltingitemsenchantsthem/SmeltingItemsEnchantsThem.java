package tfar.smeltingitemsenchantsthem;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tfar.smeltingitemsenchantsthem.datagen.ModDatagen;

import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SmeltingItemsEnchantsThem.MODID)
public class SmeltingItemsEnchantsThem
{
    // Directly reference a log4j logger.

    public static final String MODID = "smeltingitemsenchantsthem";

    public SmeltingItemsEnchantsThem() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(ModDatagen::start);
        bus.addGenericListener(IRecipeSerializer.class, ModRecipeSerializers::register);

    }

    private void setup(final FMLCommonSetupEvent event)
    {
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    public static void addEnchants(ItemStack stack) {
        List<EnchantmentData> data = EnchantmentHelper.getEnchantmentDatas(50, stack,true);
        data.forEach(enchantmentData -> stack.addEnchantment(enchantmentData.enchantment,enchantmentData.enchantmentLevel));
    }
}

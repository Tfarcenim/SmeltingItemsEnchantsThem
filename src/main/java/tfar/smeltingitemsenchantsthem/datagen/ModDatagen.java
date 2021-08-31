package tfar.smeltingitemsenchantsthem.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import tfar.smeltingitemsenchantsthem.datagen.data.ModRecipeProvider;

public class ModDatagen {

    public static void start(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        if (e.includeClient()) {
            generator.addProvider(new ModRecipeProvider(generator));
        }
    }
}

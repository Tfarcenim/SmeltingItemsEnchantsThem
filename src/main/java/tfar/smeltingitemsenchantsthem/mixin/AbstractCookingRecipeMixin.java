package tfar.smeltingitemsenchantsthem.mixin;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.smeltingitemsenchantsthem.SmeltingItemsEnchantsThem;

@Mixin(AbstractCookingRecipe.class)
public class AbstractCookingRecipeMixin {


	@Inject(at = @At("RETURN"), method = "getCraftingResult")
	private void init(IInventory inv, CallbackInfoReturnable<ItemStack> cir) {
		//what we're getting here is already a copy
		ItemStack result = cir.getReturnValue();
		SmeltingItemsEnchantsThem.addEnchants(result);
	}
}

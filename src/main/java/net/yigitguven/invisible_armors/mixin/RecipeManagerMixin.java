package net.yigitguven.invisible_armors.mixin;

import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.yigitguven.invisible_armors.recipe.InvisibleArmorRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Inject(
        method = "getRecipeFor(Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/world/item/crafting/RecipeInput;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/crafting/RecipeHolder;)Ljava/util/Optional;",
        at = @At("RETURN"),
        cancellable = true
    )
    private <I extends RecipeInput, T extends Recipe<I>> void invisibleArmors$onGetRecipeFor(
        RecipeType<T> recipeType,
        I input,
        Level level,
        @Nullable RecipeHolder<T> lastRecipe,
        CallbackInfoReturnable<Optional<RecipeHolder<T>>> cir
    ) {
        if (cir.getReturnValue().isEmpty() && recipeType == RecipeType.CRAFTING && input instanceof CraftingInput craftingInput) {
            if (InvisibleArmorRecipe.INSTANCE.matches(craftingInput, level)) {
                cir.setReturnValue((Optional) Optional.of(InvisibleArmorRecipe.HOLDER));
            }
        }
    }
}


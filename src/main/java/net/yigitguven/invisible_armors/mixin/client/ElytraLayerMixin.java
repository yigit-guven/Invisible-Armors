package net.yigitguven.invisible_armors.mixin.client;

import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.yigitguven.invisible_armors.util.InvisibleArmorHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ElytraLayer.class)
public class ElytraLayerMixin<T extends LivingEntity> {
    @Inject(
        method = "shouldRender(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)Z",
        at = @At("HEAD"),
        cancellable = true
    )
    private void invisibleArmors$onShouldRenderElytra(ItemStack stack, T entity, CallbackInfoReturnable<Boolean> cir) {
        if (InvisibleArmorHelper.isInvisible(stack)) {
            cir.setReturnValue(false);
        }
    }
}


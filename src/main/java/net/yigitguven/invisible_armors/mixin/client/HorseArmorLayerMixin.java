package net.yigitguven.invisible_armors.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HorseArmorLayer;
import net.minecraft.world.entity.animal.horse.Horse;
import net.yigitguven.invisible_armors.util.InvisibleArmorHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HorseArmorLayer.class)
public class HorseArmorLayerMixin {
    @Inject(
        method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/animal/horse/Horse;FFFFFF)V",
        at = @At("HEAD"),
        cancellable = true
    )
    private void invisibleArmors$onRenderHorseArmor(
        PoseStack poseStack,
        MultiBufferSource buffer,
        int packedLight,
        Horse livingEntity,
        float limbSwing,
        float limbSwingAmount,
        float partialTicks,
        float ageInTicks,
        float netHeadYaw,
        float headPitch,
        CallbackInfo ci
    ) {
        if (InvisibleArmorHelper.isInvisible(livingEntity.getBodyArmorItem())) {
            ci.cancel();
        }
    }
}


package net.yigitguven.invisible_armors.mixin;

import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.yigitguven.invisible_armors.util.InvisibleArmorHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientboundSetEquipmentPacket.class)
public class ClientboundSetEquipmentPacketMixin {
    @Shadow
    @Final
    @Mutable
    private List<Pair<EquipmentSlot, ItemStack>> slots;

    @Inject(
        method = "<init>(ILjava/util/List;)V",
        at = @At("RETURN")
    )
    private void invisibleArmors$hideInvisibleEquipmentOnPacket(int entityId, List<Pair<EquipmentSlot, ItemStack>> originalSlots, CallbackInfo ci) {
        if (!InvisibleArmorHelper.isServerPacketSpoofingEnabled() || this.slots == null || this.slots.isEmpty()) {
            return;
        }
        boolean hasInvisible = false;
        for (Pair<EquipmentSlot, ItemStack> pair : this.slots) {
            if (pair.getFirst().getType() == EquipmentSlot.Type.HUMANOID_ARMOR && InvisibleArmorHelper.isInvisible(pair.getSecond())) {
                hasInvisible = true;
                break;
            }
        }
        if (hasInvisible) {
            List<Pair<EquipmentSlot, ItemStack>> modified = new ArrayList<>(this.slots.size());
            for (Pair<EquipmentSlot, ItemStack> pair : this.slots) {
                if (pair.getFirst().getType() == EquipmentSlot.Type.HUMANOID_ARMOR && InvisibleArmorHelper.isInvisible(pair.getSecond())) {
                    modified.add(Pair.of(pair.getFirst(), ItemStack.EMPTY));
                } else {
                    modified.add(pair);
                }
            }
            this.slots = modified;
        }
    }
}


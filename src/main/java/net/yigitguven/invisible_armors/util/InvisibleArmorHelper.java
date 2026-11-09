package net.yigitguven.invisible_armors.util;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemLore;
import net.yigitguven.invisible_armors.Config;

public final class InvisibleArmorHelper {
    public static final String INVISIBLE_TAG_KEY = "invisible_armors:invisible";
    public static final String LEGACY_TAG_KEY = "invisible_armor";
    public static final String TOOLTIP_KEY = "tooltip.invisible_armors.invisible";

    private InvisibleArmorHelper() {
    }

    public static boolean isInvisible(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData == null || (!customData.contains(INVISIBLE_TAG_KEY) && !customData.contains(LEGACY_TAG_KEY))) {
            return false;
        }
        CompoundTag tag = customData.copyTag();
        return tag.getBoolean(INVISIBLE_TAG_KEY) || tag.getBoolean(LEGACY_TAG_KEY);
    }

    public static boolean canBecomeInvisible(ItemStack stack) {
        if (stack == null || stack.isEmpty() || stack.getMaxStackSize() != 1) {
            return false;
        }
        Item item = stack.getItem();
        if (item instanceof AnimalArmorItem) {
            return isAnimalArmorAllowed();
        }
        if (item instanceof ArmorItem || stack.is(ItemTags.TRIMMABLE_ARMOR)) {
            return true;
        }
        if (item instanceof ElytraItem) {
            return isElytraAllowed();
        }
        return false;
    }

    private static boolean isElytraAllowed() {
        try {
            return Config.ALLOW_ELYTRA.getAsBoolean();
        } catch (IllegalStateException e) {
            return true;
        }
    }

    private static boolean isAnimalArmorAllowed() {
        try {
            return Config.ALLOW_ANIMAL_ARMOR.getAsBoolean();
        } catch (IllegalStateException e) {
            return true;
        }
    }

    public static boolean isServerPacketSpoofingEnabled() {
        try {
            return Config.ENABLE_SERVER_PACKET_SPOOFING.getAsBoolean();
        } catch (IllegalStateException e) {
            return true;
        }
    }

    public static ItemStack makeInvisible(ItemStack source) {
        ItemStack result = source.copyWithCount(1);
        CustomData.update(DataComponents.CUSTOM_DATA, result, tag -> tag.putBoolean(INVISIBLE_TAG_KEY, true));

        ItemLore currentLore = result.getOrDefault(DataComponents.LORE, ItemLore.EMPTY);
        boolean hasLore = currentLore.lines().stream().anyMatch(InvisibleArmorHelper::isInvisibleLoreLine);
        if (!hasLore) {
            List<Component> newLines = new ArrayList<>(currentLore.lines());
            newLines.add(createInvisibleLoreLine());
            result.set(DataComponents.LORE, new ItemLore(newLines));
        }
        return result;
    }

    public static ItemStack removeInvisible(ItemStack source) {
        ItemStack result = source.copyWithCount(1);
        CustomData customData = result.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            tag.remove(INVISIBLE_TAG_KEY);
            tag.remove(LEGACY_TAG_KEY);
            if (tag.isEmpty()) {
                result.remove(DataComponents.CUSTOM_DATA);
            } else {
                result.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
            }
        }

        ItemLore currentLore = result.get(DataComponents.LORE);
        if (currentLore != null) {
            List<Component> remaining = currentLore.lines().stream()
                    .filter(line -> !isInvisibleLoreLine(line))
                    .toList();
            if (remaining.isEmpty()) {
                result.remove(DataComponents.LORE);
            } else {
                result.set(DataComponents.LORE, new ItemLore(remaining));
            }
        }
        return result;
    }

    public static Component createInvisibleLoreLine() {
        return Component.translatableWithFallback(TOOLTIP_KEY, "Invisible")
                .withStyle(style -> style.withColor(ChatFormatting.LIGHT_PURPLE).withItalic(false));
    }

    public static boolean isInvisibleLoreLine(Component line) {
        if (line.getContents() instanceof TranslatableContents translatable) {
            if (TOOLTIP_KEY.equals(translatable.getKey())) {
                return true;
            }
        }
        return "Invisible".equals(line.getString());
    }
}


package net.yigitguven.invisible_armors.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.yigitguven.invisible_armors.InvisibleArmors;
import net.yigitguven.invisible_armors.util.InvisibleArmorHelper;

public class InvisibleArmorRecipe extends CustomRecipe {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(InvisibleArmors.MODID, "invisible_armor");
    public static final InvisibleArmorRecipe INSTANCE = new InvisibleArmorRecipe(CraftingBookCategory.EQUIPMENT);
    public static final RecipeHolder<CraftingRecipe> HOLDER = new RecipeHolder<>(ID, INSTANCE);

    public InvisibleArmorRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        // Case 1: 3x3 grid with armor in center (1, 1) and 8 Amethyst Shards surrounding it -> Make Invisible
        if (input.width() == 3 && input.height() == 3 && input.ingredientCount() == 9) {
            ItemStack center = input.getItem(1, 1);
            if (!InvisibleArmorHelper.canBecomeInvisible(center) || InvisibleArmorHelper.isInvisible(center)) {
                return false;
            }
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (row == 1 && col == 1) {
                        continue;
                    }
                    ItemStack surrounding = input.getItem(col, row);
                    if (!surrounding.is(Items.AMETHYST_SHARD)) {
                        return false;
                    }
                }
            }
            return true;
        }

        // Case 2: 1x1 grid with a single invisible armor piece -> Remove Invisibility
        if (input.width() == 1 && input.height() == 1 && input.ingredientCount() == 1) {
            ItemStack single = input.getItem(0, 0);
            return InvisibleArmorHelper.canBecomeInvisible(single) && InvisibleArmorHelper.isInvisible(single);
        }

        return false;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        if (input.width() == 3 && input.height() == 3 && input.ingredientCount() == 9) {
            ItemStack center = input.getItem(1, 1);
            if (InvisibleArmorHelper.canBecomeInvisible(center) && !InvisibleArmorHelper.isInvisible(center)) {
                return InvisibleArmorHelper.makeInvisible(center);
            }
        } else if (input.width() == 1 && input.height() == 1 && input.ingredientCount() == 1) {
            ItemStack single = input.getItem(0, 0);
            if (InvisibleArmorHelper.canBecomeInvisible(single) && InvisibleArmorHelper.isInvisible(single)) {
                return InvisibleArmorHelper.removeInvisible(single);
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 1 && height >= 1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializer.ARMOR_DYE;
    }
}


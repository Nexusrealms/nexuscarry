package de.nexusrealms.carry.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.nexusrealms.carry.item.BagItem;
import de.nexusrealms.carry.item.CarryItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.RawShapedRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

import java.util.function.Function;

public class CraftBagRecipe extends ShapedRecipe {

    public ItemStack craft(CraftingRecipeInput craftingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup) {
        ItemStack result = this.result.copy();
        craftingRecipeInput.getStacks().stream().filter(stack -> stack.isIn(CarryItems.Tags.TRANSFERS_PRIMARY_COLOR_AS_SECONDARY) && stack.contains(DataComponentTypes.DYED_COLOR)).findFirst().ifPresent(stack -> {
            BagItem.setColor(result, CarryItems.Components.BAG_STRAP_COLOR, BagItem.getColor(stack, DataComponentTypes.DYED_COLOR, 0xffffffff));
        });
        return result;
    }
    public CraftBagRecipe(String group, CraftingRecipeCategory category, RawShapedRecipe raw, ItemStack result, boolean showNotification) {
        super(group, category, raw, result, showNotification);
    }
    public static CraftBagRecipe fromShapedRecipe(ShapedRecipe shapedRecipe){
        return new CraftBagRecipe(shapedRecipe.getGroup(), shapedRecipe.getCategory(), shapedRecipe.raw, shapedRecipe.result, shapedRecipe.showNotification());
    }

    @Override
    public RecipeSerializer<? extends ShapedRecipe> getSerializer() {
        return CarryRecipes.CRAFT_BAG;
    }

    public static class Serializer implements RecipeSerializer<CraftBagRecipe> {
        public static final MapCodec<CraftBagRecipe> CODEC = ShapedRecipe.Serializer.CODEC.xmap(CraftBagRecipe::fromShapedRecipe, Function.identity());
        public static final PacketCodec<RegistryByteBuf, CraftBagRecipe> PACKET_CODEC = ShapedRecipe.Serializer.PACKET_CODEC.xmap(CraftBagRecipe::fromShapedRecipe, Function.identity());

        public Serializer() {
        }

        public MapCodec<CraftBagRecipe> codec() {
            return CODEC;
        }

        public PacketCodec<RegistryByteBuf, CraftBagRecipe> packetCodec() {
            return PACKET_CODEC;
        }

    }
}

package de.nexusrealms.carry.client;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.nexusrealms.carry.item.BagItem;
import de.nexusrealms.carry.item.CarryItems;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.Nullable;

public record StrapDyeTintSource(int defaultColor) implements TintSource {
    public static final MapCodec<StrapDyeTintSource> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Codecs.RGB.fieldOf("default").forGetter(StrapDyeTintSource::defaultColor)).apply(instance, StrapDyeTintSource::new));

    @Override
    public int getTint(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user) {
        return BagItem.getColor(stack, CarryItems.Components.BAG_STRAP_COLOR, defaultColor);
    }

    @Override
    public MapCodec<? extends TintSource> getCodec() {
        return CODEC;
    }
}

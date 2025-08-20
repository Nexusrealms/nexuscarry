package de.nexusrealms.carry.mixin.client;

import com.mojang.serialization.MapCodec;
import de.nexusrealms.carry.NexusCarry;
import de.nexusrealms.carry.client.StrapDyeTintSource;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.render.item.tint.TintSourceTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TintSourceTypes.class)
public abstract class TintSourceTypesMixin {
    @Shadow @Final public static Codecs.IdMapper<Identifier, MapCodec<? extends TintSource>> ID_MAPPER;

    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void addCustomTintSourceTypes(CallbackInfo ci){
        ID_MAPPER.put(NexusCarry.id("strap_dye"), StrapDyeTintSource.CODEC);
    }
}

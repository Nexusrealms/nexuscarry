package de.nexusrealms.carry.datagen;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import de.nexusrealms.carry.item.CarryItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

import static net.minecraft.client.data.BlockStateModelGenerator.*;

public class ModelGen extends FabricModelProvider {
    public ModelGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.registerDyeable(CarryItems.LEATHER_STRAPS, 0xffFFA8A8);
    }
    private void registerSpawnEgg(ItemModelGenerator generator, EntityType<?> entityType){
        if(SpawnEggItem.forEntity(entityType) instanceof SpawnEggItem item){
            generator.register(item, CustomModels.SPAWN_EGG);
        }
    }

    private static class CustomModels {
        protected static final Model BUILTIN_SLASH_ENTITY = new GuiLightFrontModel(Optional.of(Identifier.of("builtin/entity")), Optional.empty());
        protected static final Model SPAWN_EGG = new Model(Optional.of(Identifier.ofVanilla("item/template_spawn_egg")), Optional.empty());
        protected static class GuiLightFrontModel extends Model{


            public GuiLightFrontModel(Optional<Identifier> parent, Optional<String> variant, TextureKey... requiredTextureKeys) {
                super(parent, variant, requiredTextureKeys);
            }

            @Override
            public Identifier upload(Item item, TextureMap textures, BiConsumer<Identifier, ModelSupplier> modelCollector) {
                return super.upload(item, textures, modelCollector);
            }

            public Identifier upload(Identifier id, TextureMap textures, BiConsumer<Identifier, ModelSupplier> modelCollector) {
                Map<TextureKey, Identifier> map = this.createTextureMap(textures);
                modelCollector.accept(id, () -> {
                    JsonObject jsonObject = new JsonObject();
                    this.parent.ifPresent((identifier) -> jsonObject.addProperty("parent", identifier.toString()));
                    if (!map.isEmpty()) {
                        JsonObject textureObject = new JsonObject();
                        map.forEach((textureKey, identifier) -> textureObject.addProperty(textureKey.getName(), identifier.toString()));
                        jsonObject.add("textures", textureObject);
                        jsonObject.add("gui-light", new JsonPrimitive("front"));
                    }

                    return jsonObject;
                });
                return id;
            }
        }
    }
}

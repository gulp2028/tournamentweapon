package gulp.tournamentweapon.datagen;

import gulp.tournamentweapon.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output){
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.ICIUM, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SHORTIUM, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.UPSIDEDOWNIUM, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.WINGED_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.FLARE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.FLARE_GUN, ModelTemplates.CROSSBOW);
        itemModelGenerators.generateFlatItem(ModItems.ICE_AXE, ModelTemplates.SPEAR_IN_HAND);
    }
}

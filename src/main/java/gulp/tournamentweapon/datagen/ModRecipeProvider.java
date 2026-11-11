package gulp.tournamentweapon.datagen;

import gulp.tournamentweapon.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, ModItems.ICIUM)
                        .pattern("BB")
                        .pattern("II")
                        .define('B', Items.BLUE_ICE)
                        .define('I', Items.IRON_INGOT)
                        .unlockedBy(getHasName(ModItems.ICIUM), has(ModItems.ICIUM))
                        .group("Mod ingots")
                        .save(output);
                shaped(RecipeCategory.MISC, ModItems.SHORTIUM)
                        .pattern("AA")
                        .pattern("II")
                        .define('A', Items.ANVIL)
                        .define('I', Items.IRON_INGOT)
                        .unlockedBy(getHasName(ModItems.SHORTIUM), has(ModItems.SHORTIUM))
                        .group("Mod ingots")
                        .save(output);
                shaped(RecipeCategory.MISC, ModItems.UPSIDEDOWNIUM)
                        .pattern("FF")
                        .pattern("II")
                        .define('F', Items.FEATHER)
                        .define('I', Items.IRON_INGOT)
                        .unlockedBy(getHasName(ModItems.UPSIDEDOWNIUM), has(ModItems.UPSIDEDOWNIUM))
                        .group("Mod ingots")
                        .save(output);
                shaped(RecipeCategory.MISC, ModItems.STIRLITE)
                        .pattern("AAA")
                        .pattern("ANA")
                        .pattern("AAA")
                        .define('A', Items.RESPAWN_ANCHOR)
                        .define('N', Items.NETHERITE_SWORD)
                        .unlockedBy(getHasName(Items.RESPAWN_ANCHOR), has(Items.NETHERITE_SWORD))
                        .group("Mod ingots")
                        .save(output);
                shaped(RecipeCategory.MISC, ModItems.ZEPHYR)
                        .pattern(" S ")
                        .pattern(" S ")
                        .pattern(" T ")
                        .define('S', ModItems.STIRLITE)
                        .define('T', Items.STICK)
                        .unlockedBy(getHasName(ModItems.STIRLITE), has(ModItems.STIRLITE))
                        .group("Mod weapons")
                        .save(output);
                shaped(RecipeCategory.MISC, ModItems.ICE_AXE)
                        .pattern("II ")
                        .pattern("IT ")
                        .pattern(" T ")
                        .define('I', ModItems.ICIUM)
                        .define('T', Items.STICK)
                        .unlockedBy(getHasName(ModItems.ICIUM), has(ModItems.ICIUM))
                        .group("Mod weapons")
                        .save(output);
                shaped(RecipeCategory.MISC, ModItems.WINGED_SWORD)
                        .pattern(" U ")
                        .pattern(" U ")
                        .pattern(" T ")
                        .define('U', ModItems.UPSIDEDOWNIUM)
                        .define('T', Items.STICK)
                        .unlockedBy(getHasName(ModItems.UPSIDEDOWNIUM), has(ModItems.UPSIDEDOWNIUM))
                        .group("Mod weapons")
                        .save(output);
                shaped(RecipeCategory.MISC, ModItems.HAMMER)
                        .pattern("SSS")
                        .pattern("SSS")
                        .pattern(" T ")
                        .define('S', ModItems.SHORTIUM)
                        .define('T', Items.STICK)
                        .unlockedBy(getHasName(ModItems.SHORTIUM), has(ModItems.SHORTIUM))
                        .group("Mod weapons")
                        .save(output);



            }
        };
    }
    @Override
    public String getName(){
        return "Tournament Weapon Recipes";
    }
}

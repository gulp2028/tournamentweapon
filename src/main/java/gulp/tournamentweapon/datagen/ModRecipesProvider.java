package gulp.tournamentweapon.datagen;

import gulp.tournamentweapon.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends FabricRecipeProvider {

    public ModRecipesProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, ModItems.ICIUM, 1)
                        .pattern("BB")
                        .pattern("II")
                        .define('B', Items.BLUE_ICE)
                        .define('I', Items.IRON_INGOT)
                        .unlockedBy(getHasName(Items.BLUE_ICE), has(Items.BLUE_EGG))
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .group("icium")
                        .save(output);
                shaped(RecipeCategory.MISC, ModItems.SHORTIUM, 1)
                        .pattern("SS")
                        .pattern("II")
                        .define('S', Items.STONE_BRICKS)
                        .define('I', Items.IRON_INGOT)
                        .unlockedBy(getHasName(Items.STONE_BRICKS), has(Items.STONE_BRICKS))
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .group("shortium")
                        .save(output);
                shaped(RecipeCategory.MISC, ModItems.UPSIDEDOWNIUM, 1)
                        .pattern("FF")
                        .pattern("II")
                        .define('F', Items.FEATHER)
                        .define('I', Items.IRON_INGOT)
                        .unlockedBy(getHasName(Items.FEATHER), has(Items.FEATHER))
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .group("upsidedownium")
                        .save(output);

            }
        };
    }


    }
    @Override
    public String getName(){
        return "Tournamentmod Recipes";
    }

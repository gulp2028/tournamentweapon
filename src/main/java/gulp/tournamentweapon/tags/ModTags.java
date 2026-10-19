package gulp.tournamentweapon.tags;

import gulp.tournamentweapon.Tournamentweapon;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_UPSIDEDOWNIUM_TOOL =
                createTag("needs_upsidedownium_tool");
        public static final TagKey<Block> INCORRECT_FOR_UPSIDEDOWNIUM_TOOL =
                createTag("incorrect_for_upsidedownium_tool");
        public static final TagKey<Block> SHORTIUM_TOOL =
                createTag("needs_shortium_tool");
        public static final TagKey<Block> INCORRECT_FOR_SHORTIUM_TOOL =
                createTag("incorrect_for_shortium_tool");
        public static final TagKey<Block> ICIUM_TOOL =
                createTag("needs_icium_tool");
        public static final TagKey<Block> INCORRECT_FOR_ICIUM_TOOL =
                createTag("incorrect_for_icium_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Tournamentweapon.MOD_ID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> UPSIDEDOWNIUM_REPAIR =
                createTag("upsidedownium_repair");
        public static final TagKey<Item> SHORTIUM_REPAIR =
                createTag("shortium_repair");
        public static final TagKey<Item> ICIUM_REPAIR =
                createTag("icium_repair");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Tournamentweapon.MOD_ID, name));
        }
    }
}

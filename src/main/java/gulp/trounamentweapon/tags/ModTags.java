package gulp.trounamentweapon.tags;

import gulp.trounamentweapon.Tournamentweapon;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    private static TagKey<Block> createTag(String name) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Tournamentweapon.MOD_ID, name));
    }
}

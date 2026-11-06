package gulp.tournamentweapon.item;

import gulp.tournamentweapon.Tournamentweapon;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> FLARE_ONLY =
            TagKey.create(Registries.ITEM,
                    Identifier.fromNamespaceAndPath(Tournamentweapon.MOD_ID, "flare_only"));
}

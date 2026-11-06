package gulp.tournamentweapon.item.custom;
import gulp.tournamentweapon.item.ModItemTags;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class Flare_GunItem extends CrossbowItem {
    public Flare_GunItem(Properties properties) {
        super(properties);

    }
    @Override
    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return public static final Predicate<ItemStack> FLARE_ONLY = (itemStack ->
                itemStack.is(ModItemTags.FLARE_ONLY));
    }








}

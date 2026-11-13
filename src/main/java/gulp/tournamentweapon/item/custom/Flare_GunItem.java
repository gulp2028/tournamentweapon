package gulp.tournamentweapon.item.custom;
import gulp.tournamentweapon.entity.projectile.AbstractFlare;
import gulp.tournamentweapon.entity.projectile.FlareEntity;
import gulp.tournamentweapon.item.ModItemTags;
import gulp.tournamentweapon.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.function.Predicate;

public class Flare_GunItem extends CrossbowItem {
    public Flare_GunItem(Properties properties) {
        super(properties);

    }



    @Override
    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return itemStack -> itemStack.is(ModItemTags.FLARE_ONLY);
    }
    @Override
    protected Projectile createProjectile(final Level level, final LivingEntity shooter, final ItemStack heldItem, final ItemStack projectile, final boolean isCrit) {
        return new FlareEntity(level, projectile, shooter, shooter.getX(), shooter.getEyeY() - (double)0.15F, shooter.getZ(), true);


        }
    }













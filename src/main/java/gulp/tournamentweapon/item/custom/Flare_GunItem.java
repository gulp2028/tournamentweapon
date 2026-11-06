package gulp.tournamentweapon.item.custom;
import gulp.tournamentweapon.item.ModItemTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
        if (projectile.is(Items.FIREWORK_ROCKET)) {
            return new FireworkRocketEntity(level, projectile, shooter, shooter.getX(), shooter.getEyeY() - (double)0.15F, shooter.getZ(), true);
        } else {
            Projectile projectileEntity = super.createProjectile(level, shooter, heldItem, projectile, isCrit);
            if (projectileEntity instanceof AbstractArrow) {
                AbstractArrow arrow = (AbstractArrow)projectileEntity;
                arrow.setSoundEvent(SoundEvents.CROSSBOW_HIT);
            }

            return projectileEntity;
        }
    }





}

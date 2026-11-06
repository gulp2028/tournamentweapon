package gulp.tournamentweapon.item.custom;
import gulp.tournamentweapon.entity.projectile.FlareEntity;
import gulp.tournamentweapon.item.ModItemTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.function.Predicate;

public class Flare_GunItem extends CrossbowItem {
    public Flare_GunItem(Properties properties) {
        super(properties);

    }

    public static int getChargeDuration(final ItemStack crossbow, final LivingEntity user) {
        float duration = EnchantmentHelper.modifyCrossbowChargingTime(crossbow, user, 1.0F);
        return Mth.floor(duration * 20.0F);
    }
    @Override
    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return itemStack -> itemStack.is(ModItemTags.FLARE_ONLY);
    }

    @Override
    protected Projectile createProjectile(final Level level, final LivingEntity shooter, final ItemStack heldItem, final ItemStack projectile, final boolean isCrit) {
            Projectile projectileEntity = super.createProjectile(level, shooter, heldItem, projectile, isCrit);
            if (projectileEntity instanceof FlareEntity) {
                FlareEntity flare = (FlareEntity) projectileEntity;
                flare.setSoundEvent(SoundEvents.CROSSBOW_HIT);
            }

            return projectileEntity;
        }
    }






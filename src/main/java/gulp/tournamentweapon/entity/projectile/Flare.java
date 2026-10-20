package gulp.tournamentweapon.entity.projectile;


import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class Flare extends AbstractArrow {
    private static final double FLARE_DAMAGE = (double) 3.0f;
    private double flaredamage;
    protected Flare(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
        this.double basedamage = 2.0f;
    }


    @Override
    protected ItemStack getDefaultPickupItem() {
        return null;
    }

    @Override
    public void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity entity = hitResult.getEntity();
        float pow = (float)this.getDeltaMovement().length();
        double arrowDamage = this.flaredamage;
        Entity currentOwner = this.getOwner();
        DamageSource damageSource = this.damageSources().arrow(this, (Entity)(currentOwner != null ? currentOwner : this));
        if (this.getWeaponItem() != null) {
            Level var9 = this.level();
            if (var9 instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel)var9;
                arrowDamage = (double) EnchantmentHelper.modifyDamage(serverLevel, this.getWeaponItem(), entity, damageSource, (float)arrowDamage);
            }
        }

    }
}




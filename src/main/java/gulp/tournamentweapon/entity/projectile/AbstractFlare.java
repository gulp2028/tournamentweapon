package gulp.tournamentweapon.entity.projectile;

import gulp.tournamentweapon.item.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jspecify.annotations.Nullable;

public class AbstractFlare extends AbstractArrow {
    private int life;

    protected AbstractFlare(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
    }

    protected AbstractFlare(EntityType<? extends AbstractArrow> type, double x, double y, double z, Level level, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(type, x, y, z, level, pickupItemStack, firedFromWeapon);
    }

    protected AbstractFlare(EntityType<? extends AbstractArrow> type, LivingEntity mob, Level level, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(type, mob, level, pickupItemStack, firedFromWeapon);
    }



    @Override
    public void tickDespawn(){
        ++this.life;
        if(this.life >= 200){
            this.discard();
        }
    }
    @Override
    protected void onHitEntity(EntityHitResult hitResult){
        Entity target = hitResult.getEntity();
        if(!target.isOnFire()){
            target.igniteForSeconds(10);
        }
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return ModItems.FLARE.getDefaultInstance();
    }
    @Override
    protected double getDefaultGravity(){
        return 0.1f;
    }
}






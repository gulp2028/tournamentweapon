package gulp.tournamentweapon.entity.projectile;


import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class FlareEntity extends AbstractArrow {
    private static final double FLARE_DAMAGE = (double) 3.0f;
    private double basedamage;

    protected FlareEntity(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);

    }
    @Override
    protected Item getDefualtItem() {
        return ModItems.
    }


    @Override
    protected ItemStack getDefaultPickupItem() {
        return null;
    }
    @Override
    protected float getAirDrag() {
        return 0.999F;
    }
    @Override
    protected double getDefaultGravity(){
        return 0.08;
    }

    @Override
    public void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity entity = hitResult.getEntity();
        entity.igniteForSeconds(15);

    }
}



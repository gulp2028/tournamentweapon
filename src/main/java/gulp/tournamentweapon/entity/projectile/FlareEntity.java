package gulp.tournamentweapon.entity.projectile;


import gulp.tournamentweapon.entity.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jspecify.annotations.Nullable;

public class FlareEntity extends AbstractFlare {

    protected FlareEntity(EntityType<? extends AbstractArrow> type, double x, double y, double z, Level level, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(type, x, y, z, level, pickupItemStack, firedFromWeapon);
    }

    protected FlareEntity(EntityType<? extends AbstractArrow> type, LivingEntity mob, Level level, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(type, mob, level, pickupItemStack, firedFromWeapon);
    }


    public FlareEntity(EntityType<FlareEntity> flareEntityEntityType, Level level) {
        super(flareEntityEntityType, level);
    }
}









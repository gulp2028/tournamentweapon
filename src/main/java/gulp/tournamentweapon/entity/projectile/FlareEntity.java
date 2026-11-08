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


    protected FlareEntity(EntityType<? extends AbstractFlare> type, Level level, ItemStack pickupItemStack) {
        super(type, level, pickupItemStack);
    }

    protected FlareEntity(EntityType<? extends AbstractFlare> type, double x, double y, double z, Level level, ItemStack pickupItemStack, ItemStack pickupItemStack1, @Nullable ItemStack firedFromWeapon) {
        super(type, x, y, z, level, pickupItemStack, pickupItemStack1, firedFromWeapon);
    }

    protected FlareEntity(EntityType<? extends AbstractFlare> type, LivingEntity mob, Level level, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon, ItemStack pickupItemStack1) {
        super(type, mob, level, pickupItemStack, firedFromWeapon, pickupItemStack1);
    }

    public <T extends Entity> FlareEntity(EntityType<T> tEntityType, Level level) {
        super(tEntityType, level);
}

    public FlareEntity(Level level, LivingEntity owner, ItemStack itemStack, @Nullable ItemStack firedfromweapon) {
        super(level, owner, itemStack, firedfromweapon);
    }
}






package gulp.tournamentweapon.entity.projectile;


import gulp.tournamentweapon.entity.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jspecify.annotations.Nullable;

public class FlareEntity extends AbstractHurtingProjectile {

    public FlareEntity(Level level, LivingEntity owner, ItemStack itemStack) {
        super(ModEntityTypes.FLARE, level);
    }

    public FlareEntity(Level level, LivingEntity owner, ItemStack itemStack, @Nullable ItemStack firedfromweapon) {
        super(ModEntityTypes.FLARE, level);
    }
    public FlareEntity(Level level, double x, double y, double z, ItemStack itemStack) {
        super(ModEntityTypes.FLARE, level);
    }



    public FlareEntity(EntityType<FlareEntity> flareEntityEntityType, Level level) {
        super(EntityType)
    }






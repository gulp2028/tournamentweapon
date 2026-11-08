package gulp.tournamentweapon.entity.projectile;

import gulp.tournamentweapon.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jspecify.annotations.Nullable;

public class AbstractFlare extends Projectile {
    private static double BASE_FLARE_DAMAGE = (double) 2.5F;


    protected AbstractFlare(final EntityType<? extends AbstractFlare> type, final Level level) {
        super(type, level);
        this.baseDamage = (double)2.0F;
        this.soundEvent = this.getDefaultHitGroundSoundEvent();
        this.firedFromWeapon = null;
    }


}



package gulp.tournamentweapon.entity.projectile;

import gulp.tournamentweapon.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jspecify.annotations.Nullable;

public class AbstractFlare extends AbstractArrow {
    private ItemStack pickupItemStack;

    protected AbstractFlare(final EntityType<? extends AbstractFlare> type, final Level level, ItemStack pickupItemStack) {
        super(type, level);
        this.pickupItemStack = pickupItemStack;
    }

    protected AbstractFlare(final EntityType<? extends AbstractFlare> type, final double x, final double y, final double z, final Level level, final ItemStack pickupItemStack, ItemStack pickupItemStack1, final @Nullable ItemStack firedFromWeapon) {
        this(type, level, pickupItemStack1);
        this.pickupItemStack = pickupItemStack.copy();
        this.applyComponentsFromItemStack(pickupItemStack);
        this.setPos(x, y, z);
        if (firedFromWeapon != null && level instanceof ServerLevel serverLevel) {
            if (firedFromWeapon.isEmpty()) {
                throw new IllegalArgumentException("Invalid weapon firing an arrow");
            }
        }

    }

    protected AbstractFlare(final EntityType<? extends AbstractFlare> type, final LivingEntity mob, final Level level, final ItemStack pickupItemStack, final @Nullable ItemStack firedFromWeapon, ItemStack pickupItemStack1) {
        this(type, mob.getX(), mob.getEyeY() - (double)0.1F, mob.getZ(), level, pickupItemStack, pickupItemStack1, firedFromWeapon);
        this.setOwner(mob);
    }



    @Override
    protected void onHitBlock(BlockHitResult hitResult){
        super.onHitBlock(hitResult);
        Level level = level();
        if(!level.isClientSide()) {
            BlockPos pos =  hitResult.getBlockPos().relative(hitResult.getDirection());
            if (level.isEmptyBlock(pos)) {
                level.setBlockAndUpdate(pos, BaseFireBlock.getState(level,pos));
            }
        }
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.FLARE);
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


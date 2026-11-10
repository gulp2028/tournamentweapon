package gulp.tournamentweapon.entity.projectile;

import gulp.tournamentweapon.item.ModItems;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class AbstractFlare extends Projectile {
    private static double BASE_FLARE_DAMAGE = (double) 2.5F;
    private static final int SHAKE_TIME = 7;
    private static final float WATER_INERTIA = 0.6F;
    private static final float INERTIA = 0.99F;
    private static final short DEFAULT_LIFE = 0;
    private static final byte DEFAULT_SHAKE = 0;
    private static final boolean DEFAULT_IN_GROUND = false;
    private static final boolean DEFAULT_CRIT = false;
    private static final byte DEFAULT_PIERCE_LEVEL = 0;
    private static final int FLAG_CRIT = 1;
    private static final int FLAG_NOPHYSICS = 2;
    private @Nullable BlockState lastState;
    protected int inGroundTime;
    public AbstractArrow.Pickup pickup;
    public int shakeTime;
    private int life;
    private double baseDamage;
    private SoundEvent soundEvent;
    private @Nullable IntOpenHashSet piercingIgnoreEntityIds;
    private @Nullable List<Entity> piercedAndKilledEntities;
    private ItemStack pickupItemStack;
    private @Nullable ItemStack firedFromWeapon;



    protected AbstractFlare(final EntityType<? extends AbstractFlare> type, final Level level) {
        super(type, level);
        this.pickup = AbstractArrow.Pickup.DISALLOWED;
        this.shakeTime = 0;
        this.life = 0;
        this.baseDamage = (double)2.0F;
        this.soundEvent = this.getDefaultHitGroundSoundEvent();
        this.pickupItemStack = this.getDefaultPickupItem();
        this.firedFromWeapon = null;

    }
    protected AbstractFlare(final EntityType<? extends AbstractFlare> type, final double x, final double y, final double z, final Level level, final ItemStack pickupItemStack, final @Nullable ItemStack firedFromWeapon) {
        this(type, level);

        this.setPos(x, y, z);
        if (firedFromWeapon != null && level instanceof ServerLevel serverLevel) {
            if (firedFromWeapon.isEmpty()) {
                throw new IllegalArgumentException("Invalid weapon firing a flare");}}

    }

    public void shoot(final double xd, final double yd, final double zd, final float pow, final float uncertainty) {
        super.shoot(xd, yd, zd, pow, uncertainty);
        this.life = 0;
    }
    public void lerpMotion(final Vec3 movement) {
        super.lerpMotion(movement);
        this.life = 0;

    }

    public void onSyncedDataUpdated(final EntityDataAccessor<?> accessor) {
        super.onSyncedDataUpdated(accessor);

    }

    public void tick() {
        boolean physicsEnabled = !this.isNoPhysics();
        Vec3 movement = this.getDeltaMovement();
        BlockPos blockPos = this.blockPosition();
        BlockState blockState = this.level().getBlockState(blockPos);
        if (!blockState.isAir() && physicsEnabled) {
            VoxelShape shape = blockState.getCollisionShape(this.level(), blockPos);
            if (!shape.isEmpty()) {
                Vec3 position = this.position();

                for(AABB aabb : shape.toAabbs()) {
                    if (aabb.move(blockPos).contains(position)) {
                        this.setDeltaMovement(Vec3.ZERO);
                        this.setInGround(true);
                        break;
                    }
                }
            }
        }

        if (this.shakeTime > 0) {
            --this.shakeTime;
        }

        if (this.isInWaterOrRain()) {
            this.clearFire();
        }

        if (this.isInGround() && physicsEnabled) {
            if (!this.level().isClientSide()) {
                if (this.lastState != blockState && this.shouldFall()) {
                    this.startFalling();
                } else {
                    this.tickDespawn();
                }
            }

            ++this.inGroundTime;
            if (this.isAlive()) {
                this.applyEffectsFromBlocks();
            }

            if (!this.level().isClientSide()) {
                this.setSharedFlagOnFire(this.getRemainingFireTicks() > 0);
            }

        } else {
            this.inGroundTime = 0;
            Vec3 originalPosition = this.position();
            if (this.isInWater()) {
                this.applyInertia(this.getWaterInertia());
                this.addBubbleParticles(originalPosition);
            }

            if (this.isCritArrow()) {
                for(int i = 0; i < 4; ++i) {
                    this.level().addParticle(ParticleTypes.CRIT, originalPosition.x + movement.x * (double)i / (double)4.0F, originalPosition.y + movement.y * (double)i / (double)4.0F, originalPosition.z + movement.z * (double)i / (double)4.0F, -movement.x, -movement.y + 0.2, -movement.z);
                }
            }

            float yRot;
            if (!physicsEnabled) {
                yRot = (float)(Mth.atan2(-movement.x, -movement.z) * (double)(180F / (float)Math.PI));
            } else {
                yRot = (float)(Mth.atan2(movement.x, movement.z) * (double)(180F / (float)Math.PI));
            }

            float xRot = (float)(Mth.atan2(movement.y, movement.horizontalDistance()) * (double)(180F / (float)Math.PI));
            this.setXRot(lerpRotation(this.getXRot(), xRot));
            this.setYRot(lerpRotation(this.getYRot(), yRot));
            this.checkLeftOwner();
            if (physicsEnabled) {
                BlockHitResult blockHitResult = this.level().clipIncludingBorder(new ClipContext(originalPosition, originalPosition.add(movement), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
                this.stepMoveAndHit(blockHitResult);
            } else {
                this.setPos(originalPosition.add(movement));
                this.applyEffectsFromBlocks();
            }

            if (!this.isInWater()) {
                this.applyInertia(this.getAirDrag());
            }

            if (physicsEnabled && !this.isInGround()) {
                this.applyGravity();
            }

            super.tick();
        }
    }



}



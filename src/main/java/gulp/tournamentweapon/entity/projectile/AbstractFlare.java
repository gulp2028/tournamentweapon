package gulp.tournamentweapon.entity.projectile;

import com.google.common.collect.Lists;
import gulp.tournamentweapon.item.ModItems;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

import java.util.*;

public class AbstractFlare extends Projectile {
    private static final double FLARE_BASE_DAMAGE = (double) 2.0F;
    private static final int SHAKE_TIME = 7;
    private static final float WATER_INERTIA = 0.6F;
    private static final float INERTIA = 0.99F;
    private static final short DEFAULT_LIFE = 0;
    private static final byte DEFAULT_SHAKE = 0;
    private static final boolean DEFAULT_IN_GROUND = false;
    private static final boolean DEFAULT_CRIT = false;
    private static final byte DEFAULT_PIERCE_LEVEL = 0;
    private static final EntityDataAccessor<Byte> ID_FLAGS;
    private static final EntityDataAccessor<Byte> PIERCE_LEVEL;
    private static final EntityDataAccessor<Boolean> IN_GROUND;
    private static final int FLAG_CRIT = 1;
    private static final int FLAG_NOPHYSICS = 2;
    private @Nullable BlockState lastState;
    protected int inGroundTime;
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
        this.shakeTime = 0;
        this.life = 0;
        this.baseDamage = (double) 2.0F;
        this.soundEvent = this.getDefaultHitGroundSoundEvent();
        this.firedFromWeapon = null;
    }

    protected AbstractFlare(final EntityType<? extends AbstractFlare> type, final LivingEntity mob, final Level level, final ItemStack pickupItemStack, final @Nullable ItemStack firedFromWeapon) {
        this(type, mob.getX(), mob.getEyeY() - (double) 0.1F, mob.getZ(), level, pickupItemStack, firedFromWeapon);
        this.setOwner(mob);
    }

    protected AbstractFlare(final EntityType<? extends AbstractFlare> type, final double x, final double y, final double z, final Level level, final ItemStack pickupItemStack, final @Nullable ItemStack firedFromWeapon) {
        this(type, level);
        this.applyComponentsFromItemStack(pickupItemStack);


        this.setPos(x, y, z);
        if (firedFromWeapon != null && level instanceof ServerLevel serverLevel) {
            if (firedFromWeapon.isEmpty()) {
                throw new IllegalArgumentException("Invalid weapon firing a flare");
            }

        }
    }

    public void setSoundEvent(final SoundEvent soundEvent) {
        this.soundEvent = soundEvent;
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
        if (this.shakeTime > 0) {
            --this.shakeTime;
        }
        else {
            this.inGroundTime = 0;
            Vec3 originalPosition = this.position();
            if (this.isInWater()) {
                this.applyInertia(this.getWaterInertia());
                this.addBubbleParticles(originalPosition);
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
    protected float getAirDrag() {
        return 0.99F;
    }
    private void stepMoveAndHit(final BlockHitResult blockHitResult) {
        while(true) {
            if (this.isAlive()) {
                Vec3 initialPosition = this.position();
                ArrayList<EntityHitResult> entitiesHit = new ArrayList(this.findHitEntities(initialPosition, blockHitResult.getLocation()));
                entitiesHit.sort(Comparator.comparingDouble((c) -> initialPosition.distanceToSqr(c.getEntity().position())));
                EntityHitResult firstEntityHit = entitiesHit.isEmpty() ? null : (EntityHitResult)entitiesHit.getFirst();
                Vec3 nextLocation = ((HitResult) Objects.requireNonNullElse(firstEntityHit, blockHitResult)).getLocation();
                this.setPos(nextLocation);
                this.applyEffectsFromBlocks(initialPosition, nextLocation);
                if (this.portalProcess != null && this.portalProcess.isInsidePortalThisTick()) {
                    this.handlePortal();
                }
                if (entitiesHit.isEmpty()) {
                    if (this.isAlive() && blockHitResult.getType() != HitResult.Type.MISS) {
                        this.hitTargetOrDeflectSelf(blockHitResult);
                        this.needsSync = true;
                    }
                }
            }
            return;}}
    private void applyInertia(final float inertia) {
        Vec3 movement = this.getDeltaMovement();
        this.setDeltaMovement(movement.scale((double)inertia));
    }
    private void addBubbleParticles(final Vec3 position) {
        Vec3 movement = this.getDeltaMovement();

        for(int i = 0; i < 4; ++i) {
            float s = 0.25F;
            this.level().addParticle(ParticleTypes.BUBBLE, position.x - movement.x * (double)0.25F, position.y - movement.y * (double)0.25F, position.z - movement.z * (double)0.25F, movement.x, movement.y, movement.z);
        }

    }

    protected double getDefaultGravity() {
        return 0.1;
    }

    public void move(final MoverType moverType, final Vec3 delta) {
        super.move(moverType, delta);
        if (moverType != MoverType.SELF && this.shouldFall()) {
            this.startFalling();
        }

    }
    public void onItemBreak(final ItemStack item) {
        this.firedFromWeapon = null;
    }

    public void onAboveBubbleColumn(final boolean dragDown, final BlockPos pos) {
        if (!this.isInGround()) {
            super.onAboveBubbleColumn(dragDown, pos);
        }
    }

    public void onInsideBubbleColumn(final boolean dragDown) {
        if (!this.isInGround()) {
            super.onInsideBubbleColumn(dragDown);
        }
    }

    public void push(final double xa, final double ya, final double za) {
        if (!this.isInGround()) {
            super.push(xa, ya, za);
        }
    }
    protected boolean isInGround() {
        return (Boolean)this.entityData.get(IN_GROUND);
    }

    protected void onHitEntity(final EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity entity = hitResult.getEntity();
        float pow = (float)this.getDeltaMovement().length();
        double flareDamage = this.baseDamage;
        Entity currentOwner = this.getOwner();
        DamageSource damageSource = this.damageSources().arrow(this, (Entity)(currentOwner != null ? currentOwner : this));
        if (this.getWeaponItem() != null) {
            Level var9 = this.level();
            if (var9 instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel)var9;
                flareDamage = (double)EnchantmentHelper.modifyDamage(serverLevel, this.getWeaponItem(), entity, damageSource, (float)flareDamage);
            }
        }

        int damage = Mth.ceil(Mth.clamp((double)pow * flareDamage, (double)0.0F, (double)Integer.MAX_VALUE));


        if (currentOwner instanceof LivingEntity livingOwner) {
            livingOwner.setLastHurtMob(entity);
        }

        int remainingFireTicks = entity.getRemainingFireTicks();
        if (this.isOnFire()) {
            entity.igniteForSeconds(5.0F);
        }

        if (entity.hurtOrSimulate(damageSource, (float)damage)) {
            if (entity instanceof LivingEntity) {
                LivingEntity mob = (LivingEntity)entity;


                this.doKnockback(mob, damageSource);
                Level var12 = this.level();
                if (var12 instanceof ServerLevel) {
                    ServerLevel serverLevel = (ServerLevel)var12;
                    EnchantmentHelper.doPostAttackEffectsWithItemSource(serverLevel, mob, damageSource, this.getWeaponItem());
                }

                this.doPostHurtEffects(mob);
                if (mob instanceof Player && currentOwner instanceof ServerPlayer) {
                    ServerPlayer ownerPlayer = (ServerPlayer)currentOwner;
                    if (!this.isSilent() && mob != ownerPlayer) {
                        ownerPlayer.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.PLAY_ARROW_HIT_SOUND, 0.0F));
                    }
                }

                if (!entity.isAlive() && this.piercedAndKilledEntities != null) {
                    this.piercedAndKilledEntities.add(mob);
                }

                if (!this.level().isClientSide() && currentOwner instanceof ServerPlayer) {
                    ServerPlayer player = (ServerPlayer)currentOwner;
                    if (this.piercedAndKilledEntities != null) {
                        CriteriaTriggers.KILLED_BY_ARROW.trigger(player, this.piercedAndKilledEntities, this.firedFromWeapon);
                    } else if (!entity.isAlive()) {
                        CriteriaTriggers.KILLED_BY_ARROW.trigger(player, List.of(entity), this.firedFromWeapon);
                    }
                }
            }



        }

    }
    protected void doKnockback(final LivingEntity mob, final DamageSource damageSource) {
        float var10000;
        label18: {
            if (this.firedFromWeapon != null) {
                Level var6 = this.level();
                if (var6 instanceof ServerLevel) {
                    ServerLevel serverLevel = (ServerLevel)var6;
                    var10000 = EnchantmentHelper.modifyKnockback(serverLevel, this.firedFromWeapon, mob, damageSource, 0.0F);
                    break label18;
                }
            }

            var10000 = 0.0F;
        }
        double knockback = (double)var10000;
        if (knockback > (double)0.0F) {
            double knockbackResistance = Math.max((double)0.0F, (double)1.0F - mob.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
            Vec3 movement = this.getDeltaMovement().multiply((double)1.0F, (double)0.0F, (double)1.0F).normalize().scale(knockback * 0.6 * knockbackResistance);
            if (movement.lengthSqr() > (double)0.0F) {
                mob.push(movement.x, 0.1, movement.z);
            }
        }

    }
    protected void onHitBlock(final BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        Level level = level();
        if (!level.isClientSide()){
            BlockPos pos = hitResult.getBlockPos().relative(hitResult.getDirection());
            if(level.isEmptyBlock(pos)){
                level.setBlockAndUpdate(pos, BaseFireBlock.getState(level, pos));
            }
        }
    }
    public @Nullable ItemStack getWeaponItem() {
        return this.firedFromWeapon;
    }

    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.ARROW_HIT;
    }

    protected final SoundEvent getHitGroundSoundEvent() {
        return this.soundEvent;
    }

    protected void doPostHurtEffects(final LivingEntity mob) {
    }
    protected @Nullable EntityHitResult findHitEntity(final Vec3 from, final Vec3 to) {
        return ProjectileUtil.getEntityHitResult(this.level(), this, from, to, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate((double)1.0F), this::canHitEntity);
    }

    protected Collection<EntityHitResult> findHitEntities(final Vec3 from, final Vec3 to) {
        return ProjectileUtil.getManyEntityHitResult(this.level(), this, from, to, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate((double)1.0F), this::canHitEntity, false, false);
    }







}






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
    private static final double ARROW_BASE_DAMAGE = (double)2.0F;
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
        this.shakeTime = 0;
        this.life = 0;
        this.baseDamage = (double)2.0F;
        this.soundEvent = this.getDefaultHitGroundSoundEvent();
        this.firedFromWeapon = null;
    }
    protected AbstractFlare(final EntityType<? extends AbstractFlare> type, final LivingEntity mob, final Level level, final ItemStack pickupItemStack, final @Nullable ItemStack firedFromWeapon) {
        this(type, mob.getX(), mob.getEyeY() - (double)0.1F, mob.getZ(), level, pickupItemStack, firedFromWeapon);
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

            this.firedFromWeapon = firedFromWeapon.copy();
            int pierceLevel = EnchantmentHelper.getPiercingCount(serverLevel, firedFromWeapon, this.pickupItemStack);
            if (pierceLevel > 0) {
                this.setPierceLevel((byte) pierceLevel);
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



}


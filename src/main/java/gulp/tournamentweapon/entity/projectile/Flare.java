package gulp.tournamentweapon.entity.projectile;


import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class Flare extends AbstractArrow {
    private static final double FLARE_DAMAGE = (double) 3.0f;
    private double basedamage;

    protected Flare(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
        this.double basedamage = 2.0f;


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
        Entity currentOwner = this.getOwner();
        double flareDamage = 4f;
        DamageSource damageSource = this.damageSources().arrow(this, (Entity) (currentOwner != null ? currentOwner : this));
        if (this.getWeaponItem() != null) {
            Level var9 = this.level();
            if (var9 instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel) var9;
                flareDamage = (double) EnchantmentHelper.modifyDamage(serverLevel, this.getWeaponItem(), entity, damageSource, (float) flareDamage);
            }
        }
        if (entity.hurtOrSimulate(damageSource, (float) flareDamage)) {
            if (entity instanceof LivingEntity) {
                LivingEntity mob = (LivingEntity) entity;
                if (!this.level().isClientSide() && this.getPierceLevel() <= 0) {
                    mob.setArrowCount(mob.getArrowCount() + 1);
                }

                this.doKnockback(mob, damageSource);
                Level var12 = this.level();
                if (var12 instanceof ServerLevel) {
                    ServerLevel serverLevel = (ServerLevel) var12;
                    EnchantmentHelper.doPostAttackEffectsWithItemSource(serverLevel, mob, damageSource, this.getWeaponItem());
                }

                this.doPostHurtEffects(mob);
                if (mob instanceof Player && currentOwner instanceof ServerPlayer) {
                    ServerPlayer ownerPlayer = (ServerPlayer) currentOwner;
                    if (!this.isSilent() && mob != ownerPlayer) {
                        ownerPlayer.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.PLAY_ARROW_HIT_SOUND, 0.0F));
                    }
                }

            }


        }
    }
}



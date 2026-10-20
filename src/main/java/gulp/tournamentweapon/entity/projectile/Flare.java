package gulp.tournamentweapon.entity.projectile;


import net.minecraft.advancements.triggers.CriteriaTriggers;
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

import java.util.List;

public class Flare extends AbstractArrow {
    private static final double FLARE_DAMAGE = (double) 3.0f;
    private double flaredamage;
    protected Flare(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
        this.double basedamage = 2.0f;


    }


    @Override
    protected ItemStack getDefaultPickupItem() {
        return null;
    }

    @Override
    public void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity entity = hitResult.getEntity();
        entity.igniteForSeconds(15);
        int damage = (int) this.flaredamage;
        DamageSource damageSource = this.damageSources().arrow(this, (Entity)(currentOwner != null ? currentOwner : this));
        if (this.getWeaponItem() != null) {
            Level var9 = this.level();
            if (var9 instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel)var9;
                arrowDamage = (double)EnchantmentHelper.modifyDamage(serverLevel, this.getWeaponItem(), entity, damageSource, (float)arrowDamage);
            }
        }
        if (entity.hurtOrSimulate(damageSource, (float)damage)) {
            if (entity instanceof LivingEntity) {
                LivingEntity mob = (LivingEntity)entity;
                if (!this.level().isClientSide() && this.getPierceLevel() <= 0) {
                    mob.setArrowCount(mob.getArrowCount() + 1);
                }

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




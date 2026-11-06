package gulp.tournamentweapon.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public class HammerItem extends Item {
    public HammerItem(Properties properties) {
        super(properties);
    }

    @Override
    public void postHurtEnemy(ItemStack itemStack, LivingEntity entity, LivingEntity attacker) {
        super.postHurtEnemy(itemStack, entity, attacker);
        double height = attacker.getY();
        System.out.println("detected hurt");

        if (height < 50 && height >= 0) {
            System.out.println("detected");
            if (entity.level() instanceof ServerLevel serverLevel) {
                DamageSource damageSource = attacker.damageSources().playerAttack(
                        attacker instanceof Player player ? player : null
                );
                entity.hurtServer(serverLevel, damageSource, 4f);
                entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 15));
                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 15));
                System.out.println("lowest damage");
            }
        } else if (height < 100 && height >= 50)  {
            if (entity.level() instanceof ServerLevel serverLevel) {
                DamageSource damageSource = attacker.damageSources().playerAttack(
                        attacker instanceof Player player ? player : null
                );
                entity.hurtServer(serverLevel, damageSource, 8f);
                entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20));
                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20));
                System.out.println("medium damage");
            }

        } else if (height >= 100 && height < 150) {
            if (entity.level() instanceof ServerLevel serverLevel) {
                DamageSource damageSource = attacker.damageSources().playerAttack(
                        attacker instanceof Player player ? player : null
                );
                entity.hurtServer(serverLevel, damageSource, 12f);
                entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 30));
                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 30));
                System.out.println("highest damage");
            }

        }

    }
}





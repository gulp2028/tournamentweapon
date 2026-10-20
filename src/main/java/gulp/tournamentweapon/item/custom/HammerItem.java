package gulp.tournamentweapon.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
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
    public void hurtEnemy(ItemStack itemStack,LivingEntity entity,LivingEntity attacker) {
        double height = attacker.getY();
        double damage = height*0.75;
        if( height >= 0 && height < 50) {
            if (entity.level() instanceof ServerLevel serverLevel) {
                DamageSource damageSource = attacker.damageSources().playerAttack(
                        attacker instanceof Player player ? player : null
                );

        } else if (height >= 50 && height < 100) {
                if (entity.level() instanceof ServerLevel serverLevel) {
                    DamageSource damageSource = attacker.damageSources().playerAttack(
                            attacker instanceof Player player ? player : null
                    );
            }

        } else if (height >= 100 && height < 150){
                if (entity.level() instanceof ServerLevel serverLevel) {
                    DamageSource damageSource = attacker.damageSources().playerAttack(
                            attacker instanceof Player player ? player : null
                    );
            }

        }
    }




}




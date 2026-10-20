package gulp.tournamentweapon.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Ice_AxeItem extends Item {
    public Ice_AxeItem(Properties properties) {
        super(properties);
    }
    @Override
    public void hurtEnemy(ItemStack itemStack, LivingEntity entity, LivingEntity attacker){
        if(entity.isOnFire()){
            entity.clearFire();
            //entity.hurt()

        }

    }



}

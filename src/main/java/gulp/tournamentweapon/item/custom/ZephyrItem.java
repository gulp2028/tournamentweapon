package gulp.tournamentweapon.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ZephyrItem extends Item {
    private int bloodlevel = 0;
    private int ragetime = 0;
    public ZephyrItem(Properties properties){
        super(properties);
    }

    @Override
    public void postHurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker){
        if(bloodlevel == 0){
            bloodlevel = 1;
            attacker.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 1));

        }if(bloodlevel == 1){
            bloodlevel = 2;
            attacker.addEffect(new MobEffectInstance(MobEffects.SPEED, 100, 2));
        }if(bloodlevel == 2){
            bloodlevel = 3;
            attacker.addEffect(new MobEffectInstance(MobEffects.SPEED, 150, 3));
        }else{
            attacker.addEffect(new MobEffectInstance(MobEffects.SPEED, 200, 5));
            attacker.level().playSound(null, attacker.blockPosition(), SoundEvents.ANVIL_PLACE,
                    SoundSource.PLAYERS, 2.0f, 1f);
        }

    }

}

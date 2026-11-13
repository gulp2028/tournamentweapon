package gulp.tournamentweapon.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class ZephyrItem extends Item {
    private int bloodlevel = 0;
    private int ragetime = 0;
    private int cooldown = 20;
    public ZephyrItem(Properties properties){
        super(properties);
    }



    @Override
    public void postHurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker){
            if(bloodlevel == 0){
                ragetime = 100;
                bloodlevel = 1;
                attacker.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 1));

            }else if(bloodlevel == 1){
                ragetime = 100;
                bloodlevel = 2;
                attacker.addEffect(new MobEffectInstance(MobEffects.SPEED, 100, 2));
            }else if(bloodlevel == 2){
                ragetime = 100;
                bloodlevel = 3;
                attacker.addEffect(new MobEffectInstance(MobEffects.SPEED, 150, 3));
            }else{
                ragetime = 100;
                attacker.addEffect(new MobEffectInstance(MobEffects.SPEED, 200, 5));
                target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100));
                attacker.level().playSound(null, attacker.blockPosition(), SoundEvents.ANVIL_PLACE,
                        SoundSource.PLAYERS, 2.0f, 1f);
            }
        }


    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        super.inventoryTick(itemStack, level, owner, slot);
        if(ragetime>0) {
            ragetime--;
        }
        if(ragetime == 0) {
            bloodlevel = 0;

        }
    }
}

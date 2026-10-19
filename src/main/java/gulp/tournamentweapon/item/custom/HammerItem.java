package gulp.tournamentweapon.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HammerItem extends Item {
    public HammerItem(Properties properties) {
        super(properties);
    }
    @Override
    public void postHurtEnemy(ItemStack itemStack, LivingEntity entity, LivingEntity attacker) {
        double height = attacker.getY();
        float strength = (float) (Math.max(0, height)*0.1f);
        if(height < 100f){
           entity.hurt(attacker.damageSources().mobAttack(attacker), strength);
        }

    }}




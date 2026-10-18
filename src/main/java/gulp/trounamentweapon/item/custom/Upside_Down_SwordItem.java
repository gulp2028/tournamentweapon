package gulp.trounamentweapon.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Upside_Down_SwordItem extends Item {
    public Upside_Down_SwordItem(Properties properties) {
        super(properties);
    }

    @Override
    public void postHurtEnemy(ItemStack itemStack, LivingEntity entity, LivingEntity attacker) {
        super.postHurtEnemy(itemStack, entity, attacker);
        entity.teleportRelative(0, 50, 0);
    }
}

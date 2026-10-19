package gulp.trounamentweapon.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HammerItem extends Item {
    public HammerItem(Properties properties) {
        super(properties);
    }
    @Override
    public void postHurtEnemy(ItemStack itemStack, LivingEntity entity, LivingEntity attacker)


}

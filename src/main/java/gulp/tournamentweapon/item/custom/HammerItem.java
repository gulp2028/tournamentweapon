package gulp.tournamentweapon.item.custom;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public class HammerItem extends Item {
    public HammerItem(Properties properties) {
        super(properties);
    }


    @Override
    public void HurtEnemy(@NonNull ItemStack itemStack, @NonNull LivingEntity entity, LivingEntity attacker) {
        double height = attacker.getY();
        double damage = height * 0.1;




        }

    }
}




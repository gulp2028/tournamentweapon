package gulp.tournamentweapon.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Winged_SwordItem extends Item {
    public Winged_SwordItem(Properties properties) {
        super(properties);
    }

    @Override
    public void postHurtEnemy(ItemStack itemStack, LivingEntity entity, LivingEntity attacker) {
        super.postHurtEnemy(itemStack, entity, attacker);

        entity.teleportRelative(0, 50, 0);
        attacker.teleportRelative(0,54,0);
        super.postHurtEnemy(itemStack, entity, attacker);


        if (!(attacker instanceof Player player)) {
            return;
        }

        if (player.getCooldowns().isOnCooldown(itemStack)) {
            return;
        }
        player.getCooldowns().addCooldown(itemStack, 60);
    }


    }


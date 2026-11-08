package gulp.tournamentweapon.item.custom;

import gulp.tournamentweapon.entity.projectile.FlareEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

import java.util.Properties;

public class FlareItem extends Item {
    public FlareItem(Properties properties){
        super(properties);}

    //public FlareEntity createFlare(Level level, ItemStack itemStack, LivingEntity owner, Direction direction, @Nullable ItemStack firedfromweapon){
    //    return new FlareEntity(level, owner, itemStack.copyWithCount(1), firedfromweapon);

    //}
}

package gulp.tournamentweapon.entity;

import gulp.tournamentweapon.entity.projectile.FlareEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import javax.swing.text.html.parser.Entity;


public class ModEntityTypes {
    public static final EntityType<FlareEntity> FLARE = register(
            ModEntityTypeIds.FLARE_ENTITY,
            EntityType.Builder.<FlareEntity>of(FlareEntity::new, MobCategory.MISC)
            .sized(0.2F, 0.2F));

    private static <T extends Entity> EntityType<FlareEntity> register(ResourceKey<EntityType<?>> key, EntityType.Builder<FlareEntity> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void initialize(){}
}

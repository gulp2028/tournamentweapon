package gulp.tournamentweapon.entity;

import gulp.tournamentweapon.Tournamentweapon;
import gulp.tournamentweapon.entity.projectile.FlareEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

    public static final ResourceKey<EntityType<?>> FLARE_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(Tournamentweapon.MOD_ID, "flare_entity"));
    public static final EntityType<FlareEntity> FLARE = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(Tournamentweapon.MOD_ID, "flare_entity"),
            EntityType.Builder.of(FlareEntity::new, MobCategory.MISC).sized(0.5F, 0.5F));




    public static void registerModEntities() {
        Tournamentweapon.LOGGER.info("Registering Mod Entities for " + Tournamentweapon.MOD_ID);
    }

}

package gulp.tournamentweapon.entity;

import gulp.tournamentweapon.Tournamentweapon;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;

public class ModEntities {

    public static final ResourceKey<EntityType<?>> FLARE_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(Tournamentweapon.MOD_ID, "flare_entity"));




    public static void registerModEntities() {
        Tournamentweapon.LOGGER.info("Registering Mod Entities for " + Tournamentweapon.MOD_ID);
    }

}

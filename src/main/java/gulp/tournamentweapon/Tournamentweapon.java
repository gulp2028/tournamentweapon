package gulp.tournamentweapon;

import gulp.tournamentweapon.entity.ModEntities;
import gulp.tournamentweapon.entity.ModEntityTypes;
import gulp.tournamentweapon.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tournamentweapon implements ModInitializer {
	public static final String MOD_ID = "tournamentweapon";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModEntities.registerModEntities();
		ModEntityTypes.initialize();


		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}

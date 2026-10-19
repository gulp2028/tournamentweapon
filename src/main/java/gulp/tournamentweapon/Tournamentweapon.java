package gulp.tournamentweapon;

import gulp.tournamentweapon.item.ModItems;
import gulp.tournamentweapon.tags.ModTags;
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


		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}

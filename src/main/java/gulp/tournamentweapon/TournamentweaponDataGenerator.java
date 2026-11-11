package gulp.tournamentweapon;

import gulp.tournamentweapon.datagen.ModModelProvider;

import gulp.tournamentweapon.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TournamentweaponDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModModelProvider::new);

	}
}

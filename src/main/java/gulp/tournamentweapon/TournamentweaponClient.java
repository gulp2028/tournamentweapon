package gulp.tournamentweapon;

import gulp.tournamentweapon.entity.ModEntityTypes;
import net.fabricmc.api.ClientModInitializer;

public class TournamentweaponClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(){
        ModEntityTypes.initialize();
    }

}

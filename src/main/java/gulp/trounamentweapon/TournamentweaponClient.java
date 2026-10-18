package gulp.trounamentweapon;

import net.fabricmc.api.ClientModInitializer;

public class TournamentweaponClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(){
        ModItems.registerItems();
    }

}

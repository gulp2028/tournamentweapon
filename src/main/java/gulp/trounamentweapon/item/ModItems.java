package gulp.trounamentweapon.item;

import gulp.trounamentweapon.Tournamentweapon;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.mixin.creativetab.CreativeModeTabAccessor;
import net.minecraft.world.item.CreativeModeTabs;

public class ModItems {
    public static void registerModItems(){
        Tournamentweapon.LOGGER.info("Registering Mod Items for " + Tournamentweapon.MOD_ID);
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> );

    }
}

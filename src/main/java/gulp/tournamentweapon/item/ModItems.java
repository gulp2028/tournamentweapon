package gulp.tournamentweapon.item;

import gulp.tournamentweapon.Tournamentweapon;
import gulp.tournamentweapon.item.custom.Flare_GunItem;
import gulp.tournamentweapon.item.custom.HammerItem;
import gulp.tournamentweapon.item.custom.Ice_AxeItem;
import gulp.tournamentweapon.item.custom.Winged_SwordItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {
    public static final Item WINGED_SWORD = registerItem("winged_sword",
            properties -> new Winged_SwordItem(properties.sword(ModToolMaterials.UPSIDEDOWNIUM,
                    4, -2.4f)));
    public static final Item HAMMER = registerItem("hammer",
            properties -> new HammerItem(properties.sword(ModToolMaterials.SHORTIUM,
                    2, -2)));
    public static final Item FLARE_GUN = registerItem("flare_gun",
            properties -> new Flare_GunItem(properties.durability(4)));
    public static final Item ICE_AXE = registerItem("ice_axe",
            properties -> new Ice_AxeItem(properties.axe(ModToolMaterials.ICIUM,
                    2, -2)));
    //public static final ShieldItem COMBO_SHIELD = registerItem("combo_shield",
       //     properties -> new Item(properties.)




    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Tournamentweapon.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Tournamentweapon.MOD_ID, name)))));
    }

    public static void registerModItems() {
        Tournamentweapon.LOGGER.info("Registering Mod Items for " + Tournamentweapon.MOD_ID);
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            output.accept(WINGED_SWORD);
            output.accept(HAMMER);
                }

                );

    }
}

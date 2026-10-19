package gulp.trounamentweapon.item;

import gulp.trounamentweapon.Tournamentweapon;
import gulp.trounamentweapon.item.custom.Upside_Down_SwordItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.mixin.creativetab.CreativeModeTabAccessor;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;

import java.util.function.Function;

public class ModItems {
    public static final Item UPSIDE_DOWN_SWORD = registerItem("upside_down_sword",
            properties -> new Upside_Down_SwordItem(properties.sword(ModToolMaterials.UPSIDEDOWNIUM,
                    4, -2.4f)));
    //public static final ShieldItem COMBO_SHIELD = registerItem("combo_shield",
     //       properties -> new Item(properties.)
      //      )

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Tournamentweapon.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Tournamentweapon.MOD_ID, name)))));
    }

    public static void registerModItems() {
        Tournamentweapon.LOGGER.info("Registering Mod Items for " + Tournamentweapon.MOD_ID);
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            output.accept(UPSIDE_DOWN_SWORD);
                }

                );

    }
}

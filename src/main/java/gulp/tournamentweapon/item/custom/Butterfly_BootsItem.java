package gulp.tournamentweapon.item.custom;

import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;

public class Butterfly_BootsItem extends HumanoidArmorLayer {
    public Butterfly_BootsItem(RenderLayerParent renderer, ArmorModelSet modelSet, EquipmentLayerRenderer equipmentRenderer) {
        super(renderer, modelSet, equipmentRenderer);
    }

    public Butterfly_BootsItem(RenderLayerParent renderer, ArmorModelSet modelSet, ArmorModelSet babyModelSet, EquipmentLayerRenderer equipmentRenderer) {
        super(renderer, modelSet, babyModelSet, equipmentRenderer);
    }
}

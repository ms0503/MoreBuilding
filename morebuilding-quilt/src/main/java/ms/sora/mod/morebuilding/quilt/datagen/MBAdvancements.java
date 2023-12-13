package ms.sora.mod.morebuilding.quilt.datagen;

import java.util.function.Consumer;
import ms.sora.mod.morebuilding.common.MBBlocks;
import ms.sora.mod.morebuilding.common.MBInfo;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.ItemConvertible;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

@SuppressWarnings("unused")
final class MBAdvancements extends FabricAdvancementProvider {
    MBAdvancements(FabricDataOutput output) {
        super(output);
    }

    public static String[] getAdvancementTranslationKeys(String id) {
        return new String[]{
            Util.createTranslationKey("advancements", new Identifier(MBInfo.MOD_ID, "%s.title".formatted(id))),
            Util.createTranslationKey("advancements", new Identifier(MBInfo.MOD_ID, "%s.description".formatted(id)))
        };
    }

    private static String gotItem(ItemConvertible item) {
        return "got_%s".formatted(item.asItem().toString());
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        Advancement backpack = Advancement.Task.create()
            .display(
                MBBlocks.BACKPACK.get(),
                Text.translatable(getAdvancementTranslationKeys("backpack")[0]),
                Text.translatable(getAdvancementTranslationKeys("backpack")[1]),
                new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.TASK,
                true,
                true,
                false
            )
            .criterion(gotItem(MBBlocks.BACKPACK.get()), InventoryChangedCriterion.Conditions.items(MBBlocks.BACKPACK.get()))
            .build(consumer, "%s/backpack".formatted(MBInfo.MOD_ID));
    }
}

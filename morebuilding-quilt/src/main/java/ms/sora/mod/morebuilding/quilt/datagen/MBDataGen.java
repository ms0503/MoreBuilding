package ms.sora.mod.morebuilding.quilt.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

/**
 * MBDataGen is a Quilt-dependent part of datagen entrypoint of this mod.
 * <p>
 * This class contains data generator of this mod.
 *
 * @since 0.1.0
 */
@SuppressWarnings("unused")
public final class MBDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(MBAdvancements::new);
        pack.addProvider(MBBlockLootTables::new);
        pack.addProvider(MBModels::new);
        pack.addProvider(MBRecipes::new);
        pack.addProvider(MBTags.BlockTags::new);
        pack.addProvider(MBTranslations.MBEnglishUnitedStatesTranslations::new);
        pack.addProvider(MBTranslations.MBJapaneseJapanTranslations::new);
    }
}

package ms.sora.mod.morebuilding.quilt.datagen;

import ms.sora.mod.morebuilding.common.MBBlocks;
import ms.sora.mod.morebuilding.common.MBCore;
import ms.sora.mod.morebuilding.common.MBInfo;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

final class MBTranslations {
    private static void addAdvancement(FabricLanguageProvider.TranslationBuilder builder, String id, String title, String description) {
        builder.add(MBAdvancements.getAdvancementTranslationKeys(id)[0], title);
        builder.add(MBAdvancements.getAdvancementTranslationKeys(id)[1], description);
    }

    private static void addContainer(FabricLanguageProvider.TranslationBuilder builder, String id, String name, String more) {
        builder.add(Util.createTranslationKey("container", new Identifier(MBInfo.MOD_ID, id)), name);
        builder.add(Util.createTranslationKey("container", new Identifier(MBInfo.MOD_ID, "%s.more".formatted(id))), more);
    }

    static final class MBEnglishUnitedStatesTranslations extends FabricLanguageProvider {
        private TranslationBuilder builder;

        MBEnglishUnitedStatesTranslations(FabricDataOutput output) {
            super(output, "en_us");
        }

        @Override
        public void generateTranslations(TranslationBuilder builder) {
            this.builder = builder;
            generateAdvancements();
            generateBlocks();
            generateContainer();
            generateCreativeTabs();
            generateModMenu();
        }

        private void generateAdvancements() {
            addAdvancement(this.builder, "backpack", "Let's Go!", "Got a Backpack");
        }

        private void generateBlocks() {
            this.builder.add(MBBlocks.BACKPACK.get(), "Backpack");
            this.builder.add(MBBlocks.COARSE_BRICKS.get(), "Coarse Bricks");
            this.builder.add(MBBlocks.DIAMOND_BRICKS.get(), "Diamond Bricks");
            this.builder.add(MBBlocks.EMERALD_BRICKS.get(), "Emerald Bricks");
            this.builder.add(MBBlocks.FINE_SAND_BRICKS.get(), "Fine Sand Bricks");
            this.builder.add(MBBlocks.FINE_STONE_BRICKS.get(), "Fine Stone Bricks");
            this.builder.add(MBBlocks.GOLD_BRICKS.get(), "Gold Bricks");
            this.builder.add(MBBlocks.IRON_BRICKS.get(), "Iron Bricks");
            this.builder.add(MBBlocks.SAND_BRICKS.get(), "Sand Bricks");
        }

        private void generateContainer() {
            addContainer(this.builder, MBBlocks.BACKPACK.getId().getPath(), "Backpack", "と%s個のアイテム");
        }

        private void generateCreativeTabs() {
            this.builder.add(MBCore.MORE_BUILDING_TAB.getKey(), "More Building");
        }

        private void generateModMenu() {
            this.builder.add("modmenu.descriptionTranslation.%s".formatted(MBInfo.MOD_ID), MBInfo.MOD_DESCRIPTION);
        }
    }

    static final class MBJapaneseJapanTranslations extends FabricLanguageProvider {
        private TranslationBuilder builder;

        MBJapaneseJapanTranslations(FabricDataOutput output) {
            super(output, "ja_jp");
        }

        @Override
        public void generateTranslations(TranslationBuilder builder) {
            this.builder = builder;
            generateAdvancements();
            generateBlocks();
            generateContainer();
            generateCreativeTabs();
            generateModMenu();
        }

        private void generateAdvancements() {
            addAdvancement(this.builder, "backpack", "さあ、出発だ！", "バックパックを入手する");
        }

        private void generateBlocks() {
            this.builder.add(MBBlocks.BACKPACK.get(), "バックパック");
            this.builder.add(MBBlocks.COARSE_BRICKS.get(), "粗いレンガ");
            this.builder.add(MBBlocks.DIAMOND_BRICKS.get(), "ダイヤモンドレンガ");
            this.builder.add(MBBlocks.EMERALD_BRICKS.get(), "エメラルドレンガ");
            this.builder.add(MBBlocks.FINE_SAND_BRICKS.get(), "細かい砂レンガ");
            this.builder.add(MBBlocks.FINE_STONE_BRICKS.get(), "細かい石レンガ");
            this.builder.add(MBBlocks.GOLD_BRICKS.get(), "金レンガ");
            this.builder.add(MBBlocks.IRON_BRICKS.get(), "鉄レンガ");
            this.builder.add(MBBlocks.SAND_BRICKS.get(), "砂レンガ");
        }

        private void generateContainer() {
            addContainer(this.builder, MBBlocks.BACKPACK.getId().getPath(), "Backpack", "and %s more...");
        }

        private void generateCreativeTabs() {
            this.builder.add(MBCore.MORE_BUILDING_TAB.getKey(), "More Building");
        }

        private void generateModMenu() {
            this.builder.add("modmenu.descriptionTranslation.%s".formatted(MBInfo.MOD_ID), MBInfo.MOD_DESCRIPTION);
        }
    }
}

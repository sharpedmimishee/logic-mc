package sharped.mimishee.logic.providers;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import sharped.mimishee.logic.Blocks.BlocksRegister;
import sharped.mimishee.logic.Items.ItemsRegister;

import java.util.Set;

public class blockloottable extends BlockLootSubProvider {
    protected blockloottable(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }


    protected LootTable.Builder createDrops(Block block, Item item, float min, float max) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                block,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }


    @Override
    protected void generate() {
        add(BlocksRegister.TIN_ORE.get(),
                block -> createDrops(BlocksRegister.TIN_ORE.get(), ItemsRegister.RAW_TIN.get(), 1, 2));
        add(BlocksRegister.DEEPSLATE_TIN_ORE.get(),
                block -> createDrops(BlocksRegister.DEEPSLATE_TIN_ORE.get(), ItemsRegister.RAW_TIN.get(), 1, 2));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlocksRegister.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}

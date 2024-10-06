package sharped.mimishee.logic.providers;

import com.mojang.logging.LogUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import sharped.mimishee.logic.Logic;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Logic.MODID)
public class providermanager {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        // Data generators may require some of these as constructor parameters.
        // See below for more details on each of these.
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Register the provider.
        generator.addProvider(
                // A boolean that determines whether the data should actually be generated.
                // The event provides methods that determine this:
                // event.includeClient(), event.includeServer(),
                // event.includeDev() and event.includeReports().
                // Since recipes are server data, we only run them in a server datagen.
                event.includeClient(),
                // Our provider.
                new blockstates(output, existingFileHelper)
        );
        generator.addProvider(event.includeClient(), new itemmodels(output, existingFileHelper));
        generator.addProvider(event.includeServer(), new LootTableProvider(output, Collections.emptySet(),
                List.of(
                        new LootTableProvider.SubProviderEntry(blockloottable::new, LootContextParamSets.BLOCK)
                ), lookupProvider));
        blocktags Blocktagsprovider = new blocktags(output, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeClient(), Blocktagsprovider);
        generator.addProvider(event.includeClient(), new itemtags(output, lookupProvider,
                Blocktagsprovider.contentsGetter(), existingFileHelper));

    }
}

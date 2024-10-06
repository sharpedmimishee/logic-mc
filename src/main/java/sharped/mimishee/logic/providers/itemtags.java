package sharped.mimishee.logic.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import sharped.mimishee.logic.Items.ItemsRegister;
import sharped.mimishee.logic.Logic;

import java.util.concurrent.CompletableFuture;

public class itemtags extends ItemTagsProvider {
    public itemtags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Logic.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath(Logic.MODID,"ores/tin")))
                .add(ItemsRegister.RAW_TIN.value());
    }
}

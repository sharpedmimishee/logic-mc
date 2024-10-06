package sharped.mimishee.logic.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import sharped.mimishee.logic.Blocks.BlocksRegister;
import sharped.mimishee.logic.Logic;

import java.util.concurrent.CompletableFuture;

public class blocktags extends BlockTagsProvider {
    public blocktags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Logic.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlocksRegister.DEEPSLATE_TIN_ORE.get())
                .add(BlocksRegister.TIN_ORE.get());
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(BlocksRegister.DEEPSLATE_TIN_ORE.value())
                .add(BlocksRegister.TIN_ORE.value());
    }
}

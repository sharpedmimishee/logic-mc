package sharped.mimishee.logic.providers;

import com.mojang.logging.LogUtils;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import sharped.mimishee.logic.Blocks.BlocksRegister;
import sharped.mimishee.logic.Logic;

public class blockstates extends BlockStateProvider {
    public blockstates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Logic.MODID, exFileHelper);
    }

    private void SimpleBlockWithItem(DeferredBlock<?> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }

    @Override
    protected void registerStatesAndModels() {
        LogUtils.getLogger().info("PLSPLSPLS");
        SimpleBlockWithItem(BlocksRegister.TIN_ORE);
        SimpleBlockWithItem(BlocksRegister.DEEPSLATE_TIN_ORE);
    }
}

package sharped.mimishee.logic.Blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import sharped.mimishee.logic.Items.ItemsRegister;
import sharped.mimishee.logic.Logic;

import java.util.function.Supplier;

public class BlocksRegister {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Logic.MODID);
    //BLOCKS is a DeferredRegister.Blocks
    public static final DeferredBlock<Block> TIN_ORE = registerBlock(
            "tin_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .destroyTime(2.0f)
                    .explosionResistance(10.0f)
                    .sound(SoundType.STONE)
                    .lightLevel(state -> 7)
            ));
    public static final DeferredBlock<Block> DEEPSLATE_TIN_ORE = registerBlock(
            "deepslate_tin_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .destroyTime(2.0f)
                    .explosionResistance(10.0f)
                    .sound(SoundType.STONE)
                    .lightLevel(state -> 7)
            ));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> deferredBlock = BLOCKS.register(name, block);
        ItemsRegister.ITEMS.register(name,
                () -> new BlockItem(deferredBlock.get(), new Item.Properties()));
        return deferredBlock;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

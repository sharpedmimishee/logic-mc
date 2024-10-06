package sharped.mimishee.logic.providers;

import com.mojang.logging.LogUtils;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import sharped.mimishee.logic.Items.ItemsRegister;
import sharped.mimishee.logic.Logic;

public class itemmodels extends ItemModelProvider {
    public itemmodels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Logic.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemsRegister.RAW_TIN.get());
    }
}

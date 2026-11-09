package net.yigitguven.invisible_armors;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(InvisibleArmors.MODID)
public class InvisibleArmors {
    public static final String MODID = "invisible_armors";
    public static final Logger LOGGER = LogUtils.getLogger();

    public InvisibleArmors(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        LOGGER.info("Invisible Armors initialized");
    }
}

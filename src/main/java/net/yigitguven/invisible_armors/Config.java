package net.yigitguven.invisible_armors;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLE_SERVER_PACKET_SPOOFING = BUILDER
            .comment("Whether the server should hide invisible armor in equipment packets sent to other players (allows vanilla clients to see invisible armor as hidden).")
            .define("enableServerPacketSpoofing", true);

    public static final ModConfigSpec.BooleanValue ALLOW_ELYTRA = BUILDER
            .comment("Whether Elytras can also be made invisible using Amethyst Shards.")
            .define("allowElytra", true);

    public static final ModConfigSpec.BooleanValue ALLOW_ANIMAL_ARMOR = BUILDER
            .comment("Whether Horse and Wolf armor can also be made invisible using Amethyst Shards.")
            .define("allowAnimalArmor", true);

    static final ModConfigSpec SPEC = BUILDER.build();
}

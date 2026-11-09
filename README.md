# Invisible Armors

Invisible Armors allows players to make armor pieces invisible by crafting them with Amethyst Shards, without adding any new items to the game.

## Links & Downloads

- **CurseForge:** [curseforge.com/minecraft/mc-mods/invisible-armors](https://www.curseforge.com/minecraft/mc-mods/invisible-armors)
- **Modrinth:** [modrinth.com/mod/invisiblearmors](https://modrinth.com/mod/invisiblearmors)
- **Source Code:** [github.com/yigit-guven/Invisible-Armors](https://github.com/yigit-guven/Invisible-Armors)
- **Issue Tracker:** [github.com/yigit-guven/Invisible-Armors/issues](https://github.com/yigit-guven/Invisible-Armors/issues)
- **Wiki:** [github.com/yigit-guven/Invisible-Armors/wiki](https://github.com/yigit-guven/Invisible-Armors/wiki)

## Features

- **No New Items:** Uses vanilla data components (`custom_data` and `lore`). Preserves enchantments, armor trims, dyes, durability, and custom names.
- **Broad Compatibility:** Works with vanilla and modded armors, Elytras, Horse Armor, and Wolf Armor.
- **Reversible:** Invisibility can be removed at any time in a crafting grid.

## Client & Server Compatibility

| Environment | Supported | Details |
| :--- | :---: | :--- |
| **Server Only** | Yes | Vanilla clients can join without issues. The server hides invisible armor in equipment packets sent to other players. *(Wearers only see their own armor in 3rd person / F5 unless they also install the mod).* |
| **Client Only** | Yes | Singleplayer and client-side rendering. |
| **Both Client & Server** | Yes | Full support (recommended). Armor, trims, and glint are hidden from all perspectives. |

## Recipes

### Make Armor Invisible
Place any armor piece in the center of a Crafting Table and surround it with 8 Amethyst Shards:

| | | |
| :---: | :---: | :---: |
| Amethyst Shard | Amethyst Shard | Amethyst Shard |
| Amethyst Shard | **Armor Piece** | Amethyst Shard |
| Amethyst Shard | Amethyst Shard | Amethyst Shard |

*Output: Invisible Armor Piece (marked with an "Invisible" tooltip)*

### Remove Invisibility
Place the invisible armor piece alone in any Crafting Table or 2x2 player crafting grid:

| | |
| :---: | :---: |
| **Invisible Armor** | *(empty)* |
| *(empty)* | *(empty)* |

*Output: Visible Armor Piece*

## Configuration

Located at `config/invisible_armors-common.toml`:

- `enableServerPacketSpoofing` (default: `true`) - Hides invisible armor from players who do not have the mod installed.
- `allowElytra` (default: `true`) - Allows Elytras to be made invisible.
- `allowAnimalArmor` (default: `true`) - Allows Horse and Wolf armor to be made invisible.

## License & Credits

- **Author:** Yigit_Guven
- **License:** [MIT](LICENSE)
- **Bug Reports & Feedback:** [GitHub Issues](https://github.com/yigit-guven/Invisible-Armors/issues)

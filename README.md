# Invisible Armors

Show off your skin while keeping full armor protection! **Invisible Armors** lets you turn any armor piece invisible using **Amethyst Shards**—without adding any new items to the game.

---

## ✨ Features

- **No New Items:** Modifies existing armor using vanilla data components, so it works with **all vanilla and modded armors** (including Elytras, Horse Armor, and Wolf Armor).
- **Keeps Everything:** Preserves all enchantments, armor trims, dye colors, durability, and custom names.
- **Server & Client Friendly:** Works in Singleplayer and Multiplayer. Even vanilla clients on a server won't see invisible armor worn by other players!
- **100% Reversible:** Easily make your armor visible again at any time.

---

## 🛠️ Recipes

### 1. Make Armor Invisible
Place **any armor piece** in the center of a Crafting Table and surround it with **8 Amethyst Shards**:

| | | |
| :---: | :---: | :---: |
| 🟣 Amethyst Shard | 🟣 Amethyst Shard | 🟣 Amethyst Shard |
| 🟣 Amethyst Shard | 🛡️ **Any Armor Piece** | 🟣 Amethyst Shard |
| 🟣 Amethyst Shard | 🟣 Amethyst Shard | 🟣 Amethyst Shard |

**Result:** Invisible Armor Piece *(marked with an `Invisible` tooltip)*

---

### 2. Remove Invisibility
Place the **Invisible Armor Piece** by itself in any Crafting Table or your 2×2 inventory crafting grid:

| | |
| :---: | :---: |
| 🛡️ **Invisible Armor** | *(Empty)* |
| *(Empty)* | *(Empty)* |

**Result:** Original Visible Armor Piece

---

## ⚙️ Configuration

Located in `config/invisible_armors-common.toml` (or via the in-game Mods config menu):
- `enableServerPacketSpoofing` *(default: `true`)* — Hides invisible armor from other players even if they don't have the mod installed.
- `allowElytra` *(default: `true`)* — Allows Elytras to be made invisible.
- `allowAnimalArmor` *(default: `true`)* — Allows Horse and Wolf armor to be made invisible.

---

## 📄 License & Credits

- **Author:** Yigit_Guven
- **License:** [MIT](LICENSE)

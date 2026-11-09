# Changelog

All notable changes to **Invisible Armors** are documented in this file.

## [2.0.0] - 2026-11-09

### Added
- **Component-Based Invisibility:** Switched to vanilla `DataComponents.CUSTOM_DATA` and `DataComponents.LORE`. No custom items are registered, leaving registries untouched.
- **Crafting Invisibility:** Surround any armor piece in a Crafting Table with 8 Amethyst Shards to make it invisible.
- **Reversible Invisibility:** Place an invisible armor piece alone in any crafting grid to restore it to visible armor.
- **Server Packet Spoofing:** Server intercepts and filters equipment packets so vanilla players cannot see worn invisible armor on other players.
- **Client Rendering Support:** Added layer mixins to hide 3D armor, trims, and glint for humanoid armor, Elytras, Horse armor, and Wolf armor.
- **Configuration:** Options in `config/invisible_armors-common.toml` to toggle server packet spoofing, Elytras, and animal armor.
- **Documentation & Links:** Added comprehensive documentation, MIT license, CurseForge, Modrinth, and GitHub issue tracker links.

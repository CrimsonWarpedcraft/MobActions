# MobActions

Welcome to MobActions! A simple way to turn any mob into actions players can click to use!

## Simple and Stable
Works with Paper, Spigot, and Bukkit servers!

Native Version: **1.16**  
Tested Minecraft Versions: **1.16**

![Build and Artifact](https://github.com/CrimsonWarpedcraft/MobActions/workflows/Build%20and%20Artifact/badge.svg?event=push)

## Features
For a simple plugin, we pack a few powerful features!

### Commands
`/mac create command <name> "command" "description"` - Create a new command mob  
`/mac create warp <warp>` - Create a new warp mob  
`/mac remove` - Remove a mob's action  
`/mac cancel` - Cancels the current operation  
`/mac warp <warp>` - Teleport to a warp  
`/mac warps` - List available warps  
`/mac warps set <name>` - Create a warp  
`/mac warps remove <name>` - Delete a warp  
`/mac reload` - Reloads the plugin's configuration  
`/mac` - Shows this message

### Permissions
`mobactions.*` - Grants access to all warps and all other permissions (Default: disabled)  
`mobactions.command.*` - Allows players to use all command mobs (Default: enabled)  
`mobactions.command.[command]` - Allows players to use specific command mobs (Default: disabled)  
`mobactions.warp` - Allows players to go to a warp using /mac warp. Also needed for /mac warps (Default: op)  
`mobactions.warp.*` - Allows players to use all mob portals (Default: enabled)  
`mobactions.warp.[warp]` - Allows players to use portals to the specific warp (Default: disabled)

`mobactions.admin.*` - Grants access to all admin commands and warps (Default: op)  
`mobactions.admin.create` - Allows players to create action mobs with /mac create (Default: op)  
`mobactions.admin.remove` - Allows players to destroy mob portals with /mac remove (Default: op)  
`mobactions.admin.reload` - Allows players to reload the plugin configuration using /mac reload (Default: op)  
`mobactions.admin.warps.set` - Allows players to create a warp using /mac setwarp (Default: op)  
`mobactions.admin.warps.remove` - Allows players to delete a warp using /mac delwarp (Default: op)

### Configuration
For now, enjoy a simple, empty config. :)

## Installation
Simply drop into your server's plugin folder, and we'll generate your config for you. Restart your server or run `/mac reload` to load any changes you make or messages you add!

## Building From Source
You can build from source by running the following command.

On macOS, Linux, or Unix:
```bash
./gradlew build
```

On Windows:
```batch
gradlew.bat build
```

# MobPortals

Welcome to MobPortals! A simple way to turn any mob into a portal players can click on to use!

## Simple and Stable
Works with Paper, Spigot, and Bukkit servers!

Native Version: **1.16**  
Tested Minecraft Versions: **1.16**

![Build and Artifact](https://github.com/CrimsonWarpedcraft/MobPortals/workflows/Build%20and%20Artifact/badge.svg?event=push)

## Features
For a simple plugin, we pack a few powerful features!

### Commands
`/mp create warp <name>` - Create a portal to the specified warp 
`/mp remove` - Remove a portal  
`/mp warp <warp>` - Teleport to a warp  
`/mp list` - Lists the available warps  
`/mp setwarp <name>` - Create a warp at the player's location  
`/mp delwarp <name>` - Delete a warp  
`/mp reload` - Reload the configuration

### Permissions
`mobportals.*` - Grants access to all warps and all other permissions (Default: disabled)  
`mobportals.command.*` - Allows players to use all command mobs, or specify a command mob name instead of * (Default: enabled)  
`mobportals.warp` - Allows players to go to a warp using /mp warp. Also needed for /mp list (Default: op)  
`mobportals.warp.*` - Allows players to use all mob portals (Default: enabled)  
`mobportals.warp.[warp]` - Allows players to use portals to the specific warp (Default: disabled)  
`mobportals.listwarps` - Allows players to list warps that they have access to (Default: op)
  
`mobportal.admin.*` - Grants access to all admin commands and warps (Default: op)  
`mobportals.admin.createcommand` - Allows players to create command mobs with /mp create command (Default: false)  
`mobportals.admin.createportal` - Allows players to create mob portals with /mp create (Default: false)  
`mobportals.admin.remove` - Allows players to destroy mob portals with /mp remove (Default: false)  
`mobportals.admin.setwarp` - Allows players to create a warp using /mp setwarp (Default: op)  
`mobportals.admin.delwarp` - Allows players to delete a warp using /mp delwarp (Default: op)  
`mobportals.admin.reload` - Allows players to reload the plugin configuration (Default: op)  

### Configuration
For now, enjoy a simple, empty config. :)

## Installation
Simply drop into your server's plugin folder, and we'll generate your config for you. Restart your server or run `/mp reload` to load any changes you make or messages you add!

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

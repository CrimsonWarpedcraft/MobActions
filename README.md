# MobPortals

Welcome to MobPortals! A simple way to turn any mob into a portal players can click on to use!

## Simple and Stable
Works with Paper, Spigot, and Bukkit servers!

Native Version: **1.16**  
Tested Minecraft Versions: **1.16**

## Features
For a simple plugin, we pack a few powerful features!

### Commands
`/mp create <warp>` - Create a portal to the specified warp  
`/mp remove` - Remove a portal  
`/mp setwarp <name>` - Create a warp at the player's location  
`/mp warp <warp>` - Teleport to a warp  
`/mp delwarp <name>` - Delete a warp  
`/mp reload` - Reload the configuration

### Permissions
`mobportals.use.*` - Allows players to use all mob portals (Default: enabled)  
`mobportals.use.[warp]` - Allows players to use portals to the specific warp (Default: disabled)  
`mobportals.create` - Allows players to create mob portals with /mp create (Default: op)  
`mobportals.remove` - Allows players to destroy mob portals with /mp remove (Default: op)  
`mobportals.setwarp` - Allows players to create a warp using /mp setwarp (Default: op)  
`mobportals.delwarp` - Allows players to delete a warp using /mp delwarp (Default: op)  
`mobportals.warp` - Allows players to go to a warp using /mp warp (Default: op)  
`mobportals.reload` - Allows players to reload the plugin configuration (Default: op)  
`mobportals.*` - Grants access to all portals and all other permissions (Default: disabled)

### Configuration
Please refer to the [config.yml](https://github.com/leviem1/MobPortals/blob/master/src/main/resources/config.yml) for further information.

## Installation
Simply drop into your server's plugin folder, and we'll generate your config for you. Restart your server or run `/mp reload` to load any changes you make or messages you add!

## Building From Source
You can build from source by running the following command.

```bash
./gradlew build
```

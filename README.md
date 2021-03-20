# MobActions

Welcome to MobActions! A simple way to turn any mob into actions players can click to use!

[![Build and Artifact](https://github.com/CrimsonWarpedcraft/MobActions/actions/workflows/artifact.yml/badge.svg)](https://github.com/CrimsonWarpedcraft/MobActions/actions/workflows/artifact.yml)

## Simple and Stable
Works with Paper, Spigot, and Bukkit servers!

Native Version: **1.16**  
Tested Minecraft Versions: **1.16**

## Features
For a simple plugin, we pack a few powerful features!
### Commands
`/mac action create command "command[;command 2]" "name tag text"` - Create a new command mob action    
`/mac action create consolecmd "command[;command 2]" "name tag text"` - Create a new console command mob action  
`/mac action create warp <name>` - Create a new warp mob action  
`/mac action create event <name>` - Create a new event mob action with a command or warp action  
`/mac action remove` - Remove a mob action  
`/mac action cancel` - Cancels the current action operation  
`/mac events create <event name> <wait seconds> [max players] command "command[;command 2]"` - Create a command 
event with an optional player limit  
`/mac events create <event name> <wait seconds> [max players] consolecmd "command[;command 2]"` - Create a 
console command event with an optional player limit   
`/mac events create <event name> <wait seconds> [max players] warp <warp name>` - Create a warp  
event with an optional player limit  
`/mac events open <name>` - Opens event, starts event timer  
`/mac events cancel <name>` - Cancel an event  
`/mac events remove <name>` - Remove an event from Mob with click  
`/mac events forcestart <name>` - Forces an event to start now  
`/mac events info <name>` - Show information about the event  
`/mac events` - List all events  
`/mac warp <warp>` - Teleport to a warp  
`/mac warps` - List available warps  
`/mac warps set <name>` - Create a warp  
`/mac warps remove <name>` - Delete a warp  
`/mac reload` - Reloads the plugin's configuration  
`/mac help [page]` - Shows the specified help page

### Permissions
`mobactions.*` - Grants access to all warps and all other permissions (Default: disabled)  
`mobactions.command` - Allows players to use all command mobs (Default: enabled)  
`mobactions.consolecmd` - Allows players to use all console command mobs (Default: enabled)  
`mobactions.event.*` - Allows players to join all events (Default: enabled)  
`mobactions.event.[event]` - Allows players to use specific command mobs (Default: disabled)  
`mobactions.warp` - Allows players to go to a warp using /mac warp. Also needed for /mac warps (Default: op)  
`mobactions.warp.*` - Allows players to use all mob portals (Default: enabled)  
`mobactions.warp.[warp]` - Allows players to use portals to the specific warp (Default: disabled)

`mobactions.admin.*` - Grants access to all admin commands and warps (Default: op)  
`mobactions.admin.action.create` - Allows players to create action mobs with /mac action create (Default: op)  
`mobactions.admin.action.remove` - Allows players to destroy mob portals with /mac action remove (Default: op)  
`mobactions.admin.events.create` - Allows players to create an event using /mac event create (Default: op)  
`mobactions.admin.events.remove` - Allows players to remove an event using /mac event remove (Default: op)  
`mobactions.admin.events.start` - Allows players to start an event using /mac events start (Default: op)  
`mobactions.admin.events.stop` - Allows players to stop an event using /mac events stop (Default: op)  
`mobactions.admin.warps.set` - Allows players to create a warp using /mac setwarp (Default: op)  
`mobactions.admin.warps.remove` - Allows players to delete a warp using /mac delwarp (Default: op)  
`mobactions.admin.reload` - Allows players to reload the plugin configuration using /mac reload (Default: op)  

### Configuration
For now, enjoy a simple, empty config. :)

## Installation
Simply drop into your server's plugin folder, and we'll generate your config for you. Restart your server or run 
`/mac reload` to load any changes you make or messages you add!

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

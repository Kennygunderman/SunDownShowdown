# SundownShowdown
Minecraft Spigot Plugin

Sundown Showdown is an arena-based mini-game that happens every Minecraft day at sundown. When the game starts chests in the specified arena are stocked with valuable loot that is guarded by high-level mobs!

## Set-Up
To set up the plugin simply add chests to your arena using the commands below, and make sure the plugin is enabled.

## Commands
#### /showdown enable
Enables the Showdown to reoccur at sundown.

#### /showdown disable
Disables the Showdown for happening at sundown.

#### /showdown start
Force starts the Showdown.

#### /showdown arena set radius <radius>
Sets the box radius of the arena from where the player is standing.

#### /showdown arena remove
Removes the current saved arena from the configuration.

#### /showdown arena clear
Clears hostile mobs from the arena.

#### /showdown chest add
Adds a chest to the arena. The chest added will be above the current block that the player is looking at.

#### /showdown chest remove
Removes the chest the player is looking at from the arena.

#### /showdown chest remove all
Removes all chests from the arena.

#### /showdown chest restock
Restocks all chests in the arena forcefully.

#### /showdown mob add <mob type>
Add's a mob spawn point for the type of mob that was specified. currently, the available types are `pillager` & `ravager`.

#### /showdown mob remove all
Remove's all defined mob spawn locations from the arena.

#### /showdown mob spawn points
Spawns all mobs at the defined points.

#### /showdown mob spawn chests
Spawns all mobs at the arena chests.

#### /showdown mob spawn all
Spawns all mobs at defined points and chests.
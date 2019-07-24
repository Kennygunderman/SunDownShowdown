# SundownShowdown
Minecraft Spigot Plugin

Sundown Showdown is an arena-based mini-game that happens every Minecraft day at sundown. When the game starts chests in the specified arena are stocked with valuable loot that is guarded by high-level mobs!

## Set up
To set up the arena make sure you are standing in the middle of the arena and set the radius using `\showdown arena set <radius>`. Once you have specified the arena radius, add chests within the arena by using `\showdown chest add`. By default, mobs will spawn around the chests when a new game starts. To add high-level mobs such as Ravager's and Pillager's use the command `/showdown mob add <mob type>`. Please reference the `Commands` section to understand how to properly use these commands. Enjoy the Plugin!

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
Restocks all chests in the arena.

#### /showdown mob add <mob type>
Adds a mob spawn point at the location that the player is looking at. Currently, the available mob types to spawn are `pillager` & `ravager`.

#### /showdown mob remove all
Removes all defined mob spawn locations from the arena.

#### /showdown mob spawn points
spawns all mobs at the defined points in the arena.

#### /showdown mob spawn chests
Spawns all mobs at the arena chests.

#### /showdown mob spawn all
Spawns all mobs at defined points and chests in the arena.


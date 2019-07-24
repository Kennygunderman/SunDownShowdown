package com.kgeezy.sundownshowdown

import org.bukkit.ChatColor

object StringRes {
    private const val PLUGIN_NAME = "Sundown Showdown"
    private val PREFIX = "${ChatColor.AQUA}[${ChatColor.GOLD}$PLUGIN_NAME${ChatColor.AQUA}] "

    val SHOWDOWN_WILL_BEGIN_SECONDS = PREFIX + ChatColor.LIGHT_PURPLE + "Showdown will begin in %1\$s seconds!"
    val SHOWDOWN_START = PREFIX + ChatColor.LIGHT_PURPLE + "Showdown has begun!"

    val SHOWDOWN_CHEST_ADDED = PREFIX + ChatColor.GREEN + "Chest successfully added!"
    val SHOWDOWN_FORCE_STARTED = PREFIX + ChatColor.GREEN + "You force started the Showdown!"
    val SHOWDOWN_CHESTS_RESTOCKED = PREFIX + ChatColor.GREEN + "Showdown chests have been restocked!"
    val SHOWDOWN_ENABLE = PREFIX + ChatColor.GREEN + "You have " + ChatColor.BOLD + "ENABLED " + ChatColor.RESET + ChatColor.GREEN + "Sundown Showdown!"
    val SHOWDOWN_DISABLE = PREFIX + ChatColor.GREEN + "You have " + ChatColor.BOLD + "DISABLED " + ChatColor.RESET + ChatColor.GREEN + "Sundown Showdown!"
    val SHOWDOWN_CHEST_REMOVE = PREFIX + ChatColor.GREEN + "Chest successfully removed!"
    val SHOWDOWN_CHEST_REMOVE_ALL = PREFIX + ChatColor.GREEN + "All chests have been removed from the Showdown!"
    val SHOWDOWN_ARENA_CREATED = PREFIX + ChatColor.GREEN + "Arena radius set successfully!"
    val SHOWDOWN_ARENA_REMOVED = PREFIX + ChatColor.GREEN + "Arena removed!"
    val SHOWDOWN_ARENA_CLEARED = PREFIX + ChatColor.GREEN + "%1\$s mobs cleared from the arena!"
    val SHOWDOWN_MOB_SPAWNS_REMOVED = PREFIX + ChatColor.GREEN + "All mob spawns removed!"
    val SHOWDOWN_MOB_SPAWNED = PREFIX + ChatColor.GREEN + "Showdown mobs spawned at the spawn points!"
    val SHOWDOWN_MOB_SPAWNED_CHESTS = PREFIX + ChatColor.GREEN + "Mobs have been spawned at the Showdown chests!"
    val SHOWDOWN_MOB_SPAWNED_ALL = PREFIX + ChatColor.GREEN + "All Showdown mobs have been spawned!"

    val CANT_PLACE_CHEST_IN_AIR = PREFIX + ChatColor.RED + "You cannot place a chest in mid air!"
    val SHOWDOWN_CHEST_WORLD_ERROR = PREFIX + ChatColor.RED + "Showdown chests can only be placed in the main world!"
    val SHOWDOWN_CHEST_NOT_FOUND = PREFIX + ChatColor.RED + "Unable to find a chest at this location!"
    val SHOWDOWN_CANT_ADD_MOB_TYPE = PREFIX + ChatColor.RED + "You can't add a spawn point for this kind of mob!"
    val SHOWDOWN_UNABLE_TO_ADD_MOB = PREFIX + ChatColor.RED + "Uh oh, something went wrong! Unable to add a spawn for this mob!"

    val SHOWDOWN_CMD_USAGE = ChatColor.RED.toString() + "Usages: /showdown <chest | arena | start | enable | disable>"
    val SHOWDOWN_CHEST_CMD_USAGE = ChatColor.RED.toString() + "Usages: /showdown chest <add | restock | remove | remove all>"
    val SHOWDOWN_ARENA_USAGE = ChatColor.RED.toString() + "Usages: /showdown arena <set | clear | remove>"
    val SHOWDOWN_ARENA_SET_USAGE = ChatColor.RED.toString() + "Usages: /showdown arena set radius <radius>"
    val SHOWDOWN_MOB_REMOVE_USAGE = ChatColor.RED.toString() + "Usages: /showdown mob remove all"
    val SHOWDOWN_MOB_SPAWN_USAGE = ChatColor.RED.toString() + "Usages: /showdown mob spawn <all | chest>"
    val SHOWDOWN_MOB_USAGE = ChatColor.RED.toString() + "Usages: /showdown mob <add | spawn | remove all>"
}
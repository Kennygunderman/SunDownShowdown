package com.kgeezy.sundownshowdown

import org.bukkit.ChatColor

object StringRes {
    private const val PLUGIN_NAME = "SunDown Showdown"
    private val PREFIX = "${ChatColor.AQUA}[${ChatColor.GOLD}$PLUGIN_NAME${ChatColor.AQUA}] "

    val SHOWDOWN_WILL_BEGIN_SECONDS = PREFIX + ChatColor.LIGHT_PURPLE + "Showdown will begin in %1\$s seconds!"
    val SHOWDOWN_START = PREFIX + ChatColor.LIGHT_PURPLE + "Showdown has begun!"

    val SHOWDOWN_CHEST_ADDED = PREFIX + ChatColor.GREEN + "Chest successfully added!"
    val SHOWDOWN_FORCE_STARTED = PREFIX + ChatColor.GREEN + "You force started the Showdown!"
    val SHOWDOWN_CHESTS_RESTOCKED = PREFIX + ChatColor.GREEN + "Showdown chests have been restocked!"
    val SHOWDOWN_ENABLE = PREFIX + ChatColor.GREEN + "You have " + ChatColor.BOLD + "ENABLED " + ChatColor.RESET + ChatColor.GREEN + "SunDown Showdown!"
    val SHOWDOWN_DISABLE = PREFIX + ChatColor.GREEN + "You have " + ChatColor.BOLD + "DISABLED " + ChatColor.RESET + ChatColor.GREEN + "SunDown Showdown!"

    val CANT_PLACE_CHEST_IN_AIR = PREFIX + ChatColor.RED + "You cannot place a chest in mid air!"
    val SHOWDOWN_CHEST_CMD_USAGE = PREFIX + ChatColor.RED + "Usages: /showdown chest <add | restock | remove | remove all>"
    val SHOWDOWN_CMD_USAGE = PREFIX + ChatColor.RED + "Usages: /showdown <chest | start | enable | disable>"
}
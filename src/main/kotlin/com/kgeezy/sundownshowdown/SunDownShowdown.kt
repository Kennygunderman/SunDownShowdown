package com.kgeezy.sundownshowdown

import com.kgeezy.sundownshowdown.Command.*
import com.kgeezy.sundownshowdown.chest.ChestGenerator
import com.kgeezy.sundownshowdown.chest.ItemGenerator
import com.kgeezy.sundownshowdown.game.Arena
import com.kgeezy.sundownshowdown.game.Showdown
import com.kgeezy.sundownshowdown.mob.MobSpawner
import com.kgeezy.sundownshowdown.scheduler.ShowdownScheduler
import com.kgeezy.sundownshowdown.util.CommandHelper
import com.kgeezy.sundownshowdown.util.FileManager
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

const val DEFAULT_WORLD = "world"

class SundownShowdown : JavaPlugin() {
    private val defaultWorld by lazy {
       server.getWorld(DEFAULT_WORLD)
    }

    private val arena by lazy {
        Arena(defaultWorld, FileManager.getInstance())
    }

    private val chestGenerator by lazy {
        ChestGenerator(ItemGenerator(), FileManager.getInstance(), defaultWorld)
    }

    private val mobSpawner by lazy {
        MobSpawner(defaultWorld, FileManager.getInstance())
    }

    private val showdown: Showdown by lazy {
        val scheduler = ShowdownScheduler(this)
        Showdown(server, arena, chestGenerator, mobSpawner, scheduler)
    }

    override fun onEnable() {
        super.onEnable()
        /**
         * Initialize the File Manager for the plugin
         */
        FileManager.initialize(dataFolder)

        /**
         * Enable the showdown
         */
        showdown.enable()
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player && command.name != MAIN) {
            return true
        }

        CommandHelper.command(args) { cmd, usage, param ->
            when (cmd) {
                CHEST_ADD -> {
                    if ((sender as Player).world != server.getWorld(DEFAULT_WORLD)) {
                        sender.sendMessage(StringRes.SHOWDOWN_CHEST_WORLD_ERROR)
                        return@command
                    }

                    if (chestGenerator.createChestAboveBlock(sender, sender.getTargetBlock(null, 200))) {
                        sender.sendMessage(StringRes.SHOWDOWN_CHEST_ADDED)
                    }
                }

                CHEST_RESTOCK -> {
                    showdown.chestGenerator.restockChests()
                    sender.sendMessage(StringRes.SHOWDOWN_CHESTS_RESTOCKED)
                }

                CHEST_REMOVE -> {
                    if (chestGenerator.removeChest((sender as Player).getTargetBlock(null, 200).location)) {
                        sender.sendMessage(StringRes.SHOWDOWN_CHEST_REMOVE)
                    } else {
                        sender.sendMessage(StringRes.SHOWDOWN_CHEST_NOT_FOUND)
                    }
                }

                CHEST_REMOVE_ALL -> {
                    chestGenerator.removeAll()
                    sender.sendMessage(StringRes.SHOWDOWN_CHEST_REMOVE_ALL)
                }

                START -> {
                    showdown.startGame()
                    sender.sendMessage(StringRes.SHOWDOWN_FORCE_STARTED)
                }

                ENABLE -> {
                    showdown.enable()
                    sender.sendMessage(StringRes.SHOWDOWN_ENABLE)
                }

                DISABLE -> {
                    showdown.disable()
                    sender.sendMessage(StringRes.SHOWDOWN_DISABLE)
                }

                ARENA_SET -> {
                    param?.toDoubleOrNull()?.let { radius ->
                        arena.setArena((sender as Player).location, radius)
                        sender.sendMessage(StringRes.SHOWDOWN_ARENA_CREATED)
                        return@command
                    }
                    sender.sendMessage(StringRes.SHOWDOWN_ARENA_USAGE)
                }

                ARENA_REMOVE -> {
                    arena.removeArena()
                    sender.sendMessage(StringRes.SHOWDOWN_ARENA_REMOVED)
                }

                ARENA_CLEAR -> {
                    sender.sendMessage(String.format(StringRes.SHOWDOWN_ARENA_CLEARED, showdown.clearMobs()))
                }

                MOB_ADD -> {
                    param?.let { p ->
                        if (mobSpawner.availableMobs.contains(p)) {
                            val loc = (sender as Player).getTargetBlock(null, 200).location.apply { y++ }
                            if (!mobSpawner.saveMob(loc, p)) {
                                sender.sendMessage(StringRes.SHOWDOWN_UNABLE_TO_ADD_MOB)
                            }
                        } else {
                            sender.sendMessage(StringRes.SHOWDOWN_CANT_ADD_MOB_TYPE)
                        }
                    }
                }

                MOB_REMOVE_ALL -> {
                    showdown.mobSpawner.removeAllMobSpawns()
                    sender.sendMessage(StringRes.SHOWDOWN_MOB_SPAWNS_REMOVED)
                }

                MOB_SPAWN -> {
                    showdown.mobSpawner.spawnMobsFromConfig()
                    sender.sendMessage(StringRes.SHOWDOWN_MOB_SPAWNED)
                }

                MOB_SPAWN_CHESTS -> {
                    showdown.spawnMobsAtChests()
                    sender.sendMessage(StringRes.SHOWDOWN_MOB_SPAWNED_CHESTS)
                }

                MOB_SPAWN_ALL -> {
                    showdown.spawnAllMobs()
                    sender.sendMessage(StringRes.SHOWDOWN_MOB_SPAWNED_ALL)
                }

                else -> usage?.let {
                    sender.sendMessage(it)
                }
            }
        }
        return true
    }
}
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
    private val showdown: Showdown by lazy {
        val defaultWorld = server.getWorld(DEFAULT_WORLD)
        val chestGenerator = ChestGenerator(ItemGenerator(), FileManager.getInstance(), defaultWorld)
        val mobSpawner = MobSpawner(defaultWorld)
        val scheduler = ShowdownScheduler(this)
        Showdown(server, scheduler, chestGenerator, mobSpawner)
    }


    private val arena by lazy {
        //todo: dont double bang
        Arena(server.getWorld(DEFAULT_WORLD)!!, FileManager.getInstance())
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

                    if (showdown.chestGenerator.createChestAboveBlock(sender, sender.getTargetBlock(null, 200))) {
                        sender.sendMessage(StringRes.SHOWDOWN_CHEST_ADDED)
                    }
                }

                CHEST_RESTOCK -> {
                    showdown.chestGenerator.restockChests()
                    sender.sendMessage(StringRes.SHOWDOWN_CHESTS_RESTOCKED)
                }

                CHEST_REMOVE -> {
                    if (showdown.chestGenerator.removeChest((sender as Player).getTargetBlock(null, 200).location)) {
                        sender.sendMessage(StringRes.SHOWDOWN_CHEST_REMOVE)
                    } else {
                        sender.sendMessage(StringRes.SHOWDOWN_CHEST_NOT_FOUND)
                    }
                }

                CHEST_REMOVE_ALL -> {
                    showdown.chestGenerator.removeAll()
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
                    sender.sendMessage(String.format(StringRes.SHOWDOWN_ARENA_CLEARED, arena.clearMobs()))
                }

                else -> usage?.let {
                    sender.sendMessage(it)
                }
            }
        }
        return true
    }
}
package com.kgeezy.sundownshowdown

import com.kgeezy.sundownshowdown.chest.ChestGenerator
import com.kgeezy.sundownshowdown.chest.ItemGenerator
import com.kgeezy.sundownshowdown.game.Showdown
import com.kgeezy.sundownshowdown.mob.MobSpawner
import com.kgeezy.sundownshowdown.scheduler.ShowdownScheduler
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

    override fun onEnable() {
        super.onEnable()
        /**
         * Initialize the File Manager for the plugin
         */
        FileManager.initialize(dataFolder)

        /**
         * start the showdown
         */
        showdown.enable()
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player && command.name != MAIN) {
            return true
        }

        when (args.firstOrNull()) {
            CHEST_ARG -> {
                when (args.getOrNull(1)) {
                    CHEST_ADD_ARG -> {
                        if ((sender as Player).world != server.getWorld(DEFAULT_WORLD)) {
                            sender.sendMessage(StringRes.SHOWDOWN_CHEST_WORLD_ERROR)
                            return true
                        }

                        if (showdown.chestGenerator.createChestAboveBlock(sender, sender.getTargetBlock(null, 200))) {
                            sender.sendMessage(StringRes.SHOWDOWN_CHEST_ADDED)
                        }
                    }

                    CHEST_RESTOCK_ARG -> {
                        showdown.chestGenerator.restockChests()
                        sender.sendMessage(StringRes.SHOWDOWN_CHESTS_RESTOCKED)
                    }

                    CHEST_REMOVE_ARG -> {
                        if (args.getOrNull(2) == CHEST_REMOVE_ALL_ARG) {
                            showdown.chestGenerator.removeAll()
                            sender.sendMessage(StringRes.SHOWDOWN_CHEST_REMOVE_ALL)
                        } else {
                            if (showdown.chestGenerator.removeChest((sender as Player).getTargetBlock(null, 200).location)) {
                                sender.sendMessage(StringRes.SHOWDOWN_CHEST_REMOVE)
                            } else {
                                sender.sendMessage(StringRes.SHOWDOWN_CHEST_NOT_FOUND)
                            }
                        }
                    }

                    else -> sender.sendMessage(StringRes.SHOWDOWN_CHEST_CMD_USAGE)
                }
            }

            START_ARG -> {
                showdown.startGame()
                sender.sendMessage(StringRes.SHOWDOWN_FORCE_STARTED)
            }

            ENABLE_ARG -> {
                showdown.enable()
                sender.sendMessage(StringRes.SHOWDOWN_ENABLE)
            }

            DISABLE_ARG -> {
                showdown.disable()
                sender.sendMessage(StringRes.SHOWDOWN_DISABLE)
            }
            
            else -> sender.sendMessage(StringRes.SHOWDOWN_CMD_USAGE)
        }
        return true
    }
}
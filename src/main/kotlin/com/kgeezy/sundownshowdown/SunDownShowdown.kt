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

class SunDownShowdown : JavaPlugin() {
    private val chestGenerator: ChestGenerator by lazy {
        val itemGenerator = ItemGenerator()
        ChestGenerator(itemGenerator, FileManager.getInstance())
    }

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
         * Start the showdown
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
                        chestGenerator.world = (sender as Player).world
                        if (chestGenerator.createChestAboveBlock(sender, sender.getTargetBlock(null, 200))) {
                            sender.sendMessage(StringRes.SHOWDOWN_CHEST_ADDED)
                        }
                    }

                    CHEST_RESTOCK_ARG -> {
                        showdown.chestGenerator.restockChests()
                        sender.sendMessage(StringRes.SHOWDOWN_CHESTS_RESTOCKED)
                    }

                    null -> sender.sendMessage(StringRes.SHOWDOWN_CHEST_CMD_USAGE)
                }
            }

            START_ARG -> {
                showdown.startGame()
                sender.sendMessage(StringRes.SHOWDOWN_FORCE_STARTED)
            }
            
            null -> sender.sendMessage(StringRes.SHOWDOWN_CMD_USAGE)
        }
        return true
    }
}
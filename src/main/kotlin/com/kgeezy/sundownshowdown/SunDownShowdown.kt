package com.kgeezy.sundownshowdown

import com.kgeezy.sundownshowdown.chest.ChestGenerator
import com.kgeezy.sundownshowdown.chest.ItemGenerator
import com.kgeezy.sundownshowdown.scheduler.ShowDownScheduler
import com.kgeezy.sundownshowdown.util.FileManager
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class SunDownShowdown : JavaPlugin() {

    private val chestGenerator: ChestGenerator by lazy {
        val itemGenerator = ItemGenerator()
        ChestGenerator(itemGenerator, FileManager.getInstance())
    }

    private var showDownScheduler: ShowDownScheduler? = null

    override fun onEnable() {
        super.onEnable()

        /**
         * Initialize the File Manager for the plugin
         */
        FileManager.initialize(dataFolder)

        /**
         * Init the ShowDownScheduler & start the task
         */
        showDownScheduler = ShowDownScheduler(this, chestGenerator).apply {
            scheduleMainTask()
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            return true
        }

        when (command.name) {
            CHEST -> {
                chestGenerator.world = sender.world
                chestGenerator.createChestAboveBlock(sender, sender.getTargetBlock(null, 200))
            }

            RESTOCK -> {
                chestGenerator.world = sender.world
                chestGenerator.restockChests()
            }
        }

        return false
    }
}
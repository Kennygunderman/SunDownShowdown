package com.kgeezy.sundownshowdown

import com.kgeezy.sundownshowdown.chest.ChestGenerator
import com.kgeezy.sundownshowdown.chest.ItemGenerator
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class SunDownShowdown: JavaPlugin() {

    private val itemGenerator = ItemGenerator()
    private val chestGenerator = ChestGenerator(itemGenerator)

    override fun onEnable() {
        super.onEnable()
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
        }

        return false
    }
}
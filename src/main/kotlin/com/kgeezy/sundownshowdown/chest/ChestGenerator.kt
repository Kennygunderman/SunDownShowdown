package com.kgeezy.sundownshowdown.chest

import com.kgeezy.sundownshowdown.StringRes
import com.kgeezy.sundownshowdown.util.ChestLocationFile
import com.kgeezy.sundownshowdown.util.int
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.block.Chest
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * Spawning Chests with loot
 *
 */

private const val CHEST_SIZE = 26

class ChestGenerator(private val itemGenerator: ItemGenerator, private val fileManager: ChestLocationFile, var world: World? = null) {

    private val rng by lazy {
        Random()
    }

    /**
     * creates a chest above the block that the Player is looking at, as long as the
     * block isn't Air.
     *
     */
    fun createChestAboveBlock(player: Player, block: Block): Boolean {
        if (block.type == Material.AIR) {
            player.sendMessage(StringRes.CANT_PLACE_CHEST_IN_AIR)
            return false
        }

        world?.let { w ->
            val chestBlock = w.getBlockAt(block.location.apply { y++ })
            createChestAtBlock(chestBlock)
            saveChestLocation(chestBlock.location)
        }

        return true
    }

    private fun createChestAtBlock(block: Block) {
        block.type = Material.CHEST

        val chest = block.state as Chest
        generateChestContents { chestIndex, item ->
            chest.inventory.setItem(chestIndex, item)
        }
    }

    private fun generateChestContents(callback: (chestIndex: Int, item: ItemStack) -> Unit) {
        val rolls = rng.int(2, 5)

        for (i in 0 until rolls) {
            val index = rng.nextInt(CHEST_SIZE)
            callback(index, itemGenerator.generateRandomItem())
        }
    }

    private fun saveChestLocation(location: Location) {
        val yml = fileManager.getChestLocationYml()
        val config = fileManager.configFromYml(yml)

        val size = config
            .getConfigurationSection("${world?.name}")
            ?.getConfigurationSection("chests")
            ?.getKeys(false)
            ?.size ?: 0

        world?.name.let { worldName ->
            config.set("$worldName.chests.$size.x", location.x)
            config.set("$worldName.chests.$size.y", location.y)
            config.set("$worldName.chests.$size.z", location.z)
            config.save(yml)
        }
    }

    fun getChestLocations(): List<Location> {
        val locations = mutableListOf<Location>()

        val config = fileManager.configFromYml(fileManager.getChestLocationYml())
        val chestSection = config
            .getConfigurationSection("${world?.name}")
            ?.getConfigurationSection("chests")

        chestSection?.getKeys(false)?.forEach { chestIndex ->
            val x = chestSection.getConfigurationSection(chestIndex)?.get("x") as? Double
            val y = chestSection.getConfigurationSection(chestIndex)?.get("y") as? Double
            val z = chestSection.getConfigurationSection(chestIndex)?.get("z") as? Double

            if (x != null && y != null && z != null) {
                val location = Location(world, x,y,z)
                locations.add(location)
            }
        }

        return locations
    }

    fun restockChests() {
        getChestLocations().forEach { chestLocation ->
            world?.getBlockAt(chestLocation)?.let { block ->
                (block.state as? Chest)?.inventory?.clear()
                createChestAtBlock(block)
            }
        }
    }

    /**
     * Validate chests still exist in the world
     */
    fun validateChests() {
        /**
         * TODO: Add this logic
         */
    }

    /**
     * Removes a chest
     */
    fun removeChest() {
        /**
         * TODO: Add this logic
         */
    }
}

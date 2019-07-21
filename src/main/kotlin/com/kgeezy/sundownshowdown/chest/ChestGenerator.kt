package com.kgeezy.sundownshowdown.chest

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
class ChestGenerator constructor(private val itemGenerator: ItemGenerator) {

    private val rng by lazy {
        Random()
    }

    var world: World? = null

    /**
     * creates a chest above the block that the Player is looking at, as long as the
     * block isn't Air.
     *
     */
    fun createChestAboveBlock(player: Player, block: Block) {
        if (block.type == Material.AIR) {
            player.sendMessage("You cannot create a chest here, this is Air!")
        }

        world?.let { w ->
            val chestBlock = w.getBlockAt(block.location.apply { y++ })
            chestBlock.type = Material.CHEST

            val chest = chestBlock.state as Chest
            generateChestContents { chestIndex, item ->
                chest.inventory.setItem(chestIndex, item)
            }
        }
    }

    private fun generateChestContents(callback: (chestIndex: Int, item: ItemStack) -> Unit) {
        val rolls = rng.nextInt(5) + 2 // 2-5 rolls per chest
        for (i in 0 until rolls) {
            callback(rng.nextInt(CHEST_SIZE), itemGenerator.generateRandomItem())
        }
    }
}

package com.kgeezy.sundownshowdown.chest

import com.kgeezy.sundownshowdown.util.int
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import java.util.*

enum class ItemChance {
    LOW,
    MED,
    HIGH,
    GOD
}

class ItemGenerator {
    private val rng: Random by lazy {
        Random()
    }

    val lowTierItems = listOf(

        /**
         * Foood
         */
        ItemStack(Material.COOKED_PORKCHOP, rng.nextInt(5) + 1),
        ItemStack(Material.COOKED_SALMON, rng.nextInt(5) + 1),
        ItemStack(Material.COOKED_CHICKEN, rng.nextInt(5) + 1),

        /**
         * Weapons
         */
        ItemStack(Material.STICK, 1),
        ItemStack(Material.WOODEN_SWORD, 1),
        ItemStack(Material.STONE_SWORD, 1)
    )

    val mediumTierItems = listOf(
        /**
         * Armor
         */
        ItemStack(Material.IRON_HELMET, 1),
        ItemStack(Material.IRON_CHESTPLATE, 1),
        ItemStack(Material.IRON_LEGGINGS, 1),
        ItemStack(Material.IRON_BOOTS, 1),

        /**
         * Items
         */
        ItemStack(Material.IRON_INGOT, rng.nextInt(4))
    )

    val highTierItems = listOf(
        /**
         * Armor
         */
        ItemStack(Material.DIAMOND_HELMET, 1),
        ItemStack(Material.DIAMOND_CHESTPLATE, 1),
        ItemStack(Material.DIAMOND_LEGGINGS, 1),
        ItemStack(Material.DIAMOND_BOOTS, 1),

        /**
         * Items
         */
        ItemStack(Material.DIAMOND, 1),


        /**
         * Food
         */
        ItemStack(Material.GOLDEN_APPLE, 1)
    )


    private val godItems = listOf(
        /**
         * Weapons
         */
        ItemStack(Material.DIAMOND_SWORD, 1).apply {
            itemMeta?.let { stackMeta ->
                stackMeta.addEnchant(Enchantment.DAMAGE_ALL, rng.int(2, 3), false)
                setItemMeta(stackMeta)
            }
        }
    )


    fun generateRandomItem(): ItemStack {
        return when (getItemChance()) {
            ItemChance.GOD -> godItems[rng.nextInt(godItems.size)]
            ItemChance.HIGH -> highTierItems[rng.nextInt(highTierItems.size)]
            ItemChance.MED -> mediumTierItems[rng.nextInt(mediumTierItems.size)]
            ItemChance.LOW -> lowTierItems[rng.nextInt(lowTierItems.size)]
        }
    }

    private fun getItemChance(): ItemChance {
        return when (rng.int(1, 20)) {
            1 -> ItemChance.GOD
            2,3 -> ItemChance.HIGH
            4,5,6,7 -> ItemChance.MED
            else -> ItemChance.LOW
        }
    }
}
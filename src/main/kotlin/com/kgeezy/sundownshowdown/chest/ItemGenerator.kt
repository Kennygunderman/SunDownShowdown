package com.kgeezy.sundownshowdown.chest

import com.kgeezy.sundownshowdown.util.int
import com.kgeezy.sundownshowdown.util.withEnchatment
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment.*
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
        ItemStack(Material.COOKED_PORKCHOP, rng.int(1, 5)).withEnchatment(ARROW_FIRE, 3, "Spicy Pork"),
        ItemStack(Material.COOKED_SALMON, rng.int(1, 5)).withEnchatment(THORNS, 3, "DON'T EAT THIS"),
        ItemStack(Material.COOKED_CHICKEN, rng.int(1, 5)),
        ItemStack(Material.COOKED_BEEF, rng.int(1, 5)),

        /**
         * Weapons
         */
        ItemStack(Material.STICK, 1),
        ItemStack(Material.WOODEN_SWORD, 1),
        ItemStack(Material.STONE_SWORD, 1),
        ItemStack(Material.FISHING_ROD, 1),
        ItemStack(Material.IRON_HOE, 1),
        ItemStack(Material.ARROW, rng.int(4,12)),


        /**
         * Items
         */
        ItemStack(Material.IRON_INGOT, 1),
        ItemStack(Material.COAL, 1),
        ItemStack(Material.EMERALD, 1),
        ItemStack(Material.EMERALD, 2),
        ItemStack(Material.GOLD_INGOT, 1),
        ItemStack(Material.COMPASS, 1),
        ItemStack(Material.PAPER, 16)
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
         * Weapons
         */
        ItemStack(Material.IRON_SWORD, 1),
        ItemStack(Material.IRON_AXE, 1),
        ItemStack(Material.IRON_SHOVEL, 1),

        /**
         * Items
         */
        ItemStack(Material.IRON_INGOT, rng.int(1,4)),
        ItemStack(Material.COAL, rng.int(6,24)),
        ItemStack(Material.IRON_BLOCK, 1),
        ItemStack(Material.ENDER_PEARL, 1),


        ItemStack(Material.ARROW, 32)
    )

    val highTierItems = listOf(
        /**
         * Armor (diamond)
         */
        ItemStack(Material.DIAMOND_HELMET, 1),
        ItemStack(Material.DIAMOND_CHESTPLATE, 1),
        ItemStack(Material.DIAMOND_LEGGINGS, 1),
        ItemStack(Material.DIAMOND_BOOTS, 1),

        /**
         * Armor (iron)
         */
        ItemStack(Material.IRON_CHESTPLATE, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(1,3)),
        ItemStack(Material.IRON_LEGGINGS, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(1,3)),
        ItemStack(Material.IRON_BOOTS, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(1,3)),
        ItemStack(Material.IRON_HELMET, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(1,3)),

        /**
         * Items
         */
        ItemStack(Material.DIAMOND, 1),
        ItemStack(Material.DIAMOND, 2),
        ItemStack(Material.DIAMOND, 3),
        ItemStack(Material.IRON_INGOT, 8),
        ItemStack(Material.IRON_INGOT, 16),
        ItemStack(Material.COAL, rng.int(24, 36)),

        /**
         * Books
         */
        ItemStack(Material.ENCHANTED_BOOK, 1).withEnchatment(DAMAGE_ARTHROPODS, rng.int(1,  5)),
        ItemStack(Material.ENCHANTED_BOOK, 1).withEnchatment(DIG_SPEED, rng.int(1,3)),
        ItemStack(Material.ENCHANTED_BOOK, 1).withEnchatment(LOOT_BONUS_BLOCKS, 1),
        ItemStack(Material.ENCHANTED_BOOK, 1).withEnchatment(KNOCKBACK, 2),


        /**
         * Food
         */
        ItemStack(Material.GOLDEN_APPLE, rng.int(1, 5)),
        ItemStack(Material.APPLE, rng.int(16, 32))
    )


    private val godItems = listOf(
        /**
         * Weapons
         */
        ItemStack(Material.DIAMOND_SWORD, 1).withEnchatment(DAMAGE_ALL, rng.int(2,3)),
        ItemStack(Material.DIAMOND_SWORD, 1).withEnchatment(DAMAGE_ALL, 5),
        ItemStack(Material.IRON_SWORD, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(3,4)),
        ItemStack(Material.IRON_AXE, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(3,4)),
        ItemStack(Material.BOW, 1).withEnchatment(ARROW_INFINITE, 10),
        ItemStack(Material.CROSSBOW, 1).withEnchatment(MULTISHOT, 1),
        ItemStack(Material.TRIDENT, 1).withEnchatment(CHANNELING, 1),
        ItemStack(Material.GOLDEN_SWORD, 1).withEnchatment(DAMAGE_ALL, rng.int(6, 12), "PISS BOOTS"),
        ItemStack(Material.WOODEN_SWORD, 1).withEnchatment(DAMAGE_ALL, rng.int(8, 10), "PISS BOOTS"),
        ItemStack(Material.STICK, 1).withEnchatment(KNOCKBACK, 5, "PISS BOOTS"),
        ItemStack(Material.TRIDENT, 1).withEnchatment(RIPTIDE, 1),


        /**
         * Armor (iron)
         */
        ItemStack(Material.IRON_CHESTPLATE, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(3,4)),
        ItemStack(Material.IRON_LEGGINGS, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(3,4)),
        ItemStack(Material.IRON_BOOTS, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(3,4)),
        ItemStack(Material.IRON_HELMET, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(3,4)),
        ItemStack(Material.IRON_BOOTS, 1).withEnchatment(PROTECTION_FALL, rng.int(1,4)),
        ItemStack(Material.IRON_HELMET, 1).withEnchatment(OXYGEN, rng.int(1,3)),

        /**
         * Armor (diamond)
         */
        ItemStack(Material.DIAMOND_CHESTPLATE, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(1,4)),
        ItemStack(Material.DIAMOND_LEGGINGS, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(1,4)),
        ItemStack(Material.DIAMOND_BOOTS, 1).withEnchatment(PROTECTION_FALL, rng.int(1,4)),
        ItemStack(Material.DIAMOND_HELMET, 1).withEnchatment(OXYGEN, rng.int(1,3)),
        ItemStack(Material.DIAMOND_BOOTS, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(1,4)),
        ItemStack(Material.DIAMOND_HELMET, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(1,3)),

        /**
         * Armor gold
         */
        ItemStack(Material.GOLDEN_CHESTPLATE, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(6, 10), "King Tut's CHEST PIECE!"),
        ItemStack(Material.GOLDEN_LEGGINGS, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(6, 10), "King Tut's LEGS!"),
        ItemStack(Material.DIAMOND_HELMET, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(8, 10), "GOLDEN CROWN"),
        ItemStack(Material.GOLDEN_BOOTS, 1).withEnchatment(PROTECTION_ENVIRONMENTAL, rng.int(8, 10), "PISS BOOTS"),

        /**
         * Food
         */
        ItemStack(Material.ENCHANTED_GOLDEN_APPLE, rng.int(1, 5)),

        /**
         * Items
         */
        ItemStack(Material.DIAMOND, 1).withEnchatment(LOOT_BONUS_MOBS, 1, "LUCKY DIAMOND!"),
        ItemStack(Material.DIAMOND, rng.int(2,4)),
        ItemStack(Material.IRON_INGOT, rng.int(16,24)),
        ItemStack(Material.GOLD_INGOT, rng.int(16,24)),
        ItemStack(Material.DIAMOND_BLOCK, 1),


        /**
         * Enchantment books
         */
        ItemStack(Material.ENCHANTED_BOOK, 1).withEnchatment(MULTISHOT, 1),
        ItemStack(Material.ENCHANTED_BOOK, 1).withEnchatment(ARROW_INFINITE, 1),
        ItemStack(Material.ENCHANTED_BOOK, 1).withEnchatment(KNOCKBACK, 1),
        ItemStack(Material.ENCHANTED_BOOK, 1).withEnchatment(LOOT_BONUS_MOBS, rng.int(2,3)),
        ItemStack(Material.ENCHANTED_BOOK, 1).withEnchatment(LOOT_BONUS_BLOCKS, rng.int(2,3)),
        ItemStack(Material.ENCHANTED_BOOK, 1).withEnchatment(QUICK_CHARGE, rng.int(2,3)),
        ItemStack(Material.ENCHANTED_BOOK, 1).withEnchatment(SILK_TOUCH, 1),
        ItemStack(Material.ENCHANTED_BOOK, 1).withEnchatment(CHANNELING, 1)
    )


    fun generateRandomItem(): ItemStack {
        return when (getItemChance()) {
            ItemChance.GOD -> godItems[rng.nextInt(godItems.size)]
            ItemChance.HIGH -> highTierItems[rng.nextInt(highTierItems.size)]
            ItemChance.MED -> mediumTierItems[rng.nextInt(mediumTierItems.size)]
            ItemChance.LOW -> lowTierItems[rng.nextInt(lowTierItems.size)]
        }
    }

    /**
     * 5% of god weapon
     * 20% of high tier
     * 30% of med tier
     * 45% of low tier
     */
    private fun getItemChance(): ItemChance {
        return when (rng.int(1, 20)) {
            1 -> ItemChance.GOD
            2,3,4,5 -> ItemChance.HIGH
            8,9,10,11,12 -> ItemChance.MED
            else -> ItemChance.LOW
        }
    }
}
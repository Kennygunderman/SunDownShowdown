package com.kgeezy.sundownshowdown.chest

import com.kgeezy.sundownshowdown.util.ext.int
import com.kgeezy.sundownshowdown.util.ext.withEnchantment
import com.kgeezy.sundownshowdown.util.ext.withName
import com.kgeezy.sundownshowdown.util.ext.withRandomEnchantment
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.enchantments.Enchantment.*
import org.bukkit.inventory.ItemStack
import java.util.*

enum class ItemChance {
    LOW,
    MED,
    HIGH
}

class ItemGenerator {
    private val rng: Random by lazy {
        Random()
    }

    val lowTierItems = listOf(
        ItemStack(Material.COOKED_BEEF, rng.int(2, 6)),
        ItemStack(Material.COOKED_PORKCHOP, rng.int(2, 6))
            .withEnchantment(Enchantment.ARROW_FIRE, 3)
            .withName("Spicy Pork"),

        ItemStack(Material.COOKED_CHICKEN, rng.int(2, 6)),
        ItemStack(Material.COOKED_SALMON, rng.int(2, 6)),
        ItemStack(Material.APPLE, rng.int(2, 6)),
        ItemStack(Material.BREAD, rng.int(2, 6)),

        ItemStack(Material.LEATHER_HELMET, 1),
        ItemStack(Material.LEATHER_CHESTPLATE, 1),
        ItemStack(Material.LEATHER_LEGGINGS, 1),
        ItemStack(Material.LEATHER_BOOTS, 1),

        ItemStack(Material.STICK, 1),
        ItemStack(Material.FISHING_ROD, 1),
        ItemStack(Material.BOOK, rng.int(4, 8)),

        ItemStack(Material.OAK_WOOD, rng.int(12, 24)),
        ItemStack(Material.COAL, rng.int(8, 12)),
        ItemStack(Material.IRON_INGOT, rng.int(4, 8)),
        ItemStack(Material.LAPIS_LAZULI, rng.int(4, 12)),


        ItemStack(Material.BOW, 1),
        ItemStack(Material.ARROW, 4),
        ItemStack(Material.ARROW, 6),
        ItemStack(Material.ARROW, 8),
        ItemStack(Material.ARROW, 10),
        ItemStack(Material.ARROW, 12),
        ItemStack(Material.ARROW, 18),

        ItemStack(Material.TNT, 1),
        ItemStack(Material.ENDER_PEARL, 1)
    )

    val mediumTierItems = listOf(
        ItemStack(Material.IRON_HELMET, 1),
        ItemStack(Material.IRON_CHESTPLATE, 1),
        ItemStack(Material.IRON_LEGGINGS, 1),
        ItemStack(Material.IRON_BOOTS, 1),
        ItemStack(Material.TURTLE_HELMET, 1).withEnchantment(THORNS, 3),

        ItemStack(Material.BOW, 1).withEnchantment(ARROW_FIRE, 1),
        ItemStack(Material.CROSSBOW, 1).withEnchantment(PIERCING, rng.int(3, 4)),
        ItemStack(Material.CROSSBOW, 1).withEnchantment(MULTISHOT, rng.int(1, 3)),
        ItemStack(Material.IRON_SWORD, 1).withEnchantment(DAMAGE_ALL, rng.int(3, 4)),
        ItemStack(Material.IRON_AXE, 1).withEnchantment(DAMAGE_ALL, rng.int(3, 4)),
        ItemStack(Material.WOODEN_SWORD, 1).withEnchantment(KNOCKBACK, 2),
        ItemStack(Material.TRIDENT, 1).withEnchantment(CHANNELING, 1),
        ItemStack(Material.GOLDEN_SWORD, 1).withEnchantment(LOOT_BONUS_MOBS, rng.int(4, 5)),

        ItemStack(Material.GOLDEN_PICKAXE, 1).withEnchantment(DIG_SPEED, rng.int(4, 6)),
        ItemStack(Material.WOODEN_HOE, 1).withEnchantment(DURABILITY, 10),
        ItemStack(Material.GOLDEN_SHOVEL, 1).withEnchantment(DIG_SPEED, rng.int(4, 6)),
        ItemStack(Material.GOLDEN_AXE, 1).withEnchantment(DIG_SPEED, rng.int(4, 6)),
        ItemStack(Material.IRON_PICKAXE, 1).withEnchantment(SILK_TOUCH, 10),

        ItemStack(Material.IRON_BLOCK, 2),
        ItemStack(Material.COAL_BLOCK, 3),
        ItemStack(Material.GOLD_BLOCK, 1),
        ItemStack(Material.DIAMOND, 1),
        ItemStack(Material.DIAMOND, 2),
        ItemStack(Material.DIAMOND, rng.int(1, 4)),
        ItemStack(Material.EMERALD, 2),
        ItemStack(Material.DIAMOND, rng.int(2, 6)),
        ItemStack(Material.GOLD_INGOT, rng.int(4, 9)),

        ItemStack(Material.ENDER_PEARL, rng.int(2, 4)),
        ItemStack(Material.ENCHANTED_BOOK, 1).withRandomEnchantment()
    )

    val highTierItems = listOf(
        ItemStack(Material.DIAMOND_HELMET, 1),
        ItemStack(Material.DIAMOND_CHESTPLATE, 1),
        ItemStack(Material.DIAMOND_LEGGINGS, 1),
        ItemStack(Material.DIAMOND_BOOTS, 1),

        ItemStack(Material.DIAMOND_HELMET, 1)
            .withEnchantment(BINDING_CURSE, 1)
            .withEnchantment(PROTECTION_ENVIRONMENTAL, 5)
            .withEnchantment(PROTECTION_EXPLOSIONS, 5)
            .withEnchantment(PROTECTION_PROJECTILE, 5)
            .withEnchantment(PROTECTION_FIRE, 5)
            .withEnchantment(THORNS, 1)
            .withEnchantment(OXYGEN, 3)
            .withEnchantment(WATER_WORKER, 2)
            .withEnchantment(DURABILITY, 2),

        ItemStack(Material.DIAMOND_CHESTPLATE, 1)
            .withEnchantment(BINDING_CURSE, 1)
            .withEnchantment(PROTECTION_ENVIRONMENTAL, 5)
            .withEnchantment(PROTECTION_EXPLOSIONS, 5)
            .withEnchantment(PROTECTION_PROJECTILE, 5)
            .withEnchantment(PROTECTION_FIRE, 5)
            .withEnchantment(THORNS, 1)
            .withEnchantment(DURABILITY, 2),

        ItemStack(Material.DIAMOND_LEGGINGS, 1)
            .withEnchantment(BINDING_CURSE, 1)
            .withEnchantment(PROTECTION_ENVIRONMENTAL, 5)
            .withEnchantment(PROTECTION_EXPLOSIONS, 5)
            .withEnchantment(PROTECTION_PROJECTILE, 5)
            .withEnchantment(PROTECTION_FIRE, 5)
            .withEnchantment(THORNS, 1)
            .withEnchantment(DURABILITY, 2),

        ItemStack(Material.DIAMOND_BOOTS, 1)
            .withEnchantment(BINDING_CURSE, 1)
            .withEnchantment(PROTECTION_ENVIRONMENTAL, 5)
            .withEnchantment(PROTECTION_EXPLOSIONS, 5)
            .withEnchantment(PROTECTION_PROJECTILE, 5)
            .withEnchantment(PROTECTION_FIRE, 5)
            .withEnchantment(PROTECTION_FALL, 4)
            .withEnchantment(DURABILITY, 2),


        ItemStack(Material.GOLDEN_HELMET, 1)
            .withEnchantment(PROTECTION_ENVIRONMENTAL, rng.int(8, 12))
            .withEnchantment(THORNS, rng.int(1, 4)),

        ItemStack(Material.GOLDEN_CHESTPLATE, 1)
            .withEnchantment(PROTECTION_ENVIRONMENTAL, rng.int(8, 12))
            .withEnchantment(THORNS, rng.int(1, 4)),

        ItemStack(Material.GOLDEN_LEGGINGS, 1)
            .withEnchantment(PROTECTION_ENVIRONMENTAL, rng.int(8, 12)),

        ItemStack(Material.GOLDEN_BOOTS, 1)
            .withEnchantment(PROTECTION_ENVIRONMENTAL, rng.int(8, 12))
            .withEnchantment(PROTECTION_FALL, rng.int(4, 8)),

        ItemStack(Material.LEATHER_HELMET, 1)
            .withEnchantment(PROTECTION_ENVIRONMENTAL, rng.int(8,14))
            .withEnchantment(PROTECTION_EXPLOSIONS, rng.int(8,14))
            .withEnchantment(PROTECTION_PROJECTILE, rng.int(8,14))
            .withEnchantment(PROTECTION_FIRE, rng.int(8,14)),

        ItemStack(Material.LEATHER_CHESTPLATE, 1)
            .withEnchantment(PROTECTION_ENVIRONMENTAL, rng.int(8,14))
            .withEnchantment(PROTECTION_EXPLOSIONS, rng.int(8,14))
            .withEnchantment(PROTECTION_PROJECTILE, rng.int(8,14))
            .withEnchantment(PROTECTION_FIRE, rng.int(8,14)),

        ItemStack(Material.LEATHER_LEGGINGS, 1)
            .withEnchantment(PROTECTION_ENVIRONMENTAL, rng.int(8,14))
            .withEnchantment(PROTECTION_EXPLOSIONS, rng.int(8,14))
            .withEnchantment(PROTECTION_PROJECTILE, rng.int(8,14))
            .withEnchantment(PROTECTION_FIRE, rng.int(8,14)),

        ItemStack(Material.LEATHER_BOOTS, 1)
            .withEnchantment(PROTECTION_ENVIRONMENTAL, rng.int(8,14))
            .withEnchantment(PROTECTION_EXPLOSIONS, rng.int(8,14))
            .withEnchantment(PROTECTION_PROJECTILE, rng.int(8,14))
            .withEnchantment(PROTECTION_FIRE, rng.int(8,14)),

        ItemStack(Material.CHAINMAIL_BOOTS, 1)
            .withEnchantment(PROTECTION_FALL, 5)
            .withEnchantment(FROST_WALKER, 5)
            .withEnchantment(PROTECTION_ENVIRONMENTAL, rng.int(5, 7))
            .withEnchantment(THORNS, rng.int(1,7)),


        ItemStack(Material.GOLDEN_AXE,1)
            .withEnchantment(DIG_SPEED, rng.int(5, 8))
            .withEnchantment(DURABILITY, 2),

        ItemStack(Material.IRON_PICKAXE,1)
            .withEnchantment(VANISHING_CURSE, 1)
            .withEnchantment(LOOT_BONUS_BLOCKS, 4),

        ItemStack(Material.IRON_PICKAXE,1)
            .withEnchantment(SILK_TOUCH, 10)
            .withEnchantment(DURABILITY, 100),

        ItemStack(Material.STICK,1)
            .withEnchantment(KNOCKBACK, 4),

        ItemStack(Material.GOLDEN_SWORD, 1)
            .withEnchantment(DAMAGE_ALL, rng.int(10, 14)),

        ItemStack(Material.GOLDEN_AXE, 1)
            .withEnchantment(DAMAGE_ALL, rng.int(10, 12)),

        ItemStack(Material.WOODEN_SWORD, 1)
            .withEnchantment(FIRE_ASPECT, 2)
            .withEnchantment(KNOCKBACK, 2)
            .withEnchantment(DAMAGE_UNDEAD, 10),

        ItemStack(Material.IRON_SWORD, 1)
            .withEnchantment(LOOT_BONUS_MOBS, rng.int(6, 8)),

        ItemStack(Material.TRIDENT, 1)
            .withEnchantment(LOYALTY, rng.int(2, 4)),

        ItemStack(Material.BOW, 1)
            .withEnchantment(ARROW_INFINITE, 99)
            .withEnchantment(ARROW_DAMAGE, 6),

        ItemStack(Material.BOW, 1)
            .withEnchantment(ARROW_KNOCKBACK, 3),

        ItemStack(Material.EMERALD_BLOCK, rng.int(1,3)),
        ItemStack(Material.DIAMOND_BLOCK,1),
        ItemStack(Material.DIAMOND_BLOCK,1),
        ItemStack(Material.DIAMOND,1).withEnchantment(LOOT_BONUS_BLOCKS, 10).withName("LUCKY DIAMOND!"),


        ItemStack(Material.IRON_BOOTS, 1)
    )

    fun generateRandomItem(): ItemStack {
        return when (getItemChance()) {
            ItemChance.HIGH -> highTierItems[rng.nextInt(highTierItems.size)]
            ItemChance.MED -> mediumTierItems[rng.nextInt(mediumTierItems.size)]
            ItemChance.LOW -> lowTierItems[rng.nextInt(lowTierItems.size)]
        }
    }

    /**
     * 10% of high tier
     * 30% of med tier
     * 40% of low tier
     */
    private fun getItemChance(): ItemChance {
        return when (rng.int(1, 10)) {
            1 -> ItemChance.HIGH
            2,3,4 -> ItemChance.MED
            else -> ItemChance.LOW
        }
    }
}
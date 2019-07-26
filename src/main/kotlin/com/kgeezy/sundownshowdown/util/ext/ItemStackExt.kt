package com.kgeezy.sundownshowdown.util.ext

import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import java.util.*

private val allEnchantments = listOf(
    Enchantment.PROTECTION_ENVIRONMENTAL,
    Enchantment.PROTECTION_FIRE,
    Enchantment.PROTECTION_FALL,
    Enchantment.PROTECTION_EXPLOSIONS,
    Enchantment.PROTECTION_PROJECTILE,
    Enchantment.OXYGEN,
    Enchantment.WATER_WORKER,
    Enchantment.THORNS,
    Enchantment.DEPTH_STRIDER,
    Enchantment.FROST_WALKER,
    Enchantment.BINDING_CURSE,
    Enchantment.DAMAGE_ALL,
    Enchantment.DAMAGE_UNDEAD,
    Enchantment.DAMAGE_ARTHROPODS,
    Enchantment.KNOCKBACK,
    Enchantment.FIRE_ASPECT,
    Enchantment.LOOT_BONUS_MOBS,
    Enchantment.SWEEPING_EDGE,
    Enchantment.DIG_SPEED,
    Enchantment.SILK_TOUCH,
    Enchantment.DURABILITY,
    Enchantment.LOOT_BONUS_BLOCKS,
    Enchantment.ARROW_DAMAGE,
    Enchantment.ARROW_KNOCKBACK,
    Enchantment.ARROW_FIRE,
    Enchantment.ARROW_INFINITE,
    Enchantment.LUCK,
    Enchantment.LURE,
    Enchantment.LOYALTY,
    Enchantment.IMPALING,
    Enchantment.RIPTIDE,
    Enchantment.CHANNELING,
    Enchantment.MULTISHOT,
    Enchantment.QUICK_CHARGE,
    Enchantment.PIERCING,
    Enchantment.MENDING,
    Enchantment.VANISHING_CURSE
)

fun ItemStack.withEnchantment(enchantment: Enchantment, value: Int): ItemStack {
    itemMeta?.let { stackMeta ->
        stackMeta.addEnchant(enchantment, value, true)
        setItemMeta(stackMeta)
    }
    return this
}

fun ItemStack.withName(name: String): ItemStack {
    itemMeta?.let { stackMeta ->
        stackMeta.setDisplayName(name)
        setItemMeta(stackMeta)
    }
    return this
}

fun ItemStack.withRandomEnchantment(): ItemStack {
    val enchantment = allEnchantments[Random().nextInt(allEnchantments.size)]
    withEnchantment(enchantment, enchantment.maxLevel)
    return this
}
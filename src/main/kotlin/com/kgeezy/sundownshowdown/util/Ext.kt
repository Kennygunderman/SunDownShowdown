package com.kgeezy.sundownshowdown.util

import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import java.util.*

fun Random.int(lower: Int, upper: Int) = nextInt(upper - lower + 1) + lower

fun ItemStack.withEnchatment(enchantment: Enchantment, value: Int, name: String? = null): ItemStack {
    itemMeta?.let { stackMeta ->
        stackMeta.addEnchant(enchantment, value, true)
        name?.let {
            stackMeta.setDisplayName(it)
        }
        setItemMeta(stackMeta)
    }

    return this
}
package com.kgeezy.sundownshowdown.util.ext

import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import java.util.*

fun Random.int(lower: Int, upper: Int) = nextInt(upper - lower + 1) + lower
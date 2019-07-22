package com.kgeezy.sundownshowdown.mob

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.EntityType
import java.util.*

class MobSpawner(val world: World?) {
    private val rng by lazy {
        Random()
    }

    private val mobs = listOf(
        EntityType.ZOMBIE,
        EntityType.SKELETON,
        EntityType.CREEPER,
        EntityType.RAVAGER
    )

    fun spawnMobs(location: Location) {
        world?.spawnEntity(location, mobs[rng.nextInt(mobs.size)])
    }

    fun spawnMobs(locations: List<Location>) {
        locations.forEach { loc ->
            spawnMobs(loc)
        }
    }
}
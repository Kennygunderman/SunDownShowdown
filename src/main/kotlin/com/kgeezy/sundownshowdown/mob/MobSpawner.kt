package com.kgeezy.sundownshowdown.mob

import com.kgeezy.sundownshowdown.util.int
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.EntityType
import java.util.*

class MobSpawner(val world: World?) {

    /**
     * maximum amount to spawn if spawning random number of mobs see #spawnMobs
     */
    var minMultiple = 2

    /**
     * maximum amount to spawn if spawning random number of mobs see #spawnMobs
     */
    var maxMultiple = 4

    private val rng by lazy {
        Random()
    }

    private val mobs = listOf(
        EntityType.ZOMBIE,
        EntityType.SKELETON,
        EntityType.CREEPER,
        EntityType.RAVAGER
    )

    fun spawnMob(location: Location) {
        world?.spawnEntity(location, mobs[rng.nextInt(mobs.size)])
    }
    
    /**
     * spawns mobs for all locations provided
     *
     * @param spawnMultiple if set to true, will spawn a random amount of mobs
     * between the `minMultiple` and `maxMultiple` that were set.
     */
    fun spawnMobs(locations: List<Location>, spawnMultiple: Boolean = true) {
        locations.forEach { loc ->
            if (spawnMultiple) {
                for (i in 0 until rng.int(minMultiple, maxMultiple)) spawnMob(loc)
            } else {
                spawnMob(loc)
            }
        }
    }
}
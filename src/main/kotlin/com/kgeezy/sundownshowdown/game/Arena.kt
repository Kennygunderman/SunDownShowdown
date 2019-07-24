package com.kgeezy.sundownshowdown.game

import com.kgeezy.sundownshowdown.util.ArenaFile
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Monster

/***
 * note: file structure
 *  world:
 *   center:
 *    x: 0
 *    y: 0
 *    z: 0
 *   radius: 50.0
 *
 */
class Arena(val world: World, fileManager: ArenaFile) {
    private val yml = fileManager.getArenaYml()
    private val fileConfig = fileManager.configFromYml(yml)

    fun setArena(location: Location, radius: Double) {
        fileConfig.set("${world.name}.arena.x", location.x)
        fileConfig.set("${world.name}.arena.y", location.y)
        fileConfig.set("${world.name}.arena.z", location.z)
        fileConfig.set("${world.name}.radius", radius)
        fileConfig.save(yml)
    }

    private fun arenaLocationFromConfig(callback: (location: Location, radius: Double) -> Unit) {
        val x = fileConfig.get("${world.name}.arena.x") as? Double
        val y = fileConfig.get("${world.name}.arena.y") as? Double
        val z = fileConfig.get("${world.name}.arena.z") as? Double
        val radius = fileConfig.get("${world.name}.radius") as? Double

        if (x != null && y != null && z != null && radius != null) {
            callback(Location(world, x,y,z), radius)
        }
    }

    fun clearMobs() {
        arenaLocationFromConfig { location, radius ->
            val entities = world.getNearbyEntities(location, radius, radius, radius)
            entities.forEach { entity ->
                if (entity.type is Monster) {
                    entity.remove()
                }
            }
        }
    }

    fun removeArena() {
        fileConfig.getConfigurationSection(world.name)?.set("arena", null)
    }
}
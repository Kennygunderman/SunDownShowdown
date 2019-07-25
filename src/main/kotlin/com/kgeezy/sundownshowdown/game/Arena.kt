package com.kgeezy.sundownshowdown.game

import com.kgeezy.sundownshowdown.util.ArenaFile
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.*

class Arena(private val world: World?, fileManager: ArenaFile) {
    private val yml = fileManager.getArenaYml()
    private val fileConfig = fileManager.configFromYml(yml)

    fun setArena(location: Location, radius: Double) {
        fileConfig.set("${world?.name}.arena.x", location.x)
        fileConfig.set("${world?.name}.arena.y", location.y)
        fileConfig.set("${world?.name}.arena.z", location.z)
        fileConfig.set("${world?.name}.arena.radius", radius)
        fileConfig.save(yml)
    }

    private fun arenaLocationFromConfig(callback: (location: Location, radius: Double) -> Unit) {
        val x = fileConfig.get("${world?.name}.arena.x") as? Double
        val y = fileConfig.get("${world?.name}.arena.y") as? Double
        val z = fileConfig.get("${world?.name}.arena.z") as? Double
        val radius = fileConfig.get("${world?.name}.arena.radius") as? Double

        if (x != null && y != null && z != null && radius != null) {
            callback(Location(world, x, y, z), radius)
        }
    }

    fun clearMobs(): Int {
        var numOfMobs = 0
        arenaLocationFromConfig { location, radius ->
            world?.let { w ->
                val entities = w.getNearbyEntities(location, radius, radius, radius)
                numOfMobs = entities.size
                val iter = entities.iterator()
                while (iter.hasNext()) {
                    val entity = iter.next()
                    when (entity?.type) {
                        EntityType.CREEPER,
                        EntityType.ZOMBIE,
                        EntityType.SKELETON,
                        EntityType.SPIDER,
                        EntityType.CAVE_SPIDER,
                        EntityType.EVOKER,
                        EntityType.RAVAGER,
                        EntityType.PILLAGER,
                        EntityType.WITCH,
                        EntityType.SLIME,
                        EntityType.PHANTOM,
                        EntityType.BLAZE,
                        EntityType.WITHER_SKELETON -> {
                            entity.remove()
                        }
                        else -> { }
                    }
                }
            }
        }
        return numOfMobs
    }

    fun removeArena() {
        fileConfig.set("${world?.name}", null)
        fileConfig.save(yml)
    }
}
package com.kgeezy.sundownshowdown.mob

import com.kgeezy.sundownshowdown.util.MobFile
import com.kgeezy.sundownshowdown.util.int
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.entity.EntityType
import java.util.*

private const val MOB_NAME_PILLAGER = "pillager"
private const val MOB_NAME_RAVAGER = "ravager"

class MobSpawner(val world: World?, fileManager: MobFile) {

    private val yml = fileManager.getMobYml()
    private val fileConfig = fileManager.configFromYml(yml)

    private val mobConfigSection: ConfigurationSection?
        get() = fileConfig
            .getConfigurationSection("${world?.name}")
            ?.getConfigurationSection("mobs")

    /**
     * maximum amount to spawn if spawning random number of mobs see #spawnRandomMobs
     */
    var minMultiple = 2

    /**
     * maximum amount to spawn if spawning random number of mobs see #spawnRandomMobs
     */
    var maxMultiple = 4

    private val rng by lazy {
        Random()
    }

    private val mobs = listOf(
        EntityType.ZOMBIE,
        EntityType.SKELETON,
        EntityType.SPIDER
    )

    /**
     * List of available mobs to add single spawn points for
     */
    private val availableMobs = listOf(
        MOB_NAME_PILLAGER,
        MOB_NAME_RAVAGER
    )

    fun isMobAvailable(mob: String): Boolean = availableMobs.contains(mob)

    fun spawnMob(location: Location, entityType: EntityType) {
        world?.spawnEntity(location, entityType)
    }

    /**
     * Spawns a random mob
     */
    fun spawnRandomMob(location: Location) {
        spawnMob(location, mobs[rng.nextInt(mobs.size)])
    }

    /**
     * spawns random mobs for all locations provided
     *
     * @param spawnMultiple if set to true, will spawn a random amount of mobs
     * between the `minMultiple` and `maxMultiple` that were set.
     */
    fun spawnRandomMobs(locations: List<Location>, spawnMultiple: Boolean = true) {
        locations.forEach { loc ->
            if (spawnMultiple) {
                for (i in 0 until rng.int(minMultiple, maxMultiple)) spawnRandomMob(loc)
            } else {
                spawnRandomMob(loc)
            }
        }
    }

    /**
     * save a specific Mob and it's location to the config.
     *
     * @return true if the mob name is among the available mobs to spawn into the world
     */
    fun saveMob(location: Location, mobName: String): Boolean {
        return getMobEntityFromName(mobName)?.let { mob ->
            val size = mobConfigSection?.getKeys(false)?.size ?: 0
            fileConfig.set("${world?.name}.mobs.$size.x", location.x)
            fileConfig.set("${world?.name}.mobs.$size.y", location.y)
            fileConfig.set("${world?.name}.mobs.$size.z", location.z)
            fileConfig.set("${world?.name}.mobs.$size.type", mobName)
            fileConfig.save(yml)
            true
        } ?: false
    }

    private fun getMobLocationsFromConfig(): List<MobLocation> {
        return mutableListOf<MobLocation>().apply {
            val mobConfigSection = fileConfig
                .getConfigurationSection("${world?.name}")
                ?.getConfigurationSection("mobs")

            mobConfigSection?.getKeys(false)?.forEach { mobIndex ->
                val x = mobConfigSection.getConfigurationSection(mobIndex)?.get("x") as? Double
                val y = mobConfigSection.getConfigurationSection(mobIndex)?.get("y") as? Double
                val z = mobConfigSection.getConfigurationSection(mobIndex)?.get("z") as? Double
                val type = mobConfigSection.getConfigurationSection(mobIndex)?.get("type") as? String

                if (x != null && y != null && z != null && type != null) {
                    add(MobLocation(Location(world, x, y, z), type))
                }
            }
        }
    }

    private fun getMobEntityFromName(mobName: String): EntityType? {
        return when (mobName) {
            MOB_NAME_PILLAGER -> EntityType.PILLAGER
            MOB_NAME_RAVAGER -> EntityType.RAVAGER
            else -> null
        }
    }

    fun spawnMobsFromConfig() {
        getMobLocationsFromConfig().forEach { mobLoc ->
            mobLoc.type?.let { entity ->
                spawnMob(mobLoc.location, entity)
            }
        }
    }

    fun removeAllMobSpawns() {
        fileConfig.getConfigurationSection("${world?.name}")?.set("mobs", null)
        fileConfig.save(yml)
    }

    internal class MobLocation(val location: Location, mobName: String) {
        val type: EntityType? = typeFromName(mobName)

        private fun typeFromName(mobName: String): EntityType? {
            return when (mobName) {
                MOB_NAME_PILLAGER -> EntityType.PILLAGER
                MOB_NAME_RAVAGER -> EntityType.RAVAGER
                else -> null
            }
        }
    }
}
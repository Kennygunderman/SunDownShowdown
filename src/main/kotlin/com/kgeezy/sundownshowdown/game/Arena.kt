package com.kgeezy.sundownshowdown.game

import com.kgeezy.sundownshowdown.util.ArenaFile
import org.bukkit.World
import javax.xml.stream.Location

class Arena(val world: World?, fileManager: ArenaFile) {
    private val yml = fileManager.getArenaYml()
    private val fileConfig = fileManager.configFromYml(yml)

    fun setArena(location: Location, radius: Long) {

    }

    fun arenaCoordsFileConfig() {

    }

    fun clearMobs() {

    }
}
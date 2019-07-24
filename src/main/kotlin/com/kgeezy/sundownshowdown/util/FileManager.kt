package com.kgeezy.sundownshowdown.util

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

private const val YML_EXT = ".yml"
private const val CHEST_LOCATION_YML = "chest-location$YML_EXT"
private const val ARENA_YML = "arena$YML_EXT"

interface FileConfig {
    fun configFromYml(ymlFile: File): YamlConfiguration
}

interface ChestLocationFile: FileConfig {
    fun getChestLocationYml(): File
}

interface ArenaFile: FileConfig {
    fun getArenaYml(): File
}

class FileManager private constructor(private val pluginFolder: File): ChestLocationFile, ArenaFile {
    companion object {
        private var instance: FileManager? = null

        fun initialize(pluginFolder: File) {
            instance = FileManager(pluginFolder)
        }

        fun getInstance(): FileManager {
            return if (instance == null) {
                throw Exception("File Manager must be initialized with the Plugin Folder!")
            } else {
                instance!!
            }
        }
    }
    override fun configFromYml(ymlFile: File): YamlConfiguration = YamlConfiguration.loadConfiguration(ymlFile)

    override fun getChestLocationYml(): File = File("$pluginFolder/$CHEST_LOCATION_YML")
    override fun getArenaYml(): File = File("$pluginFolder/$ARENA_YML")
}
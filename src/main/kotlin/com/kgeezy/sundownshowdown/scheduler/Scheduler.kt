package com.kgeezy.sundownshowdown.scheduler

import com.kgeezy.sundownshowdown.DUSK
import com.kgeezy.sundownshowdown.MINUTE
import com.kgeezy.sundownshowdown.SECOND
import com.kgeezy.sundownshowdown.StringRes
import com.kgeezy.sundownshowdown.chest.ChestGenerator
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable

class ShowDownScheduler(private val plugin: Plugin, private val chestGenerator: ChestGenerator, private val worldName: String = "world")  {
    var currentTask = 0
    var isFinalCountDownRunning = false

    fun scheduleMainTask() {
        currentTask = scheduleTask()
    }

    private val worldTime
            get() = plugin.server.getWorld(worldName)?.time ?: 0

    private fun scheduleTask(): Int =
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, {
            if (worldTime > DUSK - MINUTE  && worldTime < DUSK) {
                if (!isFinalCountDownRunning) {
                    cancelMainTask()
                    startFinalCountDownTask()
                }

                plugin.server.broadcastMessage(String.format(StringRes.SHOWDOWN_WILL_BEGIN_SECONDS, getSecondsLeftFromTime(worldTime)))
            }
        }, 0, SECOND * 1)

    private fun startFinalCountDownTask() {
        isFinalCountDownRunning = true
        object: BukkitRunnable() {
            override fun run() {
                if (worldTime > DUSK - (SECOND + 5)) {
                    plugin.server.broadcastMessage(String.format(StringRes.SHOWDOWN_WILL_BEGIN_SECONDS, getSecondsLeftFromTime(worldTime)))
                }

                if (worldTime >= DUSK) {
                    plugin.server.broadcastMessage(StringRes.SHOWDOWN_START)
                    chestGenerator.restockChests()
                    this.cancel()
                    isFinalCountDownRunning = false
                    scheduleMainTask()
                }
            }
        }.runTaskTimer(plugin, 0, SECOND)
    }
    
    private fun getSecondsLeftFromTime(time: Long): String {
        val ticksLeft = DUSK - time
        val secondsLeft = ticksLeft / SECOND
        return secondsLeft.toString()
    }

    private fun cancelMainTask() {
        Bukkit.getScheduler().cancelTask(currentTask)
    }
}
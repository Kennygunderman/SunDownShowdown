package com.kgeezy.sundownshowdown.scheduler

import com.kgeezy.sundownshowdown.DUSK
import com.kgeezy.sundownshowdown.MINUTE
import com.kgeezy.sundownshowdown.SECOND
import com.kgeezy.sundownshowdown.chest.ChestGenerator
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin


/**
 * Todo: clean up.....
 */
class ShowDownScheduler(private val plugin: Plugin, private val chestGenerator: ChestGenerator)  {
    var currentTask = 0
    var finalCountDownTask  = 0

    fun scheduleShowdownTask() {
        currentTask = scheduleTask()
    }

    private val worldTime
            get() = plugin.server.getWorld("world")?.time ?: 0

    private fun scheduleTask(): Int =
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, {
            if (worldTime > DUSK) {
                return@scheduleSyncRepeatingTask
            }

            if (worldTime > DUSK - MINUTE) {

                //cancel current task
                cancelShowdownTask()

                //start final countdown
                startFinalCountDownTask()

                plugin.server.broadcastMessage("Showdown will begin in ${getSecondsLeftFromTime(worldTime)} Seconds!")
                chestGenerator.restockChests()
            }
        }, 0, SECOND * 1) //check every 20 seconds

    private fun startFinalCountDownTask() {
        finalCountDownTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, {
            if (worldTime > DUSK - (SECOND + 5)) {
                plugin.server.broadcastMessage("Showdown will begin in ${getSecondsLeftFromTime(worldTime)} Seconds!")
            }

            if (worldTime >= DUSK) {
                plugin.server.broadcastMessage("Showdown has begun!")

                //once final countdown is complete, cancel it
                cancelFinalCountDownTask()

                //restart main task
                scheduleShowdownTask()
            }
        }, 0, SECOND)
    }

    private fun cancelFinalCountDownTask() {
        Bukkit.getScheduler().cancelTask(finalCountDownTask)
    }

    private fun getSecondsLeftFromTime(time: Long): String {
        val ticksLeft = DUSK - time
        val secondsLeft = ticksLeft / SECOND
        return secondsLeft.toString()
    }

    private fun cancelShowdownTask() {
        Bukkit.getScheduler().cancelTask(currentTask)
    }
}
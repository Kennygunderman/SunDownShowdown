package com.kgeezy.sundownshowdown.scheduler

import com.kgeezy.sundownshowdown.DEFAULT_WORLD
import com.kgeezy.sundownshowdown.DUSK
import com.kgeezy.sundownshowdown.MINUTE
import com.kgeezy.sundownshowdown.SECOND
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable

interface ShowdownSchedulerCallback {
    fun finalCountdownDone()
    fun secondsLeft(secondsLeft: Long)
}

const val TASK_NONE = 0

class ShowdownScheduler(private val plugin: Plugin, private val worldName: String = DEFAULT_WORLD)  {

    var callback: ShowdownSchedulerCallback? = null

    private var currentTask = TASK_NONE
    private var isFinalCountDownRunning = false

    fun scheduleMainTask() {
        if (currentTask == TASK_NONE) {
            currentTask = scheduleTask()
        }
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
                callback?.secondsLeft(getSecondsLeftFromTime(worldTime))

            }
        }, 0, SECOND * 20) //check every 20 seconds

    /**
     * This function is responsible for starting a final second by second count down when 5 seconds are left until
     * the showdown begins.
     *
     * once the logic is met to begin the showdown (worldTime >= DUSK) the task should preform the
     * necessary logic for running the showdown, cancel its self, and then reschedule the main task.
     */
    private fun startFinalCountDownTask() {
        isFinalCountDownRunning = true
        object: BukkitRunnable() {
            override fun run() {
                if (worldTime >= DUSK) {
                    callback?.finalCountdownDone()
                    this.cancel()
                    isFinalCountDownRunning = false
                    scheduleMainTask()
                    return
                }

                if (worldTime > DUSK - (SECOND * 6)) {
                    callback?.secondsLeft(getSecondsLeftFromTime(worldTime))
                }

            }
        }.runTaskTimer(plugin, 0, SECOND)
    }
    
    private fun getSecondsLeftFromTime(time: Long): Long {
        val ticksLeft = DUSK - time
        return ticksLeft / SECOND
    }

    fun cancelMainTask() {
        Bukkit.getScheduler().cancelTask(currentTask)
        currentTask = TASK_NONE
    }
}
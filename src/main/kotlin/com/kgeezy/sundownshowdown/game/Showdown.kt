package com.kgeezy.sundownshowdown.game

import com.kgeezy.sundownshowdown.StringRes
import com.kgeezy.sundownshowdown.chest.ChestGenerator
import com.kgeezy.sundownshowdown.mob.MobSpawner
import com.kgeezy.sundownshowdown.scheduler.ShowdownScheduler
import com.kgeezy.sundownshowdown.scheduler.ShowdownSchedulerCallback
import org.bukkit.Server

class Showdown(val server: Server, val scheduler: ShowdownScheduler, val chestGenerator: ChestGenerator, val mobSpawner: MobSpawner) {

    private val schedulerListener = object: ShowdownSchedulerCallback {
        override fun finalCountdownDone() {
            server.broadcastMessage(StringRes.SHOWDOWN_START)
            startGame()
        }

        override fun secondsLeft(secondsLeft: Long) {
            server.broadcastMessage(String.format(StringRes.SHOWDOWN_WILL_BEGIN_SECONDS, secondsLeft.toString()))
        }
    }

    fun enable() {
        scheduler.scheduleMainTask()
        scheduler.callback = schedulerListener
    }

    fun disable() {
        scheduler.cancelMainTask()
    }


    fun startGame() {
        strikeLighting()
        chestGenerator.restockChests()
        val chestLocs = chestGenerator.getChestLocations()
        mobSpawner.spawnMobs(chestLocs)
    }

    /**
     * STRIKES LIGHTNING!!
     */
    private fun strikeLighting() {
        chestGenerator.getChestLocations().forEach { loc ->
            chestGenerator.world?.strikeLightningEffect(loc)
        }
    }
}
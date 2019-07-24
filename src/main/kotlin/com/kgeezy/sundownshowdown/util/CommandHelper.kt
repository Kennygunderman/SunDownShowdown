package com.kgeezy.sundownshowdown.util

import com.kgeezy.sundownshowdown.*

object CommandHelper {

    /**
     * Method to parse through args and return the corresponding command
     */
    fun command(args: Array<out String>, callback: (cmd: Command?, msg: String?, param: String?) -> Unit) {
        var usage: String? = null
        var param: String? = null
        val cmd: Command? = when (args.firstOrNull()) {
            CHEST_ARG -> {
                when (args.getOrNull(1)) {
                    CHEST_ADD_ARG -> Command.CHEST_ADD
                    CHEST_RESTOCK_ARG -> Command.CHEST_RESTOCK
                    CHEST_REMOVE_ARG -> {
                        if (args.getOrNull(2) == CHEST_REMOVE_ALL_ARG) Command.CHEST_REMOVE_ALL
                        else Command.CHEST_REMOVE
                    }
                    else -> {
                        usage = StringRes.SHOWDOWN_CHEST_CMD_USAGE
                        null
                    }
                }
            }

            ARENA_ARG -> {
                when (args.getOrNull(1)) {
                    ARENA_SET_ARG -> {
                        if (args.getOrNull(2) == ARENA_SET_RADIUS_ARG
                            && args.getOrNull(3) != null) {
                            param = args.getOrNull(3)
                            Command.ARENA_SET
                        } else {
                            usage = StringRes.SHOWDOWN_ARENA_SET_USAGE
                            null
                        }
                    }

                    ARENA_REMOVE_ARG -> Command.ARENA_REMOVE
                    ARENA_CLEAR_ARG -> Command.ARENA_CLEAR

                    else -> {
                        usage = StringRes.SHOWDOWN_ARENA_USAGE
                        null
                    }
                }
            }

            START_ARG -> Command.START
            ENABLE_ARG -> Command.ENABLE
            DISABLE_ARG -> Command.DISABLE
            else -> {
                usage = StringRes.SHOWDOWN_CMD_USAGE
                null
            }
        }
        callback.invoke(cmd, usage, param)
    }
}
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
                    ADD_ARG -> Command.CHEST_ADD
                    CHEST_RESTOCK_ARG -> Command.CHEST_RESTOCK
                    REMOVE_ARG -> {
                        if (args.getOrNull(2) == ALL_ARG) Command.CHEST_REMOVE_ALL
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

                    REMOVE_ARG -> Command.ARENA_REMOVE
                    ARENA_CLEAR_ARG -> Command.ARENA_CLEAR

                    else -> {
                        usage = StringRes.SHOWDOWN_ARENA_USAGE
                        null
                    }
                }
            }

            MOB_ARG -> {
                when (args.getOrNull(1)) {
                    ADD_ARG -> {
                        if (args.getOrNull(2) != null) {
                            param = args.getOrNull(2)
                            Command.MOB_ADD
                        } else {
                            usage = StringRes.SHOWDOWN_MOB_ADD_USAGE
                            null
                        }
                    }

                    REMOVE_ARG -> {
                        if (args.getOrNull(2) == ALL_ARG) {
                            Command.MOB_REMOVE_ALL
                        } else {
                            usage = StringRes.SHOWDOWN_MOB_REMOVE_USAGE
                            null
                        }
                    }

                    MOB_SPAWN_ARG -> {
                        when(args.getOrNull(2)) {
                            CHEST_ARG -> Command.MOB_SPAWN_CHESTS
                            ALL_ARG -> Command.MOB_SPAWN_ALL
                            MOB_SPAWN_POINTS_ARG -> Command.MOB_SPAWN_POINTS
                            else -> {
                                usage = StringRes.SHOWDOWN_MOB_SPAWN_USAGE
                                null
                            }
                        }
                    }

                    else -> {
                        usage = StringRes.SHOWDOWN_MOB_USAGE
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
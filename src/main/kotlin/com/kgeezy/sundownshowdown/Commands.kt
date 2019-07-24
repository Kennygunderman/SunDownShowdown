package com.kgeezy.sundownshowdown

const val MAIN = "showdown"

const val CHEST_ARG = "chest"
const val CHEST_ADD_ARG = "add"
const val CHEST_RESTOCK_ARG = "restock"
const val CHEST_REMOVE_ARG = "remove"
const val CHEST_REMOVE_ALL_ARG = "all"

const val START_ARG = "start"
const val ENABLE_ARG = "enable"
const val DISABLE_ARG = "disable"

const val ARENA_ARG = "arena"
const val ARENA_SET_ARG = "set"
const val ARENA_SET_RADIUS_ARG = "radius"
const val ARENA_REMOVE_ARG = "remove"
const val ARENA_CLEAR_ARG = "clear"

enum class Command {
    CHEST_ADD,
    CHEST_REMOVE,
    CHEST_RESTOCK,
    CHEST_REMOVE_ALL,
    START,
    ENABLE,
    DISABLE,
    ARENA_SET,
    ARENA_REMOVE,
    ARENA_CLEAR
}
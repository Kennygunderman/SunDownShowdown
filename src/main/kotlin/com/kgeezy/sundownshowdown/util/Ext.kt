package com.kgeezy.sundownshowdown.util

import java.util.*

fun Random.int(lower: Int, upper: Int) = nextInt(upper - lower + 1) + lower
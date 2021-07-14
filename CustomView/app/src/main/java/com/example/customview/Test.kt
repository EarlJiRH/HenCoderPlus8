package com.example.customview

import java.lang.Thread.sleep

/**
 * ================================================
 * 类名：com.example.customview
 * 时间：2021/7/12 11:42
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */


val task1: () -> String = {
    sleep(2000)
    "Hello".also { println("task1 finished: $it") }
}

val task2: () -> String = {
    sleep(2000)
    "World".also { println("task2 finished: $it") }
}

val task3: (String, String) -> String = { p1, p2 ->
    sleep(2000)
    "$p1 $p2".also { println("task3 finished: $it") }
}

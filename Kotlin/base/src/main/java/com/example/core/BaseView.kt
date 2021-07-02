package com.example.core

interface BaseView<T> {
//    fun getPresenter(): T

    //委托实现类生成
    val presenter:T
}
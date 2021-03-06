package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.toast
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken
import java.util.*

class LessonPresenter(private val activity: LessonActivity) {

    companion object {
        private const val LESSON_PATH = "lessons"
    }

    private var lessons: List<Lesson> = ArrayList()
    private val type = object : TypeToken<List<Lesson>>() {}.type

    fun fetchData() {
        HttpClient[LESSON_PATH, type, object : EntityCallback<List<Lesson>> {

            override fun onSuccess(entity: List<Lesson>) {
                //获取外部类对象引用
                this@LessonPresenter.lessons = entity
                activity.runOnUiThread { activity.showResult(entity) }
            }

            override fun onFailure(message: String) {
                activity.runOnUiThread { toast(message) }
            }

        }]
    }

    fun showPlayback() {
        //操作符直接过滤数据
        activity.showResult(lessons.filter { it.state === Lesson.State.PLAYBACK })
    }


}
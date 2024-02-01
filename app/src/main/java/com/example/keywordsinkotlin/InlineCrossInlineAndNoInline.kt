package com.example.keywordsinkotlin

sealed class Video() {
    data class Programming(val title: String, val duration: String) : Video()
    data class Cooking(val title: String, val duration: String) : Video()
    data class Travel(val title: String, val duration: String) : Video()
}

inline fun <reified T : Video> filter(videos: List<Video>): List<T> {
    return videos.filterIsInstance<T>()
}

inline fun applyTransformation(
    videos: List<Video>,
    noinline transformation: (Video) -> Video,
    crossinline onComplete: (List<Video>) -> Unit
) {
    val v = videos.map { transformation(it) }
    onComplete.invoke(v)
}

class VideoPlayable<T> where T : Video {
    fun playVideo(list: List<T>) {

        list.forEach {
            when (it) {
                is Video.Programming -> {
                    println(it.title + "played")
                }

                is Video.Travel -> {
                    println(it.title + "played")

                }

                is Video.Cooking -> {
                    println(it.title + "played")

                }
            }
        }

    }
}

fun main() {
    val videos = listOf(
        Video.Programming("Kotlin Basics", "10:30"),
        Video.Cooking("Cooking with Kotlin", "15:45"),
        Video.Programming("Kotlin for Beginners", "20:12"),
        Video.Travel("Travel Vlog: Kotlin Edition", "30:05")
    )

    val filter = filter<Video.Programming>(videos)
    print(filter.toString())

    applyTransformation(videos, transformation = {
        when (it) {
            is Video.Programming -> {
                it.copy(title = it.title + "transformed")
            }

            is Video.Travel -> {
                it.copy(title = it.title + "transformed")

            }

            is Video.Cooking -> {
                it.copy(title = it.title + "transformed")
            }
        }
    }, onComplete = {
        it.forEach {
            println(it.toString())
        }
    })

    val videoPlayable = VideoPlayable<Video>()
    videoPlayable.playVideo(videos)
}

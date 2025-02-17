package com.ll.com.ll.domain.wiseSaying.wiseSaying.`object`

import com.ll.domain.wiseSaying.wiseSaying.entity.WiseSaying
import com.ll.file.FileUtil

object WiseSayings {
    private var list = mutableListOf<WiseSaying>()

    init {
        list = FileUtil().loadListFromJson("data.json").toMutableList()
    }

    fun getList(): List<WiseSaying> {
        return list
    }

    fun remove(wiseSaying: WiseSaying) {
        list.remove(wiseSaying)
    }

    fun add(wiseSaying: WiseSaying) {
        list.add(wiseSaying)
    }

    fun modify(wiseSaying: WiseSaying) {
        val idx = list.indexOfFirst { it.id == wiseSaying.id }
        val item = list[idx]
        item.modify(wiseSaying.content, wiseSaying.author)

        list[idx] = item
    }
}
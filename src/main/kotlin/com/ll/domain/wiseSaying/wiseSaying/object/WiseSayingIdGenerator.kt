package com.ll.com.ll.domain.wiseSaying.wiseSaying.`object`

import com.ll.file.FileUtil

object WiseSayingIdGenerator {
    private var lastId = 0

    init {
        lastId = FileUtil().loadLastId("lastId.txt")
    }

    fun getNextId(): Int {
        return ++lastId
    }

    fun getPreId(): Int {
        return --lastId
    }

    fun getId(): Int {
        return lastId
    }
}
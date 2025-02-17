package com.ll.com.ll.domain.wiseSaying.wiseSaying.repository

import com.ll.com.ll.domain.wiseSaying.wiseSaying.`object`.WiseSayingIdGenerator
import com.ll.com.ll.domain.wiseSaying.wiseSaying.`object`.WiseSayings
import com.ll.domain.wiseSaying.wiseSaying.entity.WiseSaying
import com.ll.file.FileUtil

class WiseSayingRepository(private val fileUtil: FileUtil) {

    fun save(wiseSaying: WiseSaying) {
        fileUtil.saveJson(wiseSaying, "${wiseSaying.id}.json")
        WiseSayings.add(wiseSaying)
    }

    fun findAll(): List<WiseSaying> {
        return WiseSayings.getList()
    }

    fun findById(id: Int): WiseSaying? {
        return WiseSayings.getList()
            .find { it.id == id}
    }

    fun delete(wiseSaying: WiseSaying) {
        fileUtil.fileRemove("${wiseSaying.id}.json")
    }

    fun modify(wiseSaying: WiseSaying) {
        fileUtil.updateJsonFile(wiseSaying)
    }

    fun build() {
        fileUtil.saveJson(WiseSayings.getList(), "data.json")
    }

    fun stop() {
        fileUtil.saveJson(WiseSayingIdGenerator.getId(), "lastId.txt")
    }
}

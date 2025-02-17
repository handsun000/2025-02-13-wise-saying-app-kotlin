package com.ll.domain.wiseSaying.wiseSaying.service

import com.ll.com.ll.domain.wiseSaying.wiseSaying.`object`.WiseSayingIdGenerator
import com.ll.com.ll.domain.wiseSaying.wiseSaying.`object`.WiseSayings
import com.ll.com.ll.domain.wiseSaying.wiseSaying.repository.WiseSayingRepository
import com.ll.domain.wiseSaying.wiseSaying.entity.WiseSaying

class WiseSayingService(private val wiseSayingRepository: WiseSayingRepository) {

    fun write(content: String, author: String) {
        val lastId = WiseSayingIdGenerator.getNextId()

        val wiseSaying = WiseSaying(lastId, content, author)
        wiseSayingRepository.save(wiseSaying)
    }

    fun getList() {
        val wiseSayings: List<WiseSaying> = wiseSayingRepository.findAll();

        for (wiseSaying in wiseSayings) {
            println("${wiseSaying.id} / ${wiseSaying.author} / ${wiseSaying.content}")
        }
    }

    fun delete(wiseSaying: WiseSaying) {
        WiseSayings.remove(wiseSaying)
        WiseSayingIdGenerator.getPreId()
        wiseSayingRepository.delete(wiseSaying)
    }

    fun modify(wiseSaying: WiseSaying) {
        WiseSayings.modify(wiseSaying)
        wiseSayingRepository.modify(wiseSaying)
    }

    fun getItem(id: Int): WiseSaying? {
        return wiseSayingRepository.findById(id)
    }

    fun build() {
        return wiseSayingRepository.build()
    }

    fun stop() {
        return wiseSayingRepository.stop()
    }
}
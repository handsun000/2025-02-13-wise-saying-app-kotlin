package com.ll.domain.wiseSaying.wiseSaying.service

import com.ll.com.ll.domain.wiseSaying.wiseSaying.`object`.WiseSayingIdGenerator
import com.ll.com.ll.domain.wiseSaying.wiseSaying.`object`.WiseSayings
import com.ll.com.ll.domain.wiseSaying.wiseSaying.repository.WiseSayingRepository
import com.ll.domain.wiseSaying.wiseSaying.entity.WiseSaying
import kotlin.math.ceil

class WiseSayingService(private val wiseSayingRepository: WiseSayingRepository) {

    fun write(content: String, author: String) {
        val lastId = WiseSayingIdGenerator.getNextId()

        val wiseSaying = WiseSaying(lastId, content, author)
        wiseSayingRepository.save(wiseSaying)
    }

    fun getList(param: String): Triple<List<WiseSaying>, Int, Int> {

        var wiseSayings = wiseSayingRepository.findAll().reversed()

        var page = 1
        val pageSize = 5
        var start = (page - 1) * pageSize
        var end = minOf(page * pageSize, wiseSayings.size)

        if (param.isNotEmpty()) {
            try {
                val params = param.split("&").associate {
                    if (!it.contains("=")) throw IllegalArgumentException("Invalid parameter format")
                    val (key, value) = it.split("=")
                    key to value
                }

                val keywordType = params["keywordType"].toString()
                val keyword = params["keyword"].toString()

                page = params["page"]?.toIntOrNull() ?: 1
                start = (page - 1) * pageSize
                end = page * pageSize

                if (keywordType == "content")
                    wiseSayings = wiseSayings
                        .filter { it.content.contains(keyword) }
                else if (keywordType == "author")
                    wiseSayings = wiseSayings
                        .filter { it.author.contains(keyword) }
            } catch (e: Exception) {
                println(e.message)
                return Triple(emptyList(), 1, 1)
            }
        }

        val totalPage = ceil(wiseSayings.size.toDouble() / pageSize).toInt()

        return Triple(wiseSayings.subList(start, end), page, totalPage)
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
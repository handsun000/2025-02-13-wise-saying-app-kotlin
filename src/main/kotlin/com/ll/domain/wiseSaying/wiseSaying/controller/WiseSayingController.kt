package com.ll.com.ll.domain.wiseSaying.wiseSaying.controller

import com.ll.com.ll.domain.wiseSaying.wiseSaying.`object`.WiseSayingIdGenerator
import com.ll.domain.wiseSaying.wiseSaying.service.WiseSayingService

class WiseSayingController(private val wiseSayingService: WiseSayingService) {

    fun write() {
        print("명언 : ")
        val content = readlnOrNull()!!.trim()

        print("작가 : ")
        val author = readlnOrNull()!!.trim()

        wiseSayingService.write(content, author)

        println("${WiseSayingIdGenerator.getId()}번 명언이 등록되었습니다.")
    }

    fun getList() {
        println("번호 / 작가 / 명언")
        println("----------------------")

        val wiseSayings = wiseSayingService.getList()

        for (wiseSaying in wiseSayings) {
            println("${wiseSaying.id} / ${wiseSaying.author} / ${wiseSaying.content}")
        }
    }

    fun delete(id: Int?) {
        if (id != null) {
            val wiseSaying = wiseSayingService.getItem(id)

            if (wiseSaying == null) {
                println("${id}번 명언은 존재하지 않습니다.")
            } else {
                wiseSayingService.delete(wiseSaying)
                println("${id}번 명언이 삭제되었습니다.")
            }
        } else return
    }

    fun modify(id: Int?) {
        if (id != null) {
            val wiseSaying = wiseSayingService.getItem(id)

            if (wiseSaying == null) {
                println("${id}번 명언은 존재하지 않습니다.")
            } else {
                println("명언 (기존) : ${wiseSaying.content}")
                print("명언 : ")
                wiseSaying.content = readlnOrNull()!!.trim()

                println("작가(기존) : ${wiseSaying.author}")
                print("작가 : ")
                wiseSaying.author = readlnOrNull()!!.trim()

                wiseSayingService.modify(wiseSaying)
            }
        } else return
    }

    fun build() {
        wiseSayingService.build()
        println("data.json 파일의 내용이 갱신되었습니다.")
    }

    fun stop() {
        wiseSayingService.stop()
        println("프로그램이 종료됩니다.")
    }
}
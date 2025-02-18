package com.ll.com.ll.domain.wiseSaying.wiseSaying.controller

import com.ll.com.ll.domain.wiseSaying.wiseSaying.`object`.WiseSayingIdGenerator
import com.ll.com.ll.domain.wiseSaying.wiseSaying.`object`.WiseSayings
import com.ll.domain.wiseSaying.wiseSaying.entity.WiseSaying
import com.ll.domain.wiseSaying.wiseSaying.service.WiseSayingService
import kotlin.reflect.typeOf

class WiseSayingController(private val wiseSayingService: WiseSayingService) {

    fun write() {
        print("명언 : ")
        val content = readlnOrNull()!!.trim()

        print("작가 : ")
        val author = readlnOrNull()!!.trim()

        wiseSayingService.write(content, author)

        println("${WiseSayingIdGenerator.getId()}번 명언이 등록되었습니다.")
    }

    fun getList(param: String) {
        println("번호 / 작가 / 명언")
        println("----------------------")

        val (wiseSayings, page, totalPage) = wiseSayingService.getList(param)

        for (wiseSaying in wiseSayings) {
            println("${wiseSaying.id} / ${wiseSaying.author} / ${wiseSaying.content}")
        }

        println("----------------------")
        print("페이지 : ")

        for (i in 1..totalPage) {
            if (i != page) print(i)
            else print("[${i}]")

            if (i != totalPage)
            print(" / ")
        }
        println()
    }

    fun delete(param: String) {
        if (param.contains("id=")) {
            val id = param.substringAfter("id=").toIntOrNull() ?: return
            val wiseSaying = wiseSayingService.getItem(id)

            if (wiseSaying == null) {
                println("${id}번 명언은 존재하지 않습니다.")
            } else {
                wiseSayingService.delete(wiseSaying)
                println("${id}번 명언이 삭제되었습니다.")
            }
        }
    }

    fun modify(param: String) {
        if (param.contains("id=")) {
            val id = param.substringAfter("id=").toIntOrNull() ?: return
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
        }
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
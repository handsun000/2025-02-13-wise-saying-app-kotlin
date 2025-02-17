package com.ll

import com.ll.com.ll.domain.wiseSaying.wiseSaying.controller.WiseSayingController
import com.ll.com.ll.domain.wiseSaying.wiseSaying.repository.WiseSayingRepository
import com.ll.domain.wiseSaying.wiseSaying.service.WiseSayingService
import com.ll.file.FileUtil

fun main() {
    val wiseSayingRepository = WiseSayingRepository(FileUtil())
    val wiseSayingService = WiseSayingService(wiseSayingRepository)
    val wiseSayingController = WiseSayingController(wiseSayingService)

    App(wiseSayingController).run()
}
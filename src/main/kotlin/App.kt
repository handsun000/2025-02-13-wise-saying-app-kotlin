package com.ll

import com.ll.com.ll.domain.wiseSaying.wiseSaying.controller.WiseSayingController

class App(private val wiseSayingController: WiseSayingController) {
    fun run() {
        println("== 명언 앱 ==")

        while (true) {
            print("명령) ")

            val command = readlnOrNull()!!.trim()

            val (cmd, id) = parseCommand(command)

            when (cmd){
                "등록" -> wiseSayingController.write()
                "목록" -> wiseSayingController.getList()
                "삭제" -> wiseSayingController.delete(id)
                "수정" -> wiseSayingController.modify(id)
                "빌드" -> wiseSayingController.build()
                "종료" -> {
                    wiseSayingController.stop()
                    break
                }
            }
        }
    }

    private fun parseCommand(command: String): Pair<String, Int?> {
        if (command.contains("?")) {
            val split = command.split("?")
            val cmd = split[0]
            val id = split[1].split("=")[1].toIntOrNull()

            return Pair(cmd, id)
        }
        return Pair(command, null)
    }
}

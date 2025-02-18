package com.ll

import com.ll.com.ll.domain.wiseSaying.wiseSaying.controller.WiseSayingController

class App(private val wiseSayingController: WiseSayingController) {
    fun run() {
        println("== 명언 앱 ==")

        while (true) {
            print("명령) ")

            val command = readlnOrNull()!!.trim()

            val (cmd, param) = parseCommand(command)

            when (cmd){
                "등록" -> wiseSayingController.write()
                "목록" -> wiseSayingController.getList(param)
                "삭제" -> wiseSayingController.delete(param)
                "수정" -> wiseSayingController.modify(param)
                "빌드" -> wiseSayingController.build()
                "종료" -> {
                    wiseSayingController.stop()
                    break
                }
            }
        }
    }

    private fun parseCommand(command: String): Pair<String, String> {
        if (command.contains("?")) {
            val split = command.split("?")
            val cmd = split[0]
            val param = split[1]

            return Pair(cmd, param)
        }
        return Pair(command, "")
    }
}

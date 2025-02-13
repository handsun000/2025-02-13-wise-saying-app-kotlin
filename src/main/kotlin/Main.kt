package com.ll

import java.util.Objects
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    println("== 명언 앱 ==")

    var idx = 0;
    val list = mutableListOf<Map<String, Any>>(
        mapOf(
            "id" to 1,
            "content" to "현재를 사랑하라",
            "author" to "작자미상"
        ),
        mapOf(
            "id" to 2,
            "content" to "과거를 사랑하라",
            "author" to "작자미상"
        ),
    )
    idx = list.size

    while (true) {
        print("명령) ")
        val command = scanner.nextLine()
        if ("종료" == command) break;
        else if ("등록" == command) {
            val map = mutableMapOf<String, Any>()
            print("명언 : ")
            map["content"] = scanner.nextLine()
            print("작가 : ")
            map["author"] = scanner.nextLine()
            idx++;
            map["id"] = idx
            list.add(map)
            println("%d번 명언이 등록되었습니다.".format(idx))
        } else if ("목록" == command) {
            println("번호 / 작가 / 명언")
            println("----------------------")
            for (data in list) {
                println("${data["id"]} / ${data["author"]} / ${data["content"]}")
            }
        } else if (command.contains("삭제")) {
            val id = command.substringAfter("id=").toIntOrNull()

            val removedItem = list.find { it["id"] == id }
            if (removedItem != null) {
                list.remove(removedItem)
                println("${id}번 명언이 삭제되었습니다.")
            }
            else {
                println("${id}번 명언은 존재하지 않습니다.")
            }
        }
    }
}
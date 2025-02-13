package com.ll

import java.util.Objects
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    println("== 명언 앱 ==")

    var idx = 0;
    val list = mutableListOf<Map<String, Any>>()

    while(true) {
        print("명령) ")
        val command = scanner.next()
        if ("종료" == command) break;
        else if ("등록" == command) {
            val map = mutableMapOf<String, Any>()
            print("명언 : ")
            map.put("content", scanner.next())
            print("작가 : ")
            map.put("author", scanner.next())
            idx++;
            map.put("id", idx)
            list.add(map)
            println("%d번 명언이 등록되었습니다.".format(idx))
        }
        else if ("목록" == command) {
            println("번호 / 작가 / 명언")
            println("----------------------")
            for (data in list) {
                println("${data["id"]} / ${data["author"]} / ${data["content"]}")
            }
        }
        else if (command.contains("삭제")) {

        }
    }
}
package com.ll

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    println("== 명언 앱 ==")
    var idx = 0;

    while(true) {
        print("명령) ")
        val command = scanner.next()
        if ("종료" == command) break;
        else if ("등록" == command) {
            print("명언 : ")
            scanner.next()
            print("작가 : ")
            scanner.next()
            idx++;
            println("%d번 명언이 등록되었습니다.".format(idx))
        }
    }
}
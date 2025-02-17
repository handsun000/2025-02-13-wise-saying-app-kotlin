package com.ll.domain.wiseSaying.wiseSaying.entity

import com.ll.com.ll.domain.wiseSaying.wiseSaying.`object`.WiseSayings

data class WiseSaying(
    val id: Int,
    var content: String,
    var author: String,
) {
    fun modify(content: String, author: String) {
        this.content = content
        this.author = author
    }
}
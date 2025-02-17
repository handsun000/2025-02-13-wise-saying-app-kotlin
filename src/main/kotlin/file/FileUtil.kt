package com.ll.file

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.ll.com.ll.domain.wiseSaying.wiseSaying.`object`.WiseSayings
import com.ll.domain.wiseSaying.wiseSaying.entity.WiseSaying
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.exists

class FileUtil {

    companion object {
        const val DIRECTORY_PATH = "db/wiseSaying"
    }

    fun <T> saveJson(data: T, filename: String) {
        val jsonString = GsonBuilder()
                            .setPrettyPrinting()
                            .create()
                            .toJson(data)

        val directory = File(DIRECTORY_PATH)
        if (!directory.exists()) directory.mkdirs()

        val filePath = Paths.get(DIRECTORY_PATH, filename)
        Files.write(filePath, jsonString.toByteArray())

        println("File saved to: ${filePath.toAbsolutePath()}")
    }

    fun loadListFromJson(filename: String): List<WiseSaying> {
        return loadFromJson<List<WiseSaying>>(filename) ?: emptyList()
    }

    fun loadLastId(filename: String): Int {
        return loadFromJson<Int>(filename) ?: 0
    }

    fun fileRemove(filename: String): Boolean {
        val filePath = Paths.get(DIRECTORY_PATH, filename)
        return Files.deleteIfExists(filePath)
    }

    fun updateJsonFile(wiseSaying: WiseSaying) {
        val filePath = "${DIRECTORY_PATH}/${wiseSaying.id}.json"

        val jsonString = File(filePath).readText()
        val jsonObject = Gson().fromJson(jsonString, JsonObject::class.java)

        for (wiseSaying in WiseSayings.getList()) {
            jsonObject.addProperty("content", wiseSaying.content)
            jsonObject.addProperty("author", wiseSaying.author)
        }

        File(filePath).writeText(Gson().toJson(jsonObject))
    }

    private fun <T> loadFromJson(filename: String): T? {
        val filePath = Paths.get(DIRECTORY_PATH, filename)
        if (!filePath.exists()) return null;

        val jsonString = Files.readString(filePath)
        val type = object : TypeToken<Int>() {}.type

        return Gson().fromJson(jsonString, type)
    }
}
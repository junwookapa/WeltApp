package com.junwoo.android.welttestapp

import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class APITest {

    var mockWebServer : MockWebServer? = null


    fun readTxt(fileName : String) : String{
        val filePath = "C:/mockTest"
        val reader = BufferedReader(FileReader(File("$filePath/$fileName")))
        val builder = StringBuilder()
        var line: String?
        do{
            line = reader.readLine()
            builder.append(line)
        }while (line != null)
        return builder.toString()
    }

}
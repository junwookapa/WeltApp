package com.junwoo.android.welttestapp

import com.junwoo.android.api.github.vo.Repo
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun ComparatorTest(){
        var mutableList = ArrayList<Repo>()
        var repo: Repo? = Repo()
        repo?.stargazersCount = 3
        mutableList.add(repo!!)
        repo = Repo()
        repo.stargazersCount = 2
        mutableList.add(repo)
        repo = Repo()
        repo.stargazersCount = 1
        mutableList.add(repo)
        mutableList.sort()
        for(r in mutableList){
            System.out.println(r.stargazersCount)
        }


    }
}

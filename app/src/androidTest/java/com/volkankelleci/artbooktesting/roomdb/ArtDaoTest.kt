package com.volkankelleci.artbooktesting.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.volkankelleci.artbooktesting.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class ArtDaoTest(){
    @get:Rule
    var instantTaskExecutorRule=InstantTaskExecutorRule()
    private lateinit var dao: ArtDao
    private lateinit var dataBase:ArtDatabase
    @Before
    fun setUp(){

        dataBase=Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),ArtDatabase::class.java).allowMainThreadQueries().build()
        dao=dataBase.Dao()
    }
    @After
    fun teardown(){
        dataBase.close()

    }
    @Test
    fun insertArtTesting()= runBlockingTest{

        val input=Art("Ahmet","Mehmet",15,"www.bomba.com",1)
        dao.insertArt(input)
        val list=dao.observeArts().getOrAwaitValue()
        assertThat(list).contains(input)

    }
    fun deleteArtTesting()= runBlockingTest{

    }
}
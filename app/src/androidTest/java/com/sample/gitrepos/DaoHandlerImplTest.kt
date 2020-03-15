package com.sample.gitrepos

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sample.gitrepos.models.Timestamp
import com.sample.gitrepos.persistence.DaoHandlerImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class DaoHandlerImplTest: AndroidBaseTest() {

    private val daoHandlerImpl: DaoHandlerImpl by inject()

    @Test
    fun checkForInsertionIntoDB() = runBlocking {
        daoHandlerImpl.addReposDataIntoDB(mutableListOf())
        Assert.assertTrue(daoHandlerImpl.isReposDBEmpty())
        Assert.assertTrue(daoHandlerImpl.getReposDataFromDB().isEmpty())
    }

    @Test
    fun checkForInsertionOfTimestamp() = runBlocking {
        val currentTime = System.currentTimeMillis()
        daoHandlerImpl.addTimeStampInDB(Timestamp(currentTime))
        Assert.assertTrue(daoHandlerImpl.getTimeStampFromDB() != null)
        Assert.assertEquals("The timestamp added to DB is successfully retrieved", currentTime, daoHandlerImpl.getTimeStampFromDB().timesTamp)
    }
}

package com.sample.gitrepos

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.models.Timestamp
import com.sample.gitrepos.persistence.AppDatabase
import com.sample.gitrepos.persistence.DaoHandlerImpl
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject


@RunWith(AndroidJUnit4ClassRunner::class)
class DaoHandlerImplTest: AndroidBaseTest() {

    private val daoHandlerImpl: DaoHandlerImpl by inject()
    private val appDB: AppDatabase by inject()

    @Test
    fun checkForInsertionIntoDBWithEmptyData() = runBlocking {
        daoHandlerImpl.addReposDataIntoDB(mutableListOf())
        Assert.assertTrue(daoHandlerImpl.isReposDBEmpty())
        Assert.assertTrue(daoHandlerImpl.getReposDataFromDB().isEmpty())
    }

    @Test
    fun checkForInsertionIntoDBWithData() = runBlocking {
        val gitReposModel = GitReposModel("author", "", forks = 1, language = "Kotlin", languageColor = "#ddd", name = "Demo", stars = 5, url = "")
        val reposList = mutableListOf(gitReposModel)
        daoHandlerImpl.addReposDataIntoDB(reposList)
        Assert.assertFalse(daoHandlerImpl.isReposDBEmpty())
        Assert.assertTrue(daoHandlerImpl.getReposDataFromDB().isNotEmpty())
        Assert.assertEquals(gitReposModel.author, daoHandlerImpl.getReposDataFromDB()[0].author)
        Assert.assertEquals(gitReposModel.stars, daoHandlerImpl.getReposDataFromDB()[0].stars)
    }

    @Test
    fun checkForInsertionOfTimestamp() = runBlocking {
        val currentTime = System.currentTimeMillis()
        daoHandlerImpl.addTimeStampInDB(Timestamp(currentTime))
        Assert.assertNotNull(daoHandlerImpl.getTimeStampFromDB())
        Assert.assertEquals("The timestamp added to DB is successfully retrieved", currentTime, daoHandlerImpl.getTimeValue())
    }

    @After
    fun afterEach() {
        appDB.close()
    }
}

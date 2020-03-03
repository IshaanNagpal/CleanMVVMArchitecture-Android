package com.sample.gitrepos

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.persistence.DaoHandlerImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class DaoHandlerImplTest: AndroidBaseTest() {

    private val daoHandlerImpl: DaoHandlerImpl by inject()

    @ExperimentalCoroutinesApi
    @Test
    fun checkForInsertionIntoDB() = runBlockingTest {
        daoHandlerImpl.addReposDataIntoDB(mutableListOf())
        Assert.assertTrue(daoHandlerImpl.isReposDBEmpty())
    }


    private fun getList(): MutableList<GitReposModel> {
        return mutableListOf()
    }
}

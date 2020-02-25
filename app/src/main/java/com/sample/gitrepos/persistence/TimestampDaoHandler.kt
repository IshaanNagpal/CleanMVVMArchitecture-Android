package com.sample.gitrepos.persistence

import com.sample.gitrepos.models.Timestamp

interface TimestampDaoHandler {

    suspend fun getTimeStampFromDB(): Timestamp
    suspend fun addTimeStampInDB(timestamp: Timestamp)
}
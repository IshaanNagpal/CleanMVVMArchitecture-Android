package com.sample.gitrepos.utility

import org.koin.core.KoinComponent

interface ListItemModel : KoinComponent {
    fun layoutId(): Int
}
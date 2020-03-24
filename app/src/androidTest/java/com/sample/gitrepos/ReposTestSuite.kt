package com.sample.gitrepos

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    RepoListTestActivity::class, DaoHandlerImplTest::class
)

class ReposTestSuite
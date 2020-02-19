package com.sample.gitrepos.views

import com.sample.gitrepos.R
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.utility.ListItemModel

class ReposItemView(private val reposModel: GitReposModel) : ListItemModel {

    override fun layoutId(): Int {
        return R.layout.repo_list_item
    }
}
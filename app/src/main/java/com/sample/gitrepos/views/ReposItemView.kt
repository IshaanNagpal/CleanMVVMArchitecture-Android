package com.sample.gitrepos.views

import com.sample.gitrepos.R
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.utility.ListItemModel

class ReposItemView(private val reposModel: GitReposModel) : ListItemModel {

    val author = reposModel.author
    val repoName = reposModel.name
    val imageUrl = reposModel.avatar


    override fun layoutId(): Int {
        return R.layout.repo_list_item
    }

}
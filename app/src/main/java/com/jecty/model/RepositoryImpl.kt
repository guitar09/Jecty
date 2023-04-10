package com.jecty.model

class RepositoryImpl(private val api: Api) : Repository {
    override fun getSentence() = api.execute()
}
package com.jecty.model

class UseCaseSentence(private val repository: Repository) {

    operator fun invoke(id: String) = repository.getSentence() + " $id"
}
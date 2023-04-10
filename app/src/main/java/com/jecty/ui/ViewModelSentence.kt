package com.jecty.ui

import androidx.lifecycle.ViewModel
import com.dep.jecty.Jecty.get
import com.jecty.Session
import com.jecty.model.UseCaseSentence
import com.jecty.util.createFactory

class ViewModelSentence(private val useCase: UseCaseSentence) : ViewModel() {

    fun callApi(session: Session) = useCase(session.id)

    companion object {
        val factory = ViewModelSentence(get()).createFactory()
    }
}

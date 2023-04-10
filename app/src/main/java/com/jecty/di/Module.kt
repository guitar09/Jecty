package com.jecty.di

import com.dep.jecty.Jecty.get
import com.dep.jecty.core.JectyModule
import com.dep.jecty.core.JectyQualifier
import com.jecty.Session
import com.jecty.model.Api
import com.jecty.model.ApiImpl
import com.jecty.model.Repository
import com.jecty.model.RepositoryImpl
import com.jecty.model.UseCaseSentence
import kotlin.reflect.KClass

class Module (private val session: Session) : JectyModule<In> {
    override fun configInjectionTree(clazz: In): Any {
        return when (clazz) {
            ApiJect -> ApiImpl()
            RepositoryJect -> RepositoryImpl(get())
            UseCaseJect -> UseCaseSentence(get())
            SessionJect -> session
        }
    }

    override fun clazz(): KClass<In> = In::class
}

sealed class In(override val clazz: KClass<*>, override val named: String? = null) :
    JectyQualifier(clazz, named)

object ApiJect : In(Api::class)
object RepositoryJect : In(Repository::class)
object UseCaseJect : In(UseCaseSentence::class)
object SessionJect : In(Session::class)
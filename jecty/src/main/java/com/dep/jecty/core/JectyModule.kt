package com.dep.jecty.core

import kotlin.reflect.KClass

interface JectyModule<out T: JectyQualifier> {
    fun configInjectionTree(clazz: @UnsafeVariance T) : Any?
    fun clazz() : KClass<@UnsafeVariance T>
}
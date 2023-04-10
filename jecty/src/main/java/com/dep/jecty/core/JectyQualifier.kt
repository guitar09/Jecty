package com.dep.jecty.core

import kotlin.reflect.KClass

open class JectyQualifier(open val clazz: KClass<*>, open val named: String? = null)
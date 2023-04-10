package com.dep.jecty.core

@PublishedApi
internal object JectyProcessor {

    val mockInjection = HashMap<JectyQualifier, Any>()
    private var injectionModule: JectyModule<JectyQualifier>? = null

    fun startInjection(injection: JectyModule<JectyQualifier>) {
        this.injectionModule = injection
    }

    fun <T> getInstance(clazz: JectyQualifier): T {
        if (mockInjection.containsKey(clazz)) return mockInjection[clazz] as T

        return configInjectionTree(clazz) as T
    }


    inline fun <reified T> getIns(qualifier: String? = null): T {
        return getInstance(findClass<T>(qualifier))
    }

    inline fun <reified T> mockTest(mock: Any, qualifier: String? = null) {
        val dep = findClass<T>(qualifier)
        mockInjection[dep] = mock
    }

    fun configInjectionTree(clazz: JectyQualifier): Any {
        val injectClass = inject().configInjectionTree(clazz)

        injectClass?.let {
            return it
        }

        val qualifier = clazz.named?.let { "with qualifier name $it" } ?: ""
        throw IllegalArgumentException("Check your injection tree ${clazz.clazz.qualifiedName ?: ""} $qualifier not found")
    }

    inline fun <reified T> findClass(qualifier: String? = null): JectyQualifier {
        val map = inject().clazz().sealedSubclasses.map {
            it.objectInstance
        }
        val dep = map.first { it?.clazz == T::class && it.named == qualifier }
        dep?.let {
            return it
        }

        throw IllegalArgumentException("Check your injection tree ${T::class.qualifiedName ?: ""} $qualifier not found")
    }

    fun checkTreeJecty() {

        val map = inject().clazz().sealedSubclasses.map {
            it.objectInstance
        }

        map.forEach {
            configInjectionTree(it as JectyQualifier)
        }
    }

    fun inject() = injectionModule
        ?: throw IllegalArgumentException("You need to config startJecty method")
}
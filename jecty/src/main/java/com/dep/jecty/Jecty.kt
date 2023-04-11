package com.dep.jecty

import com.dep.jecty.core.JectyModule
import com.dep.jecty.core.JectyProcessor.checkTreeJecty
import com.dep.jecty.core.JectyProcessor.getIns
import com.dep.jecty.core.JectyProcessor.mockTest
import com.dep.jecty.core.JectyProcessor.startInjection
import com.dep.jecty.core.JectyQualifier

object Jecty {

    fun startJecty(initializer: JectyInitializer.() -> Unit) {
        val config = JectyInitializer()
        config.initializer()

        config.module?.let {
            startInjection(it)
        }

        if (config.checkTree) checkJectyTree()
    }

    inline fun <reified T> get(qualifier: String? = null): T {
        return getIns(qualifier)
    }

    inline fun <reified T> injectMockTest(mock: Any, qualifier: String? = null) {
        mockTest<T>(mock, qualifier)
    }

    fun checkJectyTree() {
        checkTreeJecty()
    }


    class JectyInitializer {
        var module: JectyModule<JectyQualifier>? = null
        var checkTree: Boolean = false
    }

}

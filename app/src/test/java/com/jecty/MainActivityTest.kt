package com.jecty

import android.widget.TextView
import com.dep.jecty.Jecty.injectMockTest
import com.jecty.model.Api
import com.jecty.ui.MainActivity
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    @Test
    fun checkTest() {

        val api : Api = mockk()
        every { api.execute() } returns "API MOCK"
        injectMockTest<Api>(api)
        injectMockTest<Session>(Session("#ID_123"))

        val activity = buildActivity(MainActivity::class.java).create().get()
        assertEquals(activity.findViewById<TextView>(R.id.txtSentence).text.toString(), "API MOCK #ID_123")
    }
}

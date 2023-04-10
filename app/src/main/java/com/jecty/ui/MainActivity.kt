package com.jecty.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.dep.jecty.Jecty.get
import com.jecty.R
import com.jecty.Session

class MainActivity : AppCompatActivity() {

    private val viewModelSentence : ViewModelSentence by viewModels { ViewModelSentence.factory }
    private val session: Session by lazy { get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.txtSentence).text = viewModelSentence.callApi(session)

    }
}

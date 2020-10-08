package com.example.webviewuimodeissue

import android.content.Context
import android.content.res.Configuration
import android.content.res.Configuration.*
import android.util.AttributeSet
import android.webkit.WebView
import androidx.appcompat.app.AppCompatDelegate.*

class FixedWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {

    init {
        fixUiModeIfNeeded()
    }

    private fun fixUiModeIfNeeded() {
        val configuration = context.resources.configuration
        val configurationNighMode = configuration.uiMode and UI_MODE_NIGHT_MASK
        val appCompatNightMode = getDefaultNightMode()

        val newUiModeConfiguration = when {
            configurationNighMode == UI_MODE_NIGHT_NO && appCompatNightMode == MODE_NIGHT_YES -> {
                UI_MODE_NIGHT_YES or (configuration.uiMode and UI_MODE_NIGHT_MASK.inv())
            }
            configurationNighMode == UI_MODE_NIGHT_YES && appCompatNightMode == MODE_NIGHT_NO -> {
                UI_MODE_NIGHT_NO or (configuration.uiMode and UI_MODE_NIGHT_MASK.inv())
            }
            else -> null
        }

        if (newUiModeConfiguration != null) {
            val fixedConfiguration = Configuration().apply {
                uiMode = newUiModeConfiguration
            }
            @Suppress("DEPRECATION")
            context.resources.updateConfiguration(
                fixedConfiguration,
                context.resources.displayMetrics
            )
        }
    }
}
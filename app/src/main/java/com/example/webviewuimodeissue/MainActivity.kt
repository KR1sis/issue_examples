package com.example.webviewuimodeissue

import android.content.res.Configuration
import android.os.Bundle
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val changeThemeButton: TextView
        get() = findViewById(R.id.change_theme_button)

    private val addItemButton: TextView
        get() = findViewById(R.id.add_item_button)

    private val inflateWebView: TextView
        get() = findViewById(R.id.inflate_web_view)

    private val currentAppCompatThemeLabel: TextView
        get() = findViewById(R.id.current_app_compat_theme_label)

    private val currentConfigThemeLabel: TextView
        get() = findViewById(R.id.current_configuration_theme_label)

    private val recyclerView: RecyclerView
        get() = findViewById(R.id.recycler_view)

    private val isNightModeOn: Boolean
        get() = AppCompatDelegate.MODE_NIGHT_YES == AppCompatDelegate.getDefaultNightMode()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.init()

        currentAppCompatThemeLabel.text = "AppCompatTheme ${getAppCompatThemeLabel()}"
        currentConfigThemeLabel.text = "ConfigurationTheme ${getConfigThemeLabel()}"

        changeThemeButton.setOnClickListener {
            toggleNightMode()
        }

        addItemButton.setOnClickListener {
            recyclerView.addItem()
        }

        inflateWebView.setOnClickListener {
            WebView(this)
            currentAppCompatThemeLabel.text = "AppCompatTheme ${getAppCompatThemeLabel()}"
            currentConfigThemeLabel.text = "ConfigurationTheme ${getConfigThemeLabel()}"
        }
    }

    private fun getConfigThemeLabel(): String {
        return when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> "DARK"
            Configuration.UI_MODE_NIGHT_NO -> "LIGHT"
            else -> "UNKNOWN"
        }
    }

    private fun getAppCompatThemeLabel(): String =
            when (AppCompatDelegate.getDefaultNightMode()) {
                AppCompatDelegate.MODE_NIGHT_YES -> "DARK"
                AppCompatDelegate.MODE_NIGHT_NO -> "LIGHT"
                else -> "UNKNOWN"
            }

    private fun toggleNightMode() {
        val appCompatNightMode = if (isNightModeOn) {
            AppCompatDelegate.MODE_NIGHT_NO
        } else {
            AppCompatDelegate.MODE_NIGHT_YES
        }
        AppCompatDelegate.setDefaultNightMode(appCompatNightMode)
        currentAppCompatThemeLabel.text = "AppCompatTheme ${getAppCompatThemeLabel()}"
        currentConfigThemeLabel.text = "ConfigurationTheme ${getConfigThemeLabel()}"
    }

    private fun RecyclerView.init() {
        adapter = CommonAdapter()
        layoutManager = LinearLayoutManager(context)
    }

    private fun RecyclerView.addItem() {
        val commonAdapter = adapter as CommonAdapter
        val currentItems = commonAdapter.items
        val newItems = List(currentItems.size + 1) { it }
        commonAdapter.items = newItems
        commonAdapter.notifyItemInserted(newItems.size - 1)
    }
}


package ai.elimu.common.utils.ui

import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

fun Window.setLightStatusBar() {
    if (Build.VERSION.SDK_INT >= 30) {
        this.insetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
    } else {
        this.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

fun Window.setStatusBarColorCompat(color: Int) {
    if (Build.VERSION.SDK_INT >= 35) {

        this.decorView.setBackgroundColor(ContextCompat.getColor(this.context, color))

        // Apply window insets to avoid overlapping with system bars
        ViewCompat.setOnApplyWindowInsetsListener(
            this.decorView.findViewById(android.R.id.content)
        ) { v, insets ->
            val systemBarsInsets =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                v.getPaddingLeft(),
                systemBarsInsets.top,
                v.getPaddingRight(),
                systemBarsInsets.bottom
            )
            WindowInsetsCompat.CONSUMED
        }
    } else {
        this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        this.statusBarColor = ContextCompat.getColor(this.context, color)
    }
}
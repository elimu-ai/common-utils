package ai.elimu.common.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Log
import androidx.appcompat.app.AlertDialog

fun Context.isPackageInstalled(packageName: String,
                               launchPackage: String,
                               launchClass: String,
                               dialogMessage: String,
                               buttonText: String): Boolean {
    try {
        val packageInfoAppstore: PackageInfo =
            packageManager.getPackageInfo(packageName, 0)
        Log.i("isPackageInstalled", "packageInfoAppstore.versionCode: " + packageInfoAppstore.versionCode)
        return true
    } catch (e: PackageManager.NameNotFoundException) {
        Log.e("isPackageInstalled","getPackageInfo exception: " + e.message)
        AlertDialog.Builder(this)
            .setMessage(dialogMessage)
            .setPositiveButton(buttonText
            ) { _, _ ->
                val openProviderIntent = Intent().apply {
                    setClassName(launchPackage, launchClass)
                }
                try {
                    startActivity(openProviderIntent)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("isPackageInstalled", "startActivity exception: " + e.message)
                }
            }
            .setCancelable(false)
            .create().show()
        return false
    }
}
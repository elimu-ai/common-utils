package ai.elimu.common.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri

/**
 * Checks if a package is installed on the device.
 * If not, open a dialog redirecting users to another app by opening an activity
 * represented by launchPackage and launchClass.
 */
fun Context.ensurePackageInstalledOrPrompt(packageName: String,
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
                val openDestinationApp = Intent().apply {
                    setClassName(launchPackage, launchClass)
                }
                try {
                    startActivity(openDestinationApp)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("isPackageInstalled", "startActivity exception: " + e.message)
                    if (launchClass == "ai.elimu.appstore.MainActivity") {
                        val openDownloadPage = Intent(Intent.ACTION_VIEW,
                            "https://github.com/elimu-ai/appstore/releases".toUri())
                        try {
                            startActivity(openDownloadPage)
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Log.e("isPackageInstalled", "Open AppStore release page exception: " + e.message)
                        }
                    }
                }
            }
            .setCancelable(false)
            .create().show()
        return false
    }
}

fun Context.checkIfAppstoreIsInstalled(appStoreId: String): Boolean {
    try {
        val packageInfoAppstore: PackageInfo =
            packageManager.getPackageInfo(appStoreId, 0)
        Log.i("checkIfAppstoreIsInstalled",
            "packageInfoAppstore.versionCode: " + packageInfoAppstore.versionCode)
        return true
    } catch (e: PackageManager.NameNotFoundException) {
        Log.e("checkIfAppstoreIsInstalled","getPackageInfo exception: " + e.message, e)
        AlertDialog.Builder(this)
            .setMessage(this.getString(R.string.appstore_needed))
            .setPositiveButton(this.getString(R.string.download)
            ) { _, _ ->
                val openDownloadPage = Intent(Intent.ACTION_VIEW,
                    "https://github.com/elimu-ai/appstore/releases".toUri())
                try {
                    startActivity(openDownloadPage)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("checkIfAppstoreIsInstalled", "startActivity exception: " + e.message)
                }
            }
            .setCancelable(false)
            .create().show()
        return false
    }
}

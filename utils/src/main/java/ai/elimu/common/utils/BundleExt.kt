package ai.elimu.common.utils

import android.os.Build
import android.os.Bundle

/**
 * Gets a parcelable object from the Bundle in a way that's compatible with all Android API levels.
 *
 * @param key The key to retrieve the parcelable object.
 * @param clazz The class of the parcelable object.
 * @return The parcelable object, or null if not found.
 */
fun <T> Bundle?.getParcelableCompat(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this?.getParcelable(key, clazz)
    } else {
        @Suppress("DEPRECATION")
        this?.getParcelable(key)
    }
}

/**
 * Gets an ArrayList of parcelable objects from the Bundle in a way that's compatible with all Android API levels.
 *
 * @param key The key to retrieve the ArrayList.
 * @param clazz The class of the parcelable objects in the ArrayList.
 * @return The ArrayList of parcelable objects, or null if not found.
 */
fun <T : android.os.Parcelable> Bundle?.getParcelableArrayListCompat(key: String, clazz: Class<T>)
        : java.util.ArrayList<T>? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this?.getParcelableArrayList(key, clazz)
    } else {
        @Suppress("DEPRECATION")
        this?.getParcelableArrayList(key)
    }
}
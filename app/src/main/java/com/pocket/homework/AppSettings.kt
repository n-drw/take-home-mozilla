package com.pocket.homework

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow

private const val KEY_ITEM_SORT_PREFERENCE = "item_sort_preference"
private const val DEFAULT_SORT_ORDER = "DATE_ASC"

class AppSettings constructor(private val prefs: SharedPreferences) :
    SharedPreferences by prefs {
    constructor(context: Context) : this(
        PreferenceManager.getDefaultSharedPreferences(context)
    )

    fun saveItemOrder(itemSortOrder: ItemSortOrder) {
        prefs.edit()
            .putString(KEY_ITEM_SORT_PREFERENCE, itemSortOrder.name)
            .apply()
    }

    fun getItemOrder() = ItemSortOrder.valueOf(
        prefs.getString(KEY_ITEM_SORT_PREFERENCE, DEFAULT_SORT_ORDER)
            ?: DEFAULT_SORT_ORDER
    )

//    fun observe() = callbackFlow<String> {
//        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
//            trySendBlocking(key)
//        }
//        prefs.unregisterOnSharedPreferenceChangeListener(listener)
//        awaitClose {
//            prefs.unregisterOnSharedPreferenceChangeListener(listener)
//        }
//    }

//    companion object {
//        const val DATE_ORDER_KEY = "date_order"
//        const val TITLE_ORDER_KEY = "alpha_order"
//    }
}
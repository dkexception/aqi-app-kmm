package io.github.dkexception.kmm.aqiapp.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import io.github.dkexception.kmm.aqiapp.getPlatform
import kotlinx.coroutines.flow.firstOrNull

interface IPreferencesHelper {

    suspend fun putInt(key: String, value: Int)

    suspend fun getInt(key: String, default: Int): Int

    suspend fun putString(key: String, value: String)

    suspend fun getString(key: String): String?

    suspend fun putBool(key: String, value: Boolean)

    suspend fun getBool(key: String, default: Boolean): Boolean

    suspend fun nuke()
}

internal class PreferencesHelper : IPreferencesHelper {

    private val prefs: DataStore<Preferences> by lazy {
        getPlatform().getPrefsDataStore()
    }

    override suspend fun putInt(key: String, value: Int) {
        prefs.edit {
            val intKey = intPreferencesKey(key)
            it[intKey] = value
        }
    }

    override suspend fun getInt(key: String, default: Int): Int {
        val intKey = intPreferencesKey(key)
        return prefs.data.firstOrNull()?.get(intKey) ?: default
    }

    override suspend fun putString(key: String, value: String) {
        prefs.edit {
            val stringKey = stringPreferencesKey(key)
            it[stringKey] = value
        }
    }

    override suspend fun getString(key: String): String? {
        val stringKey = stringPreferencesKey(key)
        return prefs.data.firstOrNull()?.get(stringKey)
    }

    override suspend fun putBool(key: String, value: Boolean) {
        prefs.edit {
            val boolKey = booleanPreferencesKey(key)
            it[boolKey] = value
        }
    }

    override suspend fun getBool(key: String, default: Boolean): Boolean {
        val boolKey = booleanPreferencesKey(key)
        return prefs.data.firstOrNull()?.get(boolKey) ?: default
    }

    override suspend fun nuke() {
        prefs.edit {
            it.clear()
        }
    }
}

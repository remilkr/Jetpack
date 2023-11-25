package com.sample.sampledemo.repository.local.roomdb.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.sample.sampledemo.BuildConfig
import com.sample.sampledemo.model.HomeScreenApiResponseModel

class AppPreference {
    private object HOLDER {
        val INSTANCE = AppPreference()
    }

    companion object {
        val shared: AppPreference by lazy { HOLDER.INSTANCE }
        private const val PREFS_FILE_NAME = BuildConfig.APPLICATION_ID + ".preferences"
    }
    //endregion

    private object Key {
        const val HOME_SCREEN_DATA = "homeData"

    }

    private lateinit var sharedPreferences: SharedPreferences

    //region public method
    fun initWith(context: Context) {
        //shared preference encryption
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        sharedPreferences = EncryptedSharedPreferences.create(
            context,
            PREFS_FILE_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    var homeScreenData: HomeScreenApiResponseModel?
        get() = sharedPreferences.getString(Key.HOME_SCREEN_DATA, null)?.let {
            Gson().fromJson(it, HomeScreenApiResponseModel::class.java)
        }
        set(value) {
            val json = value?.let { Gson().toJson(value) }
            sharedPreferences.edit().putString(Key.HOME_SCREEN_DATA, json).apply()
        }

}
package com.example.geotag.data.okhttp

import android.content.Context
import android.content.SharedPreferences
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class PersistentCookieJar(context: Context) : CookieJar {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("CookiePrefs", Context.MODE_PRIVATE)

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val domainCookies = sharedPreferences.getStringSet(url.host, mutableSetOf()) ?: mutableSetOf()
        domainCookies.addAll(cookies.map { it.toString() })
        sharedPreferences.edit().putStringSet(url.host, domainCookies).apply()
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val domainCookies = sharedPreferences.getStringSet(url.host, emptySet()) ?: emptySet()
        return domainCookies.map { Cookie.parse(url, it) }.filterNotNull()
    }
}
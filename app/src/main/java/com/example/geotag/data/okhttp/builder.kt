package com.example.geotag.data.okhttp

import okhttp3.OkHttpClient

val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    .cookieJar(MyCookieJar())
    .build()

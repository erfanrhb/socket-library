package com.palizpars

import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

class PalizparsSocket {
    companion object {
        fun getInstance(baseUrl: String, token: String?): PalizparsSocketService {
            val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(5, TimeUnit.SECONDS)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .callTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        val request: Request = chain.request()
                        val requestBuilder: Request.Builder = request.newBuilder()
                        if (request.header("VERIFIED_ACCESS_TOKEN") == null) { // needs credentials
                            if (token != null) {
                                requestBuilder.addHeader("Authorization", "Bearer $token")
                            }
                        }
                        chain.withReadTimeout(6000, TimeUnit.MILLISECONDS)
                        chain.proceed(requestBuilder.build())
                    }
                    .build()
            val scarlet = Scarlet.Builder()
                    .webSocketFactory(okHttpClient.newWebSocketFactory(baseUrl))
                    .addMessageAdapterFactory(GsonMessageAdapter.Factory())
                    .addStreamAdapterFactory(RxJava2StreamAdapterFactory())
                    .build()
            return scarlet.create()
        }
    }
}
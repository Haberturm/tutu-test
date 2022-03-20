package com.haberturm.tutuinternship.data.network.okhttpUtil

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException
import javax.inject.Inject

class InternetConnectionInterceptor @Inject constructor (private val mContext: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtil.isOnline(mContext)) {
            throw NoInternet()
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

}


class NoInternet : IOException() {

    override val message: String
        get() {
            return "No connectivity exception"
        }

}

class NetworkUtil {
    companion object {
        fun isOnline(context: Context): Boolean {
            var result = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
            return result
        }
    }
}
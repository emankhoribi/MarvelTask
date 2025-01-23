package com.digi.marveltask.util

import android.R.id.input
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp


object Constants {

    const val API_KEY = "6e8bc68f7cc7fa0fe666464d5148749d"
    const val PRIVATE_KEY = "9aec6210d2e4acc2db314d83daf7a2a062adf64c"
    const val API_BASE = "https://gateway.marvel.com:443/v1/public/"
    val TS = Timestamp(System.currentTimeMillis()).time.toString()
    fun hash(): String {
        val data = TS.plus(PRIVATE_KEY).plus(API_KEY)
        val mdHash = MessageDigest.getInstance("MD5")
        val messageDigest: ByteArray = mdHash.digest(data.toByteArray())
        return BigInteger(1, messageDigest).toString(16).padStart(3)
    }
}
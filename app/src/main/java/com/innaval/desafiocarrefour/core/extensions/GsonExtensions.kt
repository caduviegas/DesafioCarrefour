package com.innaval.desafiocarrefour.core.extensions

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.lang.reflect.Modifier

inline fun <reified T> Gson.fromJson(json: String): T {
    return this.fromJson(json, object : TypeToken<T>() {}.type)
}

inline fun <reified T> JSONObject.toObject(type: Class<T>): T? =
    try {
        Gson().fromJson(this.toString(), type)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

fun Any.toJsonString(): String? = Gson().toJson(this)

fun Any.toJsonStringStatic(): String? = GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create().toJson(this)

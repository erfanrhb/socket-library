package com.palizpars

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName


class PalizparsBaseSocketResponseModel {
    @SerializedName("command")
    var command: PalizparsSocketCommands? = null

    @SerializedName("name")
    var name: String = ""

    @SerializedName("uuid")
    var uuid: String? = null

    @SerializedName("params")
    var params: Any? = null

    inline fun <reified T : Any> getCastedParams(): T {
        val gson = Gson()
        return gson.fromJson(gson.toJson(params), T::class.java)
    }
}
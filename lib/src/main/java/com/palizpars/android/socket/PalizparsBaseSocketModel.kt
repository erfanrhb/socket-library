package com.palizpars

import com.google.gson.annotations.SerializedName

class PalizparsBaseSocketModel<T> {
    @SerializedName("command")
    var command: PalizparsSocketCommands? = null

    @SerializedName("name")
    var name: String = ""

    @SerializedName("uuid")
    var uuid: String? = null

    @SerializedName("params")
    var params: T? = null
}

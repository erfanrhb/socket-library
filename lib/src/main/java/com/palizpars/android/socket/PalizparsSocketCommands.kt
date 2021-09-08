package com.palizpars

enum class PalizparsSocketCommands(private val text: String) {
    ACTION("ACTION"),
    REQUEST("REQUEST"),
    RESPONSE("RESPONSE"),
    EVENT("EVENT");

    override fun toString(): String {
        return this.text
    }
}
package io.intrepid.koroutines.utils

sealed class LogLevel {
    object VERBOSE : LogLevel()
    object DEBUG : LogLevel()
    object INFO : LogLevel()
    object WARN : LogLevel()
    object ERROR : LogLevel()
    object ASSERT : LogLevel()
    object WTF : LogLevel()
}
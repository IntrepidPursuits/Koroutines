package io.intrepid.koroutines.utils

import android.annotation.SuppressLint
import android.util.Log
import io.intrepid.koroutines.utils.LogLevel.*
import timber.log.Timber

/**
 * Utility for logging formatted messages with Timber.
 */
object LogUtils {

    fun formatLogTag(anyValue: Any?): String {
        return String.format("%s@%s", anyValue?.javaClass?.simpleName ?: "Null", Integer.toHexString(anyValue?.hashCode() ?: 0))
    }

    /**
     * This function helps objects record a verbose log with its corresponding class' simple name tag and hashcode.
     * 08-22 19:16:53.359 V/SimpleName@53eb0aa: message.
     *
     * @param anyValue  the anyValue logged by this function
     * @param message to format and log
     */
    fun logVerboseMessage(anyValue: Any,
                          message: String?): String {
        //Java needs an explicit cast for the varargs array
        return logFormattedMessage(VERBOSE, anyValue, message)
    }

    /**
     * This function helps objects record a log with its corresponding class' simple name tag and hashcode.
     * 08-22 19:16:53.359 V/SimpleName@53eb0aa: message.
     *
     * @param anyValue  the anyValue logged by this function
     * @param message to format and log
     */
    @SuppressLint("VisibleForTests")
    private fun logFormattedMessage(level: LogLevel,
                                    anyValue: Any,
                                    message: String?): String {
        val tag = formatLogTag(anyValue)
        LogHelper.log(level, tag, message)
        val fullMessage = if (message == null) "" else String.format(message)
        return String.format("%s %s", tag, fullMessage)
    }

    /**
     * Switch statement.
     */
    private object LogHelper {

        internal fun log(level: LogLevel, tag: String, message: String?, vararg args: Any) {
            val tree = Timber.tag(tag)
            when (level) {
                DEBUG -> tree.d(message, args)
                ERROR -> tree.e(message, args)
                INFO -> tree.i(message, args)
                VERBOSE -> tree.v(message, args)
                WARN -> tree.w(message, args)
                LogLevel.ASSERT -> tree.log(Log.ASSERT, message, args)
                WTF -> tree.wtf(message, args)
            }
        }
    }
}

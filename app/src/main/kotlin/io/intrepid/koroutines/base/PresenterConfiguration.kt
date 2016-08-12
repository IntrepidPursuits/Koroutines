package io.intrepid.koroutines.base

import io.intrepid.koroutines.rest.RestApi
import io.intrepid.koroutines.settings.UserSettings
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Wrapper class for common dependencies that all presenters are expected to have
 */
open class PresenterConfiguration(
        val ioContext: CoroutineContext,
        val uiContext: CoroutineContext,
        val userSettings: UserSettings,
        val restApi: RestApi)

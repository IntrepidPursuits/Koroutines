package io.intrepid.koroutines.app.base

import io.intrepid.koroutines.api.rest.RestApi
import io.intrepid.koroutines.app.base.logging.CrashReporter
import io.intrepid.koroutines.app.base.settings.UserSettings
import io.reactivex.Scheduler

/**
 * Wrapper class for common dependencies that all presenters are expected to have
 */
open class PresenterConfiguration(open val ioScheduler: Scheduler,
                                  open val uiScheduler: Scheduler,
                                  val userSettings: UserSettings,
                                  val restApi: RestApi,
                                  val crashReporter: CrashReporter)

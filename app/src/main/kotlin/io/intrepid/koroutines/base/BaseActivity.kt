package io.intrepid.koroutines.base

import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import io.intrepid.koroutines.KoroutinesApplication
import io.intrepid.koroutines.utils.ButterKnife
import io.intrepid.koroutines.utils.LogUtils.logVerboseMessage
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

@Suppress("AddVarianceModifier")
abstract class BaseActivity<S : BaseScreen, P : BasePresenter<S>> : AppCompatActivity() {
    private val koroutinesApplication: KoroutinesApplication get() = application as KoroutinesApplication

    protected abstract val layoutResourceId: Int
    protected val presenter: P by lazy {
        createPresenter(koroutinesApplication.getPresenterConfiguration()).also { lifecycle.addObserver(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        logVerboseMessage(this, "Lifecycle onCreate")
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)

        onViewCreated(savedInstanceState)
        setLightStatusBar()
    }

    abstract fun createPresenter(configuration: PresenterConfiguration): P

    /**
     * Override this method to do any additional screen initialization (ex: setup RecycleView adapter)
     */
    protected open fun onViewCreated(savedInstanceState: Bundle?) {

    }

    @CallSuper
    override fun onNewIntent(intent: Intent?) {
        logVerboseMessage(this, "Lifecycle onNewIntent")
        super.onNewIntent(intent)
    }

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        launch(UI) { logVerboseMessage(this, "Lifecycle onActivityResult") }
        super.onActivityResult(requestCode, resultCode, data)
    }

    @CallSuper
    override fun onStart() {
        logVerboseMessage(this, "Lifecycle onStart")
        super.onStart()
    }

    @CallSuper
    override fun onResume() {
        logVerboseMessage(this, "Lifecycle onResume")
        super.onResume()
    }

    @CallSuper
    override fun onPause() {
        logVerboseMessage(this, "Lifecycle onPause")
        super.onPause()
    }

    @CallSuper
    override fun onStop() {
        logVerboseMessage(this, "Lifecycle onStop")
        super.onStop()
    }

    @CallSuper
    override fun onDestroy() {
        logVerboseMessage(this, "Lifecycle onDestroy")
        ButterKnife.unbind(this)
        super.onDestroy()
    }

    private fun setLightStatusBar() {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}

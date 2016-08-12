package io.intrepid.koroutines.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.intrepid.koroutines.KoroutinesApplication
import io.intrepid.koroutines.utils.ButterKnife
import io.intrepid.koroutines.utils.LogUtils.formatLogTag
import io.intrepid.koroutines.utils.LogUtils.logVerboseMessage
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

@Suppress("AddVarianceModifier")
abstract class BaseFragment<S : BaseScreen, P : BasePresenter<S>> : Fragment() {

    private val koroutinesApplication: KoroutinesApplication get() = activity.application as KoroutinesApplication
    protected val presenter: P by lazy {
        createPresenter(koroutinesApplication.getPresenterConfiguration()).also { lifecycle.addObserver(it) }
    }
    protected abstract val layoutResourceId: Int

    @CallSuper
    override fun onAttach(context: Context?) {
        logVerboseMessage(this, "Lifecycle onAttach to ${formatLogTag(context)}")
        super.onAttach(context)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        logVerboseMessage(this, "Lifecycle onCreate")
        super.onCreate(savedInstanceState)
    }

    /**
     * Override [.onViewCreated] to handle any logic that needs to occur right after inflating the screen.
     * onViewCreated is called immediately after onCreateView
     */
    override final fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logVerboseMessage(this, "Lifecycle onCreateView")
        return inflater.inflate(layoutResourceId, container, false)
    }

    override final fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(savedInstanceState)
    }

    /**
     * Override this method to do any additional screen initialization (ex: setup RecyclerView adapter)
     */
    protected open fun onViewCreated(savedInstanceState: Bundle?) {
    }

    abstract fun createPresenter(configuration: PresenterConfiguration): P

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        launch(UI) { logVerboseMessage(this, "Lifecycle onStart") }
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
    override fun onDestroyView() {
        logVerboseMessage(this, "Lifecycle onDestroyView")
        super.onDestroyView()
        ButterKnife.unbind(this)
    }

    @CallSuper
    override fun onDestroy() {
        logVerboseMessage(this, "Lifecycle onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        logVerboseMessage(this, "Lifecycle onDetach from ${formatLogTag(context)}")
        super.onDetach()
    }

    inline fun <reified T : Activity> closeThenAlphaToActivity(): Unit =
            let {
                startActivity(Intent(activity, T::class.java))
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                activity.finish()
            }
}

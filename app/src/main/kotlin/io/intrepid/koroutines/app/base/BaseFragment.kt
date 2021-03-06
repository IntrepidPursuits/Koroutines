package io.intrepid.koroutines.app.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.intrepid.koroutines.app.KoroutinesApplication
import io.intrepid.koroutines.utils.ButterKnife
import timber.log.Timber

abstract class BaseFragment<A : BaseContract.Presenter, F : BaseContract.Presenter> : Fragment(), BaseContract.View {
    protected val koroutinesApplication: KoroutinesApplication
        get() = activity.application as KoroutinesApplication
    protected abstract val layoutResourceId: Int

    protected lateinit var parentPresenter: A
    protected lateinit var presenter: F

    @CallSuper
    override fun onAttach(context: Context?) {
        Timber.v("Lifecycle onAttach: $this to $context")
        super.onAttach(context)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.v("Lifecycle onCreate: $this")
        super.onCreate(savedInstanceState)
        val configuration = koroutinesApplication.presenterConfiguration()
        presenter = createPresenter(configuration)
    }

    /**
     * Override [.onViewCreated] to handle any logic that needs to occur right after inflating the view.
     * onViewCreated is called immediately after onCreateView
     */
    override final fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.v("Lifecycle onCreateView: $this")
        val view = inflater.inflate(layoutResourceId, container, false)
        return view
    }

    override final fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(savedInstanceState)
        presenter.onViewCreated()
    }

    /**
     * Override this method to do any additional view initialization (ex: setup RecyclerView adapter)
     */
    protected open fun onViewCreated(savedInstanceState: Bundle?) {
    }

    abstract fun createPresenter(configuration: PresenterConfiguration): F

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.bindView(this)
    }

    @CallSuper
    override fun onStart() {
        Timber.v("Lifecycle onStart: " + this)
        super.onStart()
        presenter.bindView(this)
    }

    @CallSuper
    override fun onResume() {
        Timber.v("Lifecycle onResume: $this")
        super.onResume()
    }

    @CallSuper
    override fun onPause() {
        Timber.v("Lifecycle onPause: $this")
        super.onPause()
    }

    @CallSuper
    override fun onStop() {
        Timber.v("Lifecycle onStop: $this")
        super.onStop()
        presenter.unbindView()
    }

    @CallSuper
    override fun onDestroyView() {
        Timber.v("Lifecycle onDestroyView: $this")
        ButterKnife.unbind(this)
        super.onDestroyView()
    }

    @CallSuper
    override fun onDestroy() {
        Timber.v("Lifecycle onDestroy: $this")
        presenter.onViewDestroyed()
        super.onDestroy()
    }

    override fun onDetach() {
        Timber.v("Lifecycle onDetach: $this from $context")
        super.onDetach()
    }

    override fun showProgressDialog(messageResource: Int) {
        (activity as BaseContract.View).showProgressDialog(messageResource)
    }

    override fun hideProgressDialog() {
        (activity as BaseContract.View).hideProgressDialog()
    }

    override fun showToast(messageResource: Int) {
        (activity as BaseContract.View).showToast(messageResource)
    }
}

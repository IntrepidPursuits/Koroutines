package io.intrepid.koroutines.app.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import io.intrepid.koroutines.app.KoroutinesApplication
import io.intrepid.koroutines.utils.ButterKnife
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity(), BaseContract.View  {
    private val progressDialog :ProgressDialog by lazy { ProgressDialog(this) }
    protected abstract val layoutResourceId: Int

    protected val koroutinesApplication: KoroutinesApplication
        get() = application as KoroutinesApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.v("Lifecycle onCreate: $this")
        super.onCreate(savedInstanceState)
        if(layoutResourceId != 0) {
            setContentView(layoutResourceId)
        }
    }

    @CallSuper
    override fun onNewIntent(intent: Intent?) {
        Timber.v("Lifecycle onNewIntent: $this")
        super.onNewIntent(intent)
    }

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Timber.v("Lifecycle onActivityResult: $this")
        super.onActivityResult(requestCode, resultCode, data)
    }

    @CallSuper
    override fun onStart() {
        Timber.v("Lifecycle onStart: $this")
        super.onStart()
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
    }

    @CallSuper
    override fun onDestroy() {
        Timber.v("Lifecycle onDestroy: $this")
        ButterKnife.unbind(this)
        super.onDestroy()
    }

    override fun showProgressDialog(@StringRes messageResource: Int) {
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.setMessage(getString(messageResource))
        progressDialog.show()
    }

    override fun hideProgressDialog() {
        progressDialog.dismiss()
    }

    override fun showToast(@StringRes messageResource: Int) {
        koroutinesApplication.showToast(messageResource)
    }
}

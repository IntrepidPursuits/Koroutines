package io.intrepid.koroutines.screens.animation

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import io.intrepid.koroutines.R
import io.intrepid.koroutines.base.BaseActivity
import io.intrepid.koroutines.base.BaseScreen
import io.intrepid.koroutines.base.EmptyPresenter
import io.intrepid.koroutines.base.PresenterConfiguration
import io.intrepid.koroutines.utils.ButterKnife.bindView

class AnimationActivity : BaseActivity<BaseScreen, EmptyPresenter>(), BaseScreen {
    private val animationView: AnimationView by bindView(R.id.activity_animation_animation_view)
    private val addButton: FloatingActionButton by bindView(R.id.activity_animation_add_button)
    private val removeButton: FloatingActionButton by bindView(R.id.activity_animation_remove_button)
    private val animationModel: AnimationModel by lazy { applyAnimationModel(this, animationView) }

    override val layoutResourceId: Int = R.layout.activity_animation

    override fun createPresenter(configuration: PresenterConfiguration): EmptyPresenter {
        return EmptyPresenter(this, configuration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        addButton.setOnClickListener {
            animationModel.addAnimation()
        }
        removeButton.setOnClickListener {
            animationModel.clearAnimations()
        }
    }

    private fun applyAnimationModel(lifecycleOwner: LifecycleOwner, view: Observer<Set<AnimatedShape>>): AnimationModel {
        return ViewModelProviders.of(this)
                .get(AnimationModel::class.java)
                .apply { observe(lifecycleOwner, view) }
    }
}
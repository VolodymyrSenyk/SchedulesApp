package com.senykvolodymyr.core.base.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.senykvolodymyr.core.R
import com.senykvolodymyr.core.base.viewmodel.BaseViewModel
import com.senykvolodymyr.core.factory.ViewModelFactory
import com.senykvolodymyr.core.livedata.event.navigation.NavigateToActivityEvent
import com.senykvolodymyr.core.livedata.event.navigation.NavigationEvent
import com.senykvolodymyr.core.livedata.event.navigation.StartActivityEvent
import com.senykvolodymyr.core.livedata.event.navigation.StartActivityForResultEvent
import com.senykvolodymyr.core.livedata.event.navigation.StartServiceEvent
import com.senykvolodymyr.core.util.ShowCustomToastUtil
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding> : DaggerAppCompatActivity() {

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected lateinit var binding: B

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var toolbar: Toolbar? = null
    var customToastView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
        setUpToolbar()
    }

    @CallSuper
    protected open fun setUpToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar?.let { setSupportActionBar(it) }
    }

    protected open fun setBaseObservers(viewModel: BaseViewModel) {
        viewModel.toastMessage.observe(this, { showToast(it) })
        viewModel.navigationEvent.observe(this, {
            handleNavigationEvent(it.handleEvent())
        })
    }

    protected open fun handleNavigationEvent(event: NavigationEvent?) {
        when (event) {
            is NavigateToActivityEvent -> {
                startActivity(Intent(this, event.destination))
                if (event.finishCurrentActivity) finish()
            }

            is StartActivityForResultEvent -> {
                startActivityForResult(event.intent, event.requestCode)
            }

            is StartActivityEvent -> {
                startActivity(event.intent)
            }

            is StartServiceEvent -> {
                startService(event.intent)
            }
        }
    }

    protected open fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    fun showCustomToast(
        message: String,
        duration: ShowCustomToastUtil.ToastDuration = ShowCustomToastUtil.ToastDuration.SHORT
    ) {
        customToastView?.let { ShowCustomToastUtil(it, message, duration) }
    }
}

package com.senyk.volodymyr.schedulesapp.presentation.core.base.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.presentation.core.base.viewmodel.BaseViewModel
import com.senyk.volodymyr.schedulesapp.presentation.core.factory.ViewModelFactory
import com.senyk.volodymyr.schedulesapp.presentation.core.livedata.event.navigation.*
import com.senyk.volodymyr.schedulesapp.presentation.core.util.ShowCustomToastUtil
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @get:LayoutRes
    protected abstract val layoutRes: Int

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var toolbar: Toolbar? = null
    var customToastView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
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

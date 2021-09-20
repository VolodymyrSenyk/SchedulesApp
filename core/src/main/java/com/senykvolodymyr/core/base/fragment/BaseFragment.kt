package com.senykvolodymyr.core.base.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.senykvolodymyr.core.R
import com.senykvolodymyr.core.base.activity.BaseActivity
import com.senykvolodymyr.core.base.viewmodel.BaseViewModel
import com.senykvolodymyr.core.entity.MessageWithAction
import com.senykvolodymyr.core.extensions.hideKeyboard
import com.senykvolodymyr.core.extensions.requestKey
import com.senykvolodymyr.core.extensions.setFragmentResultListener
import com.senykvolodymyr.core.factory.ViewModelFactory
import com.senykvolodymyr.core.livedata.event.navigation.NavigateBackEvent
import com.senykvolodymyr.core.livedata.event.navigation.NavigateBackToFragmentEvent
import com.senykvolodymyr.core.livedata.event.navigation.NavigateToActivityEvent
import com.senykvolodymyr.core.livedata.event.navigation.NavigateToFragmentEvent
import com.senykvolodymyr.core.livedata.event.navigation.NavigationEvent
import com.senykvolodymyr.core.livedata.event.navigation.RequestPermissionsEvent
import com.senykvolodymyr.core.livedata.event.navigation.StartActivityEvent
import com.senykvolodymyr.core.livedata.event.navigation.StartActivityForResultEvent
import com.senykvolodymyr.core.livedata.event.navigation.StartServiceEvent
import com.senykvolodymyr.core.util.ShowCustomToastUtil
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @get:LayoutRes
    protected abstract val layoutRes: Int

    @get:MenuRes
    protected open val menuRes: Int? = null

    protected abstract val viewModel: BaseViewModel

    protected lateinit var binding: B

    private var toolbar: Toolbar? = null

    private var snackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(view)
        setHasOptionsMenu(menuRes != null)
        setBaseObservers(viewModel)
    }

    @CallSuper
    protected open fun setUpToolbar(view: View) {
        toolbar = view.findViewById(R.id.toolbar)
        toolbar?.apply {
            (requireActivity() as? AppCompatActivity)?.setSupportActionBar(this)
            setNavigationOnClickListener { findNavController().popBackStack() }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menuRes?.let { inflater.inflate(it, menu) }
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }

    protected open fun setBaseObservers(viewModel: BaseViewModel) {
        viewModel.toastMessage.observe(viewLifecycleOwner, { showCustomToast(it) })
        viewModel.dialogFragment.observe(viewLifecycleOwner, { showDialogFragment(it) })
        viewModel.navigationEvent.observe(viewLifecycleOwner, {
            handleNavigationEvent(it.handleEvent())
        })
    }

    protected open fun handleNavigationEvent(event: NavigationEvent?) {
        when (event) {

            is NavigateToActivityEvent -> {
                startActivity(Intent(requireActivity(), event.destination))
                if (event.finishCurrentActivity) requireActivity().finish()
            }

            is StartActivityForResultEvent -> {
                startActivityForResult(event.intent, event.requestCode)
            }

            is RequestPermissionsEvent -> {
                requestPermissions(event.permissions.toTypedArray(), event.requestCode)
            }

            is StartActivityEvent -> {
                requireActivity().startActivity(event.intent)
            }

            is StartServiceEvent -> {
                requireActivity().startService(event.intent)
            }

            is NavigateToFragmentEvent -> {
                findNavController().navigate(event.action)
            }

            is NavigateBackEvent -> {
                findNavController().popBackStack()
            }

            is NavigateBackToFragmentEvent -> {
                findNavController().popBackStack(event.destination, false)
            }
        }
    }

    protected open fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected open fun showCustomToast(
        message: String,
        duration: ShowCustomToastUtil.ToastDuration = ShowCustomToastUtil.ToastDuration.SHORT
    ) {
        (requireActivity() as? BaseActivity<*>)?.showCustomToast(message, duration)
    }

    protected open fun showSnackbar(message: String) {
        view?.let { view ->
            snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG).apply {
                duration = BaseTransientBottomBar.LENGTH_INDEFINITE
            }
            snackbar?.show()
        }
    }

    protected open fun showSnackbarWithAction(data: MessageWithAction) {
        view?.let { view ->
            snackbar = Snackbar.make(view, data.text, Snackbar.LENGTH_INDEFINITE)
                .setAction(data.actionName, data.action)
            snackbar?.show()
        }
    }

    protected open fun hideSnackbar() {
        snackbar?.dismiss()
    }

    protected open fun showDialogFragment(dialogFragment: DialogFragment) {
        val requestKey = dialogFragment.requestKey()
        setFragmentResultListener(requestKey) { viewModel.onDialogResultReceived(it) }
        dialogFragment.show(childFragmentManager, requestKey)
    }
}

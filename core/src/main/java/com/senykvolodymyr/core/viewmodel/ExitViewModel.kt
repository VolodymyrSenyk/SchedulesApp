package com.senykvolodymyr.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.senykvolodymyr.core.R
import com.senykvolodymyr.core.base.viewmodel.BaseViewModel
import com.senykvolodymyr.core.livedata.SingleEventLiveData
import com.senykvolodymyr.core.provider.ResourcesProvider
import com.senykvolodymyr.core.util.ShowCustomToastUtil
import javax.inject.Inject

open class ExitViewModel @Inject constructor(
    protected val resourcesProvider: ResourcesProvider
) : BaseViewModel() {

    private var lastBackButtonPress = System.currentTimeMillis()

    private val _exit = SingleEventLiveData<Boolean>()
    val exit: LiveData<Boolean>
        get() = _exit

    fun onBackPressed(navController: NavController) {
        val current = navController.currentDestination?.id
        val start = navController.graph.startDestination
        if (start == current) {
            if (System.currentTimeMillis() - lastBackButtonPress < BACK_BUTTON_PRESS_TIMEOUT) {
                _exit.value = true
            } else {
                lastBackButtonPress = System.currentTimeMillis()
                _toastMessage.value = resourcesProvider.getString(
                    R.string.message_press_one_more_time_to_exit
                )
            }
        } else {
            _exit.value = false
        }
    }

    companion object {
        private val BACK_BUTTON_PRESS_TIMEOUT =
            ShowCustomToastUtil.ToastDuration.SHORT.timeInMillis * 1.5
    }
}

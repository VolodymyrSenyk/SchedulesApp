package com.senyk.volodymyr.schedulesapp.presentation.feature.schedules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.senyk.volodymyr.schedulesapp.domain.entity.DayType
import com.senyk.volodymyr.schedulesapp.domain.entity.Pair
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekType
import com.senyk.volodymyr.schedulesapp.domain.repository.ScheduleRepository
import com.senyk.volodymyr.schedulesapp.presentation.core.base.viewmodel.BaseRxViewModel
import com.senyk.volodymyr.schedulesapp.presentation.core.provider.ResourcesProvider
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity.PairUi
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity.toPair
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity.toPairUi
import javax.inject.Inject

class OneDayScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
    private val resourcesProvider: ResourcesProvider
) : BaseRxViewModel() {

    private lateinit var scheduleId: String
    private lateinit var weekType: WeekType
    private lateinit var dayType: DayType

    private val _pairs = MutableLiveData<List<PairUi>>()
    val pairs: LiveData<List<PairUi>>
        get() = _pairs

    private val _editablePairs = MutableLiveData<List<PairUi>>()
    val editablePairs: LiveData<List<PairUi>>
        get() = _editablePairs

    private val _isEditMode = MutableLiveData(false)
    val isEditMode: LiveData<Boolean>
        get() = _isEditMode

    fun onStart(scheduleId: String, weekType: WeekType, dayType: DayType) {
        this.scheduleId = scheduleId
        this.weekType = weekType
        this.dayType = dayType
        loadPairs()
    }

    fun onPairClick(pair: PairUi) {
    //    _dialogFragment.value =
    }

    fun onEditModeClick() {
        _isEditMode.value = true
        _editablePairs.value = pairs.value?.toList()
    }

    fun onAddClick() {
        _editablePairs.value = editablePairs.value?.toMutableList()?.apply {
            add(PairUi())
        }?.toList()
    }

    fun onDeleteClick(pair: PairUi) {
        _editablePairs.value = editablePairs.value?.toMutableList()?.apply {
            remove(pair)
        }?.toList()
    }

    fun onSaveClick() {
        subscribe(
            upstream = scheduleRepository.updateScheduleForOneDay(
                scheduleId,
                weekType,
                dayType,
                editablePairs.value!!.map { it.toPair(resourcesProvider) }
            ),
            onSuccess = {
                setPairs(it)
                onCancelClick()
            }
        )
    }

    fun onCancelClick() {
        _editablePairs.value = emptyList()
        _isEditMode.value = false
    }

    private fun loadPairs() {
        subscribeWithProgress(
            upstream = scheduleRepository.getScheduleForOneDay(scheduleId, weekType, dayType),
            onSuccess = { setPairs(it) }
        )
    }

    private fun setPairs(pairs: List<Pair>) {
        if (pairs.isEmpty()) {
            // empty state
        } else {
            _pairs.value = pairs.map { it.toPairUi(resourcesProvider) }
        }
    }
}

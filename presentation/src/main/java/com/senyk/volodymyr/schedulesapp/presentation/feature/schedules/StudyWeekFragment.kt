package com.senyk.volodymyr.schedulesapp.presentation.feature.schedules

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.databinding.FragmentStudyWeekBinding
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekType
import com.senyk.volodymyr.schedulesapp.presentation.core.base.fragment.BaseFragment
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.SimpleTabAdapter
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity.DayTypeUi
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity.toDayType

class StudyWeekFragment : BaseFragment<FragmentStudyWeekBinding>() {

    override val layoutRes = R.layout.fragment_study_week

    override val viewModel by viewModels<StudyWeekViewModel> { viewModelFactory }

    private lateinit var adapter: SimpleTabAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SimpleTabAdapter(requireActivity().supportFragmentManager)
        binding.weekPager.adapter = adapter
        binding.weekTabs.setupWithViewPager(binding.weekPager)
        //     binding.weekPager.currentItem = sharedViewModel.currentDayIndex

        viewModel.currentSchedule.observe(viewLifecycleOwner, { schedule ->
            Log.e("StudyWeekFragment", "received $schedule")
            val daysCount = if (schedule.isSaturdayWorkingDay) 6 else 5
            for (ordinal in 0 until daysCount) {
                val scheduleId = schedule.id
                val dayType = DayTypeUi.values()[ordinal]
                if (schedule.isNumeratorDenominatorSystem) {
                    adapter.addFragment(
                        NumeratorDenominatorScheduleFragment.newInstance(scheduleId, dayType.toDayType()),
                        getString(dayType.nameResId)
                    )
                } else {
                    adapter.addFragment(
                        OneDayScheduleFragment.newInstance(
                            scheduleId,
                            WeekType.SIMPLE,
                            dayType.toDayType()
                        ),
                        getString(dayType.nameResId)
                    )
                }
            }
        })

        viewModel.onStart()
    }
}

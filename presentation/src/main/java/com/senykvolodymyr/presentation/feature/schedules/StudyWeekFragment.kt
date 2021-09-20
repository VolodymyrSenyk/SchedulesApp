package com.senykvolodymyr.presentation.feature.schedules

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.senykvolodymyr.core.base.fragment.BaseFragment
import com.senykvolodymyr.presentation.R
import com.senykvolodymyr.presentation.databinding.FragmentStudyWeekBinding
import com.senykvolodymyr.presentation.domain.entity.WeekType
import com.senykvolodymyr.presentation.feature.common.SimpleTabAdapter
import com.senykvolodymyr.presentation.feature.common.entity.DayTypeUi
import com.senykvolodymyr.presentation.feature.common.entity.toDayType

class StudyWeekFragment : BaseFragment<FragmentStudyWeekBinding>() {

    override val layoutRes = R.layout.fragment_study_week

    override val viewModel by viewModels<StudyWeekViewModel> { viewModelFactory }

    private lateinit var adapter: SimpleTabAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SimpleTabAdapter(requireActivity().supportFragmentManager)
        binding.viewPagerWeeks.adapter = adapter
        binding.tabLayoutWeeks.setupWithViewPager(binding.viewPagerWeeks)
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

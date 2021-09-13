package com.senyk.volodymyr.schedulesapp.presentation.feature.schedules

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.databinding.FragmentStudyWeekBinding
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekType
import com.senyk.volodymyr.schedulesapp.presentation.core.base.fragment.BaseFragment
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity.DayTypeUi
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity.toDayType
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.SimpleTabAdapter

class StudyWeekFragment : BaseFragment<FragmentStudyWeekBinding>() {

    override val layoutRes = R.layout.fragment_study_week
    override val viewModel by viewModels<StudyWeekViewModel>(factoryProducer = { viewModelFactory })
  //  private val sharedViewModel by activityViewModels<SchedulesNavigationSharedViewModel> { viewModelFactory }

    private lateinit var adapter: SimpleTabAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.currentSchedule.observe(viewLifecycleOwner, {schedule ->
            adapter = SimpleTabAdapter(requireActivity().supportFragmentManager)
            val daysCount = if (schedule.isSaturdayWorkingDay) 6 else 5
            for (ordinal in 0 until daysCount) {
                val scheduleId = schedule.id
                val dayType = DayTypeUi.values()[ordinal]
                if (schedule.isNumeratorDenominatorSystem) {
                    adapter.addFragment(
                        NumDenomScheduleFragment.newInstance(scheduleId, dayType.toDayType()),
                        getString(dayType.nameResId)
                    )
                } else {
                    adapter.addFragment(
                        OneDayScheduleFragment.newInstance(scheduleId, WeekType.SIMPLE, dayType.toDayType()),
                        getString(dayType.nameResId)
                    )
                }
            }
            binding.weekPager.adapter = adapter
            binding.weekTabs.setupWithViewPager(binding.weekPager)
       //     binding.weekPager.currentItem = sharedViewModel.currentDayIndex
        })
     //   sharedViewModel.setAppInitFinished()
     //   addObservers()
      //  viewModel.loadScheduleData(sharedViewModel.currentScheduleName)
    }

  //  override fun onPause() {
   //     super.onPause()
  //      sharedViewModel.saveCurrentDayIndex(binding.weekPager.currentItem)
   // }

   /* private fun addObservers() {
        viewModel.getScheduleData()
            .observe(viewLifecycleOwner, { schedule ->
                adapter = SimpleTabAdapter(requireActivity().supportFragmentManager)
                val daysOfTheWeek: Array<String> =
                    resources.getStringArray(R.array.days_of_the_week)
                val daysCount = if (schedule.isSaturdayWorkingDay) 6 else 5
                for (i in 0 until daysCount) {
                    if (schedule.isNumeratorDenominatorSystem) {
                        adapter.addFragment(
                            NumDenomScheduleFragment.newInstance(schedule.name, i + 1),
                            daysOfTheWeek[i]
                        )
                    } else {
                        adapter.addFragment(
                            OneDayScheduleFragment.newInstance(schedule.name, 1, i + 1),
                            daysOfTheWeek[i]
                        )
                    }
                }
                binding.weekPager.adapter = adapter
                binding.weekTabs.setupWithViewPager(binding.weekPager)
                binding.weekPager.currentItem = sharedViewModel.currentDayIndex
            })

        sharedViewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            binding.progressBar.isVisible = isLoading
        })
    }*/
}

package com.senyk.volodymyr.schedulesapp.presentation.feature.schedules

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.databinding.FragmentNumDenomScheduleBinding
import com.senyk.volodymyr.schedulesapp.domain.entity.DayType
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekType
import com.senyk.volodymyr.schedulesapp.presentation.core.base.fragment.BaseFragment
import com.senyk.volodymyr.schedulesapp.presentation.core.extensions.toEnum
import com.senyk.volodymyr.schedulesapp.presentation.core.extensions.toInt
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.SimpleTabAdapter

class NumDenomScheduleFragment : BaseFragment<FragmentNumDenomScheduleBinding>() {

    override val layoutRes = R.layout.fragment_num_denom_schedule
    override val viewModel by activityViewModels<SchedulesNavigationSharedViewModel>(
        factoryProducer = { viewModelFactory })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SimpleTabAdapter(childFragmentManager)
        //  val weeksNames: Array<String> = resources.getStringArray(R.array.weeks_names)
        val args = arguments
        if (args != null) {
            adapter.addFragment(
                OneDayScheduleFragment.newInstance(
                    args.getString(BUNDLE_KEY_SCHEDULE_ID, ""),
                    WeekType.NUMERATOR,
                    args.getInt(BUNDLE_KEY_DAY_TYPE, 0).toEnum()
                ),
                getString(R.string.week_name_numerator)
            )
            adapter.addFragment(
                OneDayScheduleFragment.newInstance(
                    args.getString(BUNDLE_KEY_SCHEDULE_ID, ""),
                    WeekType.DENOMINATOR,
                    args.getInt(BUNDLE_KEY_DAY_TYPE, 0).toEnum()
                ),
                getString(R.string.week_name_denominator)
            )
        }
        binding.daysPager.adapter = adapter
        binding.daysTabs.setupWithViewPager(binding.daysPager)
    }

    /*override fun onResume() {
        super.onResume()
        binding.daysPager.currentItem = viewModel.currentWeekIndex
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveCurrentWeekIndex(binding.daysPager.currentItem)
    }*/

    companion object {
        private const val BUNDLE_KEY_SCHEDULE_ID = "BUNDLE_KEY_SCHEDULE_ID"
        private const val BUNDLE_KEY_DAY_TYPE = "BUNDLE_KEY_DAY_TYPE"

        fun newInstance(
            scheduleId: String,
            dayType: DayType
        ): NumDenomScheduleFragment = NumDenomScheduleFragment().apply {
            arguments = Bundle().apply {
                putString(BUNDLE_KEY_SCHEDULE_ID, scheduleId)
                putInt(BUNDLE_KEY_DAY_TYPE, dayType.toInt())
            }
        }
    }
}

package com.senykvolodymyr.presentation.feature.schedules

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.senykvolodymyr.core.base.fragment.BaseFragment
import com.senykvolodymyr.core.extensions.toEnum
import com.senykvolodymyr.core.extensions.toInt
import com.senykvolodymyr.core.recyclerview.adapter.BaseDataBindingDelegationAdapter
import com.senykvolodymyr.core.recyclerview.adapterdelegate.EmptyStateAdapterDelegate
import com.senykvolodymyr.presentation.R
import com.senykvolodymyr.presentation.databinding.FragmentOneDayScheduleBinding
import com.senykvolodymyr.presentation.domain.entity.DayType
import com.senykvolodymyr.presentation.domain.entity.WeekType
import com.senykvolodymyr.presentation.feature.schedules.adapterdelegate.AddPairAdapterDelegate
import com.senykvolodymyr.presentation.feature.schedules.adapterdelegate.PairInputAdapterDelegate
import com.senykvolodymyr.presentation.feature.schedules.adapterdelegate.PairOutputAdapterDelegate

class OneDayScheduleFragment : BaseFragment<FragmentOneDayScheduleBinding>() {

    override val layoutRes = R.layout.fragment_one_day_schedule
    override val viewModel by viewModels<OneDayScheduleViewModel>(factoryProducer = { viewModelFactory })

    private lateinit var showPairsAdapter: BaseDataBindingDelegationAdapter
    private lateinit var editPairsAdapter: BaseDataBindingDelegationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showPairsAdapter = BaseDataBindingDelegationAdapter(
            listOf(PairOutputAdapterDelegate(viewModel), EmptyStateAdapterDelegate())
        )
        editPairsAdapter = BaseDataBindingDelegationAdapter(
            listOf(PairInputAdapterDelegate(viewModel), AddPairAdapterDelegate(viewModel))
        )

        binding.listPairs.adapter = showPairsAdapter

        viewModel.isEditMode.observe(viewLifecycleOwner, { isEditMode ->
            binding.listPairs.adapter = if (isEditMode) editPairsAdapter else showPairsAdapter
        })

        viewModel.pairs.observe(viewLifecycleOwner, { pairs ->
            showPairsAdapter.items = pairs
        })

        viewModel.editablePairs.observe(viewLifecycleOwner, { pairs ->
            editPairsAdapter.items = pairs
        })

        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    //   if (viewModel.isEditMode().value != null && viewModel!!.isEditMode().value!!) {
                    //       viewModel.setOutputMode()
                    //    } else {
                    //       requireActivity().finish()
                    //    }
                }
            })

        arguments?.let {
            viewModel.onStart(
                it.getString(BUNDLE_KEY_SCHEDULE_ID, ""),
                it.getInt(BUNDLE_KEY_WEEK_TYPE, 0).toEnum(),
                it.getInt(BUNDLE_KEY_DAY_TYPE, 0).toEnum(),
            )
        }
    }

    companion object {
        private const val BUNDLE_KEY_SCHEDULE_ID = "BUNDLE_KEY_SCHEDULE_ID"
        private const val BUNDLE_KEY_WEEK_TYPE = "BUNDLE_KEY_WEEK_TYPE"
        private const val BUNDLE_KEY_DAY_TYPE = "BUNDLE_KEY_DAY_TYPE"

        fun newInstance(
            scheduleId: String,
            weekType: WeekType,
            dayType: DayType
        ): OneDayScheduleFragment = OneDayScheduleFragment().apply {
            arguments = Bundle().apply {
                putString(BUNDLE_KEY_SCHEDULE_ID, scheduleId)
                putInt(BUNDLE_KEY_WEEK_TYPE, weekType.toInt())
                putInt(BUNDLE_KEY_DAY_TYPE, dayType.toInt())
            }
        }
    }
}

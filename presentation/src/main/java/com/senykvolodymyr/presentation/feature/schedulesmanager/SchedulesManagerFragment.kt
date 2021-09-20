package com.senykvolodymyr.presentation.feature.schedulesmanager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.senykvolodymyr.core.base.fragment.BaseFragment
import com.senykvolodymyr.core.recyclerview.adapter.BaseDataBindingDelegationAdapter
import com.senykvolodymyr.core.recyclerview.adapterdelegate.EmptyStateAdapterDelegate
import com.senykvolodymyr.presentation.R
import com.senykvolodymyr.presentation.databinding.FragmentSchedulesManagerBinding
import com.senykvolodymyr.presentation.feature.schedulesmanager.adapterdelegate.ScheduleAdapterDelegate

class SchedulesManagerFragment : BaseFragment<FragmentSchedulesManagerBinding>() {

    override val layoutRes = R.layout.fragment_schedules_manager

    override val viewModel by viewModels<SchedulesManagerViewModel> { viewModelFactory }

    private lateinit var schedulesAdapter: BaseDataBindingDelegationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.showProgress = viewModel.showProgress

        schedulesAdapter = BaseDataBindingDelegationAdapter(
            listOf(ScheduleAdapterDelegate(viewModel), EmptyStateAdapterDelegate())
        )
        binding.listSchedules.adapter = schedulesAdapter

        viewModel.schedules.observe(viewLifecycleOwner, { schedules ->
            schedulesAdapter.items = schedules
        })

        viewModel.onStart()
    }
}

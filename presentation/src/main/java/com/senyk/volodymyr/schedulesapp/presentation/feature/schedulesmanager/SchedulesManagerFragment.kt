package com.senyk.volodymyr.schedulesapp.presentation.feature.schedulesmanager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.databinding.FragmentSchedulesManagerBinding
import com.senyk.volodymyr.schedulesapp.presentation.core.base.fragment.BaseFragment
import com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.adapter.BaseDataBindingDelegationAdapter
import com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.adapterdelegate.EmptyStateAdapterDelegate

class SchedulesManagerFragment : BaseFragment<FragmentSchedulesManagerBinding>() {

    override val layoutRes = R.layout.fragment_schedules_manager

    override val viewModel by viewModels<SchedulesManagerViewModel> { viewModelFactory }

    private lateinit var schedulesAdapter: BaseDataBindingDelegationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.showProgress = viewModel.showProgress

        schedulesAdapter = BaseDataBindingDelegationAdapter(
            listOf(SchedulesOutputAdapterDelegate(viewModel), EmptyStateAdapterDelegate())
        )
        binding.allSchedulesList.adapter = schedulesAdapter

        viewModel.schedules.observe(viewLifecycleOwner, { schedules ->
            schedulesAdapter.items = schedules
        })

        viewModel.onStart()
    }
}

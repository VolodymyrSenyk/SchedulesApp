package com.senyk.volodymyr.schedulesapp.presentation.feature.schedulesmanager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.databinding.FragmentSchedulesManagerBinding
import com.senyk.volodymyr.schedulesapp.presentation.core.base.fragment.BaseFragment
import com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.adapter.BaseDataBindingDelegationAdapter

class SchedulesManagerFragment : BaseFragment<FragmentSchedulesManagerBinding>() {

    override val layoutRes = R.layout.fragment_schedules_manager
    override val viewModel by viewModels<SchedulesManagerViewModel>(factoryProducer = { viewModelFactory })

    private lateinit var schedulesAdapter: BaseDataBindingDelegationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        schedulesAdapter = BaseDataBindingDelegationAdapter(
            listOf(
                SchedulesOutputAdapterDelegate(viewModel)//,
                //       EmptyStateAdapterDelegate()
            )
        )

        binding.allSchedulesList.adapter = schedulesAdapter

        viewModel.schedules.observe(viewLifecycleOwner, { schedules ->
            schedulesAdapter.items = schedules
        })

        /* viewModel = ViewModelProvider(this, this.viewModelFactory)
             .get<SchedulesManagerViewModel>(SchedulesManagerViewModel::class.java)
         sharedViewModel = ViewModelProvider(requireActivity(), this.viewModelFactory)
             .get<SchedulesNavigationSharedViewModel>(SchedulesNavigationSharedViewModel::class.java)*/
        //   viewModel!!.setSkipSchedulesDataOutput(sharedViewModel.isAppInit())
        //   setView(view)
        //    addObservers()
        //  requireActivity().getOnBackPressedDispatcher()
        //      .addCallback(this.getViewLifecycleOwner(), object : OnBackPressedCallback(true) {
        //         override fun handleOnBackPressed() {
        //             NavHostFragment.findNavController(this@SchedulesManagerFragment)
        //                 .navigate(SchedulesManagerFragmentDirections.goToSchedule())
        //         }
        //      })
        //   sharedViewModel.setIsLoading(true)
        viewModel.onStart()
    }
}
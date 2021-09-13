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
    // private val sharedViewModel by activityViewModels<SchedulesNavigationSharedViewModel> { viewModelFactory }

    private lateinit var schedulesAdapter: BaseDataBindingDelegationAdapter

    //  private var sharedViewModel: SchedulesNavigationSharedViewModel? = null

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

    /* private fun setView(view: View) {
         (view.findViewById<View>(R.id.screen_title) as TextView).setText(R.string.schedules_manager_screen_title)
         view.findViewById<View>(R.id.next_button).visibility = View.GONE
         val cancelButton: AppCompatImageButton = view.findViewById(R.id.back_button)
         cancelButton.setImageResource(R.drawable.ic_back)
         cancelButton.setOnClickListener(View.OnClickListener { view1: View? ->
             NavHostFragment.findNavController(this)
                 .navigate(SchedulesManagerFragmentDirections.goToSchedule())
         })
         schedulesListAdapter = SchedulesOutputAdapter(this, ArrayList<E>())
         val dataList: RecyclerView = view.findViewById(R.id.all_schedules_list)
         dataList.setHasFixedSize(true)
         dataList.setNestedScrollingEnabled(false)
         val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(view.context)
         dataList.setLayoutManager(layoutManager)
         dataList.setAdapter(schedulesListAdapter)
         val addButton: FloatingActionButton = view.findViewById(R.id.add_new_schedule_button)
         addButton.setOnClickListener(View.OnClickListener { view12: View? ->
             NavHostFragment.findNavController(this)
                 .navigate(SchedulesManagerFragmentDirections.createNewSchedule(false))
         })
     }

     private fun addObservers() {
         viewModel!!.isNoSchedules()
             .observe(this.getViewLifecycleOwner(), { isNoSchedules: Boolean? ->
                 NavHostFragment.findNavController(this@SchedulesManagerFragment)
                     .navigate(SchedulesManagerFragmentDirections.createNewSchedule(true))
             })
         viewModel!!.getCurrentScheduleName()
             .observe(
                 this.getViewLifecycleOwner(),
                 Observer { scheduleName: String? ->
                     sharedViewModel.setCurrentScheduleName(scheduleName)
                 })
         viewModel!!.getSchedulesOutputList()
             .observe(
                 requireActivity(),
                 { schedules: List<ScheduleUi?>? -> schedulesListAdapter.setUiItems(schedules) })
         viewModel!!.goToSchedule()
             .observe(this.getViewLifecycleOwner(), { currentScheduleName: Boolean? ->
                 NavHostFragment.findNavController(this@SchedulesManagerFragment)
                     .navigate(SchedulesManagerFragmentDirections.goToSchedule())
             })
         viewModel!!.needToShowConfirmSwapDialog()
             .observe(
                 this.getViewLifecycleOwner(),
                 { needToShow: Boolean? -> showSchedulesSwapDialog() })
         viewModel.getMessage()
             .observe(this.getViewLifecycleOwner()) { message ->
                 Toast.makeText(
                     requireContext(),
                     message,
                     Toast.LENGTH_LONG
                 ).show()
             }
     }

     fun scheduleClicked(clickedScheduleName: String?) {
         viewModel!!.scheduleSelected(clickedScheduleName!!)
     }

     fun scheduleDeleteClicked(clickedScheduleName: String) {
         showDeleteScheduleDialog(clickedScheduleName)
     }

     private fun showSchedulesSwapDialog() {
         val dialogFactory: DialogFragmentFactory =
             DialogFragmentFactory.newInstance(DialogFragmentsTypes.SWAP_SCHEDULES_DIALOG)
         dialogFactory.setTargetFragment(this, DIALOG_FRAGMENT_REQUEST_CODE)
         dialogFactory.show(requireFragmentManager(), DialogFragmentsTypes.SWAP_SCHEDULES_DIALOG)
     }

     private fun showDeleteScheduleDialog(clickedScheduleName: String) {
         val dialogFactory: DialogFragmentFactory = DialogFragmentFactory.newInstance(
             DialogFragmentsTypes.DELETE_SCHEDULE_DIALOG,
             clickedScheduleName
         )
         dialogFactory.setTargetFragment(this, DIALOG_FRAGMENT_REQUEST_CODE)
         dialogFactory.show(requireFragmentManager(), DialogFragmentsTypes.DELETE_SCHEDULE_DIALOG)
     }

     fun onPositiveButtonClick(dialogType: String?, additionalData: String?) {
         when (dialogType) {
             DialogFragmentsTypes.SWAP_SCHEDULES_DIALOG -> viewModel!!.swapSchedules()
             DialogFragmentsTypes.DELETE_SCHEDULE_DIALOG -> viewModel!!.deleteSchedule(
                 additionalData!!
             )
         }
     }

     fun onNegativeButtonClick(dialogType: String?, additionalData: String?) {}

     companion object {
         private const val DIALOG_FRAGMENT_REQUEST_CODE = 1
     }*/
}
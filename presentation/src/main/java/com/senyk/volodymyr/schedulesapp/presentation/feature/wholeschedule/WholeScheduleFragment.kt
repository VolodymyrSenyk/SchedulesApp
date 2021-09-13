package com.senyk.volodymyr.schedulesapp.presentation.feature.wholeschedule

import androidx.fragment.app.viewModels
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.databinding.FragmentWholeScheduleBinding
import com.senyk.volodymyr.schedulesapp.presentation.core.base.fragment.BaseFragment
import com.senyk.volodymyr.schedulesapp.presentation.feature.schedules.StudyWeekViewModel

class WholeScheduleFragment : BaseFragment<FragmentWholeScheduleBinding>() {

    override val layoutRes = R.layout.fragment_whole_schedule
    override val viewModel by viewModels<StudyWeekViewModel>(factoryProducer = { viewModelFactory })
}

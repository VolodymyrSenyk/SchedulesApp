package com.senykvolodymyr.presentation.feature.wholeschedule

import androidx.fragment.app.viewModels
import com.senykvolodymyr.core.base.fragment.BaseFragment
import com.senykvolodymyr.presentation.R
import com.senykvolodymyr.presentation.databinding.FragmentWholeScheduleBinding

class WholeScheduleFragment : BaseFragment<FragmentWholeScheduleBinding>() {

    override val layoutRes = R.layout.fragment_whole_schedule
    override val viewModel by viewModels<WholeScheduleViewModel>(factoryProducer = { viewModelFactory })
}

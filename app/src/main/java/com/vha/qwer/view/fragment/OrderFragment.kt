package com.vha.qwer.view.fragment

import android.os.Bundle
import android.view.View
import com.vha.qwer.BR
import com.vha.qwer.R
import com.vha.qwer.base.BaseFragment
import com.vha.qwer.base.DataBindingConfig
import com.vha.qwer.databinding.FragmentOrderBinding
import com.vha.qwer.vm.MainViewModel

class OrderFragment: BaseFragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentOrderBinding

    override val dataBindingConfig: DataBindingConfig
        get() = DataBindingConfig(R.layout.fragment_order, BR.mainViewModel, mainViewModel)

    override fun initViewModel() {
        mainViewModel = getAppViewModel(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = mBinding as FragmentOrderBinding
    }
}
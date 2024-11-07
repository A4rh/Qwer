package com.vha.qwer.view

import android.os.Bundle
import com.vha.qwer.BR
import com.vha.qwer.R
import com.vha.qwer.base.BaseActivity
import com.vha.qwer.base.DataBindingConfig
import com.vha.qwer.databinding.ActivityMainBinding
import com.vha.qwer.vm.MainViewModel

class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override val dataBindingConfig: DataBindingConfig
        get() = DataBindingConfig(R.layout.activity_main, BR.mainViewModel, mainViewModel)

    override fun initViewModel() {
        mainViewModel = getAppViewModel(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = mBinding as ActivityMainBinding

    }
}
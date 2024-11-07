package com.vha.qwer.view

import android.os.Bundle
import com.vha.qwer.BR
import com.vha.qwer.R
import com.vha.qwer.base.BaseActivity
import com.vha.qwer.base.DataBindingConfig
import com.vha.qwer.databinding.ActivityLoginBinding
import com.vha.qwer.vm.LoginViewModel

class LoginActivity: BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override val dataBindingConfig: DataBindingConfig
        get() = DataBindingConfig(R.layout.activity_login, BR.loginViewModel, loginViewModel)

    override fun initViewModel() {
        loginViewModel = getAppViewModel(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = mBinding as ActivityLoginBinding

    }
}
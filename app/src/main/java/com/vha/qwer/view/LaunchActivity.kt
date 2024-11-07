package com.vha.qwer.view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.vha.qwer.BR
import com.vha.qwer.R
import com.vha.qwer.base.BaseActivity
import com.vha.qwer.base.DataBindingConfig
import com.vha.qwer.databinding.ActivityLaunchBinding
import com.vha.qwer.vm.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LaunchActivity : BaseActivity() {

    private lateinit var binding: ActivityLaunchBinding
    private lateinit var loginViewModel: LoginViewModel

    override val dataBindingConfig: DataBindingConfig
        get() = DataBindingConfig(R.layout.activity_launch, BR.loginViewModel, loginViewModel)

    override fun initViewModel() {
        loginViewModel = getAppViewModel(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = mBinding as ActivityLaunchBinding

        lifecycleScope.launch {
            delay(2000)

            val intent = Intent().apply {
                if (loginViewModel.hadLogin()) {
                    setClass(this@LaunchActivity, MainActivity::class.java)
                } else {
                    setClass(this@LaunchActivity, LoginActivity::class.java)
                }
            }
            startActivity(intent)
            finish()
        }

    }
}
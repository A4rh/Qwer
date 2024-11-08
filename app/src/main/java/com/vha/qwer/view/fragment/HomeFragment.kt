package com.vha.qwer.view.fragment

import android.os.Bundle
import android.view.View
import com.vha.qwer.BR
import com.vha.qwer.R
import com.vha.qwer.base.BaseFragment
import com.vha.qwer.base.DataBindingConfig
import com.vha.qwer.databinding.FragmentHomeBinding
import com.vha.qwer.model.HomeItem
import com.vha.qwer.utils.DisplayHelper
import com.vha.qwer.view.adapter.HomeGridAdapter
import com.vha.qwer.vm.MainViewModel

class HomeFragment: BaseFragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentHomeBinding

    override val dataBindingConfig: DataBindingConfig
        get() = DataBindingConfig(R.layout.fragment_home, BR.mainViewModel, mainViewModel)

    override fun initViewModel() {
        mainViewModel = getAppViewModel(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = mBinding as FragmentHomeBinding

        binding.space.layoutParams.height = DisplayHelper.getStatusBarHeight(mActivity)

        val adapter = HomeGridAdapter(mActivity)
        binding.recyclerView.adapter = adapter
        adapter.setList(createListData())
    }

    private fun createListData(): ArrayList<HomeItem> {
        val list = arrayListOf<HomeItem>()
        list.add(HomeItem(R.mipmap.home_ic_1, "Compras"))
        list.add(HomeItem(R.mipmap.home_ic_2, "Flujo de caja"))
        list.add(HomeItem(R.mipmap.home_ic_3, "Paga tus facturas"))
        list.add(HomeItem(R.mipmap.home_ic_4, "Paga tu alquiler"))
        list.add(HomeItem(R.mipmap.home_ic_5, "Delicias"))
        list.add(HomeItem(R.mipmap.home_ic_6, "Criar a los hijos"))
        list.add(HomeItem(R.mipmap.home_ic_7, "Aptitud"))
        list.add(HomeItem(R.mipmap.home_ic_8, "viajar"))
        return list
    }
}
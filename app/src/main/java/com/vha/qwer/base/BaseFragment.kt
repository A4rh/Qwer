package com.vha.qwer.base

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.vha.qwer.vm.VMFactory

abstract class BaseFragment : Fragment() {

    protected lateinit var mActivity: AppCompatActivity
    protected lateinit var mBinding: ViewDataBinding

    protected abstract val dataBindingConfig: DataBindingConfig

    private val mViewModelFactory = VMFactory()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    protected abstract fun initViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        mBinding = DataBindingUtil.inflate(inflater, dataBindingConfig.layout, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        if (dataBindingConfig.stateViewModel != null) {
            mBinding.setVariable(dataBindingConfig.vmVariableId, dataBindingConfig.stateViewModel)
        }
        val bindingParams: SparseArray<Any> = dataBindingConfig.bindingParams
        var i = 0
        val length: Int = bindingParams.size()
        while (i < length) {
            mBinding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i))
            i++
        }
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding.unbind()
    }

    protected fun <T: ViewModel> getFragmentViewModel(modelClass: Class<T>): T {
        return mViewModelFactory.getFragmentViewModel(this, modelClass)
    }

    protected fun <T: ViewModel> getActivityViewModel(modelClass: Class<T>): T {
        return mViewModelFactory.getActivityViewModel(mActivity, modelClass)
    }

    protected fun <T: ViewModel> getAppViewModel(modelClass: Class<T>): T {
        return VMFactory.getAppViewModel(modelClass)
    }

    protected fun getCompatDrawable(@DrawableRes drawableRes: Int): Drawable? {
        val mContext = context ?: mActivity
        return ContextCompat.getDrawable(mContext, drawableRes)
    }

    protected fun getCompatColor(@ColorRes colorRes: Int): Int {
        val mContext = context ?: mActivity
        return ContextCompat.getColor(mContext, colorRes)
    }
}
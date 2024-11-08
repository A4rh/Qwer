package com.vha.qwer.base

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.vha.qwer.R
import com.vha.qwer.utils.MyLog
import com.vha.qwer.vm.VMFactory

abstract class BaseActivity: AppCompatActivity() {

    private val mViewModelFactory = VMFactory()

    protected lateinit var mBinding: ViewDataBinding

    protected var currentFragment: Fragment? = null

    protected abstract fun initViewModel()

    protected abstract val dataBindingConfig: DataBindingConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()

        mBinding = DataBindingUtil.setContentView(this, dataBindingConfig.layout)
        mBinding.lifecycleOwner = this
        if (dataBindingConfig.stateViewModel != null) {
            mBinding.setVariable(dataBindingConfig.vmVariableId, dataBindingConfig.stateViewModel)
        }
        val bindingParams: SparseArray<Any> = dataBindingConfig.bindingParams
        var i = 0
        val length = bindingParams.size()
        while (i < length) {
            mBinding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i))
            i++
        }
        setImmersiveDarkMode()
    }

    protected open fun switchFragment(clazz: Class<out Fragment?>?) {
        if (clazz == null) {
            return
        }
        val fragmentName = clazz.name
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        var userFragment = fm.findFragmentByTag(fragmentName)
        if (userFragment == null) {
            try {
                userFragment = clazz.newInstance()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
        if (userFragment == null) {
            throw NullPointerException("$fragmentName have no parameterless constructor")
        }
        if (currentFragment != null && currentFragment !== userFragment) {
            ft.hide(currentFragment!!)
        }
        if (!userFragment.isAdded) {
            ft.add(R.id.fragment_container, userFragment, fragmentName)
        } else {
            ft.show(userFragment)
        }
        ft.commitAllowingStateLoss()
        currentFragment = userFragment
    }

    /**
     * 设置状态栏字体亮色
     */
    protected open fun setImmersiveLightMode() {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = getColor(R.color.white)
    }

    /**
     * 设置状态栏字体暗色
     *
     *
     * 当页面TitleBar背景色是亮色时调用此方法，为暗色时调用setImmersiveMode()。
     * 小米的MIUI和魅族的Flyme在Android 4.4之后各自提供了自家的修改方法，其他品牌只能在Android 6.0及以后才能修改。
     * 经过查看用户分布(2018-02-23)Android 6.0之下占33.27%，小米用户6.69%，魅族用户1.08%。
     * 这样一看都没有必要对小米、魅族分别处理了，那就都统一到Android 6.0去设置吧。
     *
     */
    protected open fun setImmersiveDarkMode() {
        val window = window
        //设置状态栏底色白色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = Color.TRANSPARENT
        // 设置状态栏字体黑色
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }

    protected fun <T: ViewModel> getActivityViewModel(modelClass: Class<T>): T {
        return mViewModelFactory.getActivityViewModel(this, modelClass)
    }

    protected fun <T: ViewModel> getAppViewModel(modelClass: Class<T>): T {
        return VMFactory.getAppViewModel(modelClass)
    }

    protected fun getCompatDrawable(@DrawableRes drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(this, drawableRes)
    }

    protected fun getCompatColor(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(this, colorRes)
    }
}
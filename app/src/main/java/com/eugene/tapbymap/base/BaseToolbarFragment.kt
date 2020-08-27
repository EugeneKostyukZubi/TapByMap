package com.eugene.tapbymap.base

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes

/**
 * Created by Eugene on 2019-11-16
 */
open class BaseToolbarFragment : BaseFragment() {

    private var baseActivity: BaseToolbarActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity !is BaseToolbarActivity)
            throw Exception("Fragment must extend by BaseActivity")
        baseActivity = requireActivity() as BaseToolbarActivity

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    protected open val screenTitle: String = ""

    @StringRes
    protected open val screenTitleRes: Int = 0

    @DrawableRes
    protected open val toolbarNavigationIcon: Int = 0

    @MenuRes
    protected open val optionsMenu: Int = 0

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        initToolbarParams()
        if (optionsMenu != 0)
            inflater.inflate(optionsMenu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    protected fun changeToolbarTitle(title: String) {
        baseActivity?.changeToolbarTitle(title)
    }

    private fun initToolbarParams() {
        if (screenTitleRes != 0)
            baseActivity?.changeToolbarTitle(screenTitleRes)
        if (screenTitle.isNotEmpty())
            baseActivity?.changeToolbarTitle(screenTitle)
        if (toolbarNavigationIcon != 0)
            baseActivity?.changeNavigationIcon(toolbarNavigationIcon)
    }
}
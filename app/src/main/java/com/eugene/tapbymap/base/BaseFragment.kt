package com.eugene.tapbymap.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 * Created by Eugene on 2019-11-16
 */

open class BaseFragment : Fragment() {

    private var baseActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity !is BaseActivity)
            throw Exception("Fragment must extend by BaseActivity")
        baseActivity = requireActivity() as BaseActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        bindUi()
    }

    /**
     * It will be implemented in a child for common interaction with view model and views
     * It will be called in onViewCreated
     * */
    open protected fun initUi() { /* Nothing to do here */ }

    /**
     * It will be implemented in a child for common interaction with view model
     * It will be called in onViewCreated
     * */
    open protected fun bindUi() { /* Nothing to do here */ }

    /**
     * Create Toast.makeText with data form arguments
     *
     * @param res string from resource
     * @param duration Duration of show of Toast
     * */
    protected fun showToast(@StringRes res: Int, duration: Int) {
        try {
            Toast.makeText(requireContext(), res, duration).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Create Toast.makeText with data form arguments
     *
     * @param res string from resource
     * */
    protected fun showToast(@StringRes res: Int) {
        showToast(res, Toast.LENGTH_LONG)
    }

    /**
     * Create Toast.makeText with data form arguments
     *
     * @param string message for showing
     * @param duration Duration of show of Toast
     * */
    protected fun showToast(string: String, duration: Int) {
        try {
            Toast.makeText(requireContext(), string, duration).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Create Toast.makeText with data form arguments
     *
     * @param string message for showing
     * */
    protected fun showToast(string: String) {
        showToast(string, Toast.LENGTH_LONG)
    }
}
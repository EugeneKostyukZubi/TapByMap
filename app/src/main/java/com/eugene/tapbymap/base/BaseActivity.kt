package com.eugene.tapbymap.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Eugene on 2019-11-16
 */
abstract class BaseActivity : AppCompatActivity() {
    /**
     * Set xml file here
     * */
    abstract val contentView: Int

    /**
     * Setup status bar like translucent with fullscreen mode
     * */
    protected open val statusBarTransparent: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)

        if (statusBarTransparent) setStatusBarSettings()

        initUi()
        bindUi()
    }

    /**
     * It will be implemented in a child for common interaction with view model and views
     * It will be called in onCreate
     * */
    protected open fun initUi() { /* Nothing to do here */
    }

    /**
     * It will be implemented in a child for common interaction with view model
     * It will be called in onCreate
     * */
    protected open fun bindUi() { /* Nothing to do here */
    }

    /**
     * Create Toast.makeText with data form arguments
     *
     * @param res string from resource
     * @param duration Duration of show of Toast
     * */
    protected fun showToast(@StringRes res: Int, duration: Int) {
        try {
            Toast.makeText(this, res, duration).show()
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
            Toast.makeText(this, string, duration).show()
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

    private fun setStatusBarSettings() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }
}
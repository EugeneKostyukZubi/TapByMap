package com.eugene.tapbymap.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import com.eugene.tapbymap.R
import kotlinx.android.synthetic.main.toolbar_title.*

/**
 * Created by Eugene on 2019-11-16
 */
abstract class BaseToolbarActivity : BaseActivity() {

    final override val statusBarTransparent: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (findViewById(R.id.toolbar) as? Toolbar)?.let {
            setSupportActionBar(it)

            supportActionBar?.run {
                setDisplayHomeAsUpEnabled(false)
                setDisplayShowTitleEnabled(false)
                initToolbarParams()
            }
        } ?: throw Exception("Activity must contain AppBarLayout")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (optionsMenu != 0)
            menuInflater.inflate(optionsMenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @StringRes
    protected open val screenTitleRes: Int = 0

    protected open val screenTitle: String = ""

    @DrawableRes
    protected open val toolbarNavigationIcon: Int = 0

    @MenuRes
    protected open val optionsMenu: Int = 0


    fun changeToolbarTitle(@StringRes titleId: Int) {
        changeToolbarTitle(getString(titleId))
    }

    fun changeToolbarTitle(title: String) {
        toolbarTitle.text = title
    }

    fun changeNavigationIcon(@DrawableRes navigationIcon: Int?) {
        (findViewById(R.id.toolbar) as? Toolbar)?.let { toolbar ->
            navigationIcon
                ?.run { toolbar.setNavigationIcon(this) }
                ?: run { toolbar.navigationIcon = null }
        }
    }

    private fun initToolbarParams() {
        if (screenTitleRes != 0)
            toolbarTitle.text = getString(screenTitleRes)

        if (screenTitle.isNotEmpty()) {
            toolbarTitle.text = screenTitle
        }

        if (toolbarNavigationIcon != 0)
            changeNavigationIcon(toolbarNavigationIcon)
    }
}
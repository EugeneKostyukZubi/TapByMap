package com.eugene.tapbymap.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.eugene.tapbymap.R
import com.eugene.tapbymap.base.BaseToolbarFragment
import com.eugene.tapbymap.databinding.FragmentHistoryBinding
import com.eugene.tapbymap.exception.NotFoundException
import com.eugene.tapbymap.extensions.observeEvent
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Eugene on 27.08.2020
 */
class HistoryFragment : BaseToolbarFragment() {

    override val screenTitleRes = R.string.history

    override val optionsMenu = R.menu.history_menu

    private val viewModel : HistoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentHistoryBinding>(
                inflater,
                R.layout.fragment_history,
                container,
                false
            )
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun initUi() {
        searchHistoryRecyclerView.adapter = HistoryAdapter(R.layout.item_search_history)
        viewModel.getSearchHistory()
    }

    override fun bindUi() {
        viewModel.onErrorEvent.observeEvent(viewLifecycleOwner) {
            when(it) {
                is NotFoundException -> showToast(getString(R.string.not_found))
                else -> showToast(it.message ?: getString(R.string.something_went_wrong))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.remove -> viewModel.removeSearchHistory()
        }
        return super.onOptionsItemSelected(item)
    }
}
package com.eugene.tapbymap.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.eugene.tapbymap.R
import com.eugene.tapbymap.base.BaseToolbarFragment
import com.eugene.tapbymap.databinding.FragmentHistoryBinding
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Eugene on 27.08.2020
 */
class HistoryFragment : BaseToolbarFragment() {

    override val screenTitleRes = R.string.history

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
}
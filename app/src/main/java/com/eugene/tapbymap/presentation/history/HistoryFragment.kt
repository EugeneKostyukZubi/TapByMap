package com.eugene.tapbymap.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eugene.tapbymap.R
import com.eugene.tapbymap.base.BaseToolbarFragment

/**
 * Created by Eugene on 27.08.2020
 */
class HistoryFragment : BaseToolbarFragment() {

    override val screenTitleRes = R.string.history

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }
}
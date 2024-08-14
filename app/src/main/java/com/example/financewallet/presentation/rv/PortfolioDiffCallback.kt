package com.example.financewallet.presentation.rv

import androidx.recyclerview.widget.DiffUtil
import com.example.financewallet.domain.entity.Portfolio

class PortfolioDiffCallback : DiffUtil.ItemCallback<Portfolio>() {
    override fun areItemsTheSame(oldItem: Portfolio, newItem: Portfolio): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Portfolio, newItem: Portfolio): Boolean {
        return oldItem == newItem
    }
}

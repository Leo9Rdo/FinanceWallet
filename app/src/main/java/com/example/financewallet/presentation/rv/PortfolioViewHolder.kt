package com.example.financewallet.presentation.rv

import androidx.recyclerview.widget.RecyclerView
import com.example.financewallet.databinding.ItemPortfolioBinding
import com.example.financewallet.domain.entity.Portfolio

class PortfolioViewHolder(
    private val binding: ItemPortfolioBinding,
    private val onEdit: (Portfolio) -> Unit,
    private val onDelete: (Portfolio) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(portfolio: Portfolio) {
        with(binding) {
            textViewPortfolioName.text = portfolio.name
            btnEdit.setOnClickListener { onEdit(portfolio) }
            btnDelete.setOnClickListener { onDelete(portfolio) }
        }
    }
}

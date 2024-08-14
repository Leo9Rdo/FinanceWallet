package com.example.financewallet.presentation.rv

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.financewallet.databinding.ItemPortfolioBinding
import com.example.financewallet.domain.entity.Portfolio

class PortfolioAdapter(
    private val onEdit: (Portfolio) -> Unit,
    private val onDelete: (Portfolio) -> Unit
) : RecyclerView.Adapter<PortfolioViewHolder>() {

    private var portfolios: List<Portfolio> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        val binding =
            ItemPortfolioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PortfolioViewHolder(binding, onEdit, onDelete)
    }

    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
        holder.bind(portfolios[position])
    }

    override fun getItemCount(): Int = portfolios.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newPortfolios: List<Portfolio>) {
        portfolios = newPortfolios
        notifyDataSetChanged()
    }
}

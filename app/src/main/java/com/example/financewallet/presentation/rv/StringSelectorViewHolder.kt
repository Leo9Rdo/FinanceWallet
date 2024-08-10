package com.example.financewallet.presentation.rv

import androidx.recyclerview.widget.RecyclerView
import com.example.financewallet.databinding.ItemStringSelectorBinding

class StringSelectorViewHolder(
    private val binding: ItemStringSelectorBinding,
    private val onItemSelected: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.apply {
            currencyTextView.text = item
            root.setOnClickListener {
                onItemSelected(item)
            }
        }
    }
}

package com.example.financewallet.presentation.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.financewallet.databinding.ItemStringSelectorBinding

class StringSelectorAdapter(
    private val onItemSelected: (String) -> Unit
) : ListAdapter<String, StringSelectorViewHolder>(StringSelectorDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringSelectorViewHolder {
        val binding =
            ItemStringSelectorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StringSelectorViewHolder(binding, onItemSelected)
    }

    override fun onBindViewHolder(holder: StringSelectorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

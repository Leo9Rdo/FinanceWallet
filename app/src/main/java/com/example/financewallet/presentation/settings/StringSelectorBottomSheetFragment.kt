package com.example.financewallet.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import com.example.financewallet.databinding.FragmentStringSelectorBottomSheetBinding
import com.example.financewallet.presentation.rv.StringSelectorAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StringSelectorBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        private const val KEY_CURRENCY = "currencyKey"
        private const val KEY_CURRENCY_NAME = "currencyName"
    }

    private var _binding: FragmentStringSelectorBottomSheetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CurrencyViewModel by activityViewModels()

    private val stringSelectorAdapter by lazy {
        StringSelectorAdapter { selectedCurrency ->
            setFragmentResult(KEY_CURRENCY, bundleOf(KEY_CURRENCY_NAME to selectedCurrency))
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStringSelectorBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()

        viewModel.availableCurrencies.observe(viewLifecycleOwner) { currencies ->
            stringSelectorAdapter.submitList(currencies)
        }
    }

    private fun configureRecyclerView() {
        binding.currencyRecyclerView.adapter = stringSelectorAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

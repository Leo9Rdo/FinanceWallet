package com.example.financewallet.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.financewallet.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    companion object {
        private const val KEY_CURRENCY = "currencyKey"
        private const val KEY_CURRENCY_NAME = "currencyName"
    }

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CurrencyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCurrencySelector()
        observeViewModel()
        setFragmentResultListener(KEY_CURRENCY) { _, bundle ->
            bundle.getString(KEY_CURRENCY_NAME)?.let { result ->
                viewModel.selectCurrency(result)
            }
        }
    }

    private fun setupCurrencySelector() {
        binding.currencySelector.setOnClickListener {
            val action = SettingsFragmentDirections
                .actionNavigationSettingsToNavigationStringSelectorBottomSheet()
            findNavController().navigate(action)
        }
    }

    private fun observeViewModel() {
        viewModel.selectedCurrencyModel.observe(viewLifecycleOwner) { selectedCurrencyName ->
            binding.currencySelector.text = selectedCurrencyName
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

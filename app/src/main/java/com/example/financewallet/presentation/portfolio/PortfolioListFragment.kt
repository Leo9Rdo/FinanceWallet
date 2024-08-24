package com.example.financewallet.presentation.portfolio

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financewallet.R
import com.example.financewallet.databinding.FragmentPortfolioListBinding
import com.example.financewallet.domain.entity.Portfolio
import com.example.financewallet.presentation.rv.PortfolioAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PortfolioListFragment : Fragment() {

    private var _binding: FragmentPortfolioListBinding? = null
    private val binding get() = _binding!!

    private lateinit var portfolioViewModel: PortfolioViewModel

    private lateinit var adapter: PortfolioAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPortfolioListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        portfolioViewModel = ViewModelProvider(this).get(PortfolioViewModel::class.java)
        adapter = PortfolioAdapter(onEdit = { showPortfolioDialog(it) },
            onDelete = { deletePortfolio(it) })

        with(binding) {
            recyclerViewPortfolios.layoutManager = LinearLayoutManager(context)
            recyclerViewPortfolios.adapter = adapter

            fab.setOnClickListener {
                showPortfolioDialog()
            }
        }
        observeViewModel()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeViewModel() {
        portfolioViewModel.portfolios.observe(viewLifecycleOwner) { portfolios ->
            adapter.submitList(portfolios)
        }
    }

    private fun showPortfolioDialog(portfolio: Portfolio? = null) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        val input = EditText(requireContext())
        input.setText(portfolio?.name ?: "")
        input.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        dialogBuilder.setView(input)
            .setTitle(if (portfolio == null) R.string.new_portfolio else R.string.edit_portfolio)
            .setPositiveButton(R.string.save) { _, _ ->
                val newPortfolio = portfolio?.copy(name = input.text.toString())
                    ?: Portfolio(
                        0,
                        input.text.toString(),
                        emptyList()
                    )
                portfolioViewModel.savePortfolio(newPortfolio)
            }
            .setNegativeButton(R.string.cancel, null)
        dialogBuilder.create().show()
    }

    @SuppressLint("StringFormatInvalid")
    private fun deletePortfolio(portfolio: Portfolio) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.delete_portfolio)
            .setMessage(getString(R.string.are_you_sure, portfolio.name))
            .setPositiveButton(R.string.yes) { _, _ ->
                portfolioViewModel.deletePortfolio(portfolio.id)
            }
            .setNegativeButton(R.string.no, null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

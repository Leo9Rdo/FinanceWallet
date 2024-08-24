package com.example.financewallet.presentation.assetList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.financewallet.databinding.FragmentAssetListBinding
import com.example.financewallet.presentation.rv.AssetAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetListFragment : Fragment() {

    private var _binding: FragmentAssetListBinding? = null
    private val binding get() = _binding!!
    private val assetAdapter by lazy {
        AssetAdapter()
    }
    private val viewModel by viewModels<AssetListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssetListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        viewModel.assetsModel.observe(viewLifecycleOwner) { assets ->
            assetAdapter.submitList(assets)
        }

        viewModel.loadAllAssets()
    }

    private fun configureRecyclerView() {
        binding.assetRecyclerView.adapter = assetAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.oxygenbd.exthon.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oxygenbd.exthon.databinding.FragmentBlockingBinding
import com.oxygenbd.exthon.ui.adapter.BlockedSiteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlockingFragment : Fragment() {

    private var _binding: FragmentBlockingBinding? = null
    private val binding get() = _binding!!
    private lateinit var blockedSiteAdapter: BlockedSiteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlockingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        blockedSiteAdapter = BlockedSiteAdapter()
        
        binding.rvBlockedSites.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = blockedSiteAdapter
        }

        binding.fabAddBlockedSite.setOnClickListener {
            // Show dialog to add blocked site
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

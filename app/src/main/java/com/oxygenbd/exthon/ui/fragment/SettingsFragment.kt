package com.oxygenbd.exthon.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oxygenbd.exthon.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

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
        setupUI()
    }

    private fun setupUI() {
        binding.btnExport.setOnClickListener {
            // Export data
        }

        binding.btnImport.setOnClickListener {
            // Import data
        }

        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            // Update notification settings
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

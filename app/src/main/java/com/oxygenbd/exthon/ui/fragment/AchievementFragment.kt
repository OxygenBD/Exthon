package com.oxygenbd.exthon.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.oxygenbd.exthon.databinding.FragmentAchievementBinding
import com.oxygenbd.exthon.ui.adapter.AchievementAdapter
import com.oxygenbd.exthon.ui.viewmodel.AchievementViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AchievementFragment : Fragment() {

    private var _binding: FragmentAchievementBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AchievementViewModel by viewModels()
    private lateinit var achievementAdapter: AchievementAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAchievementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        achievementAdapter = AchievementAdapter()
        
        binding.rvAchievements.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = achievementAdapter
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.unlockedAchievements.collect { achievements ->
                achievementAdapter.submitList(achievements)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.totalPoints.collect { points ->
                binding.tvTotalPoints.text = "Total Points: $points"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

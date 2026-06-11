package com.oxygenbd.exthon.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.oxygenbd.exthon.databinding.FragmentHomeBinding
import com.oxygenbd.exthon.ui.viewmodel.PomodoroViewModel
import com.oxygenbd.exthon.ui.viewmodel.AchievementViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val pomodoroViewModel: PomodoroViewModel by viewModels()
    private val achievementViewModel: AchievementViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        binding.btnStartPomodoro.setOnClickListener {
            pomodoroViewModel.startTimer(25 * 60 * 1000L)
        }

        binding.btnViewAchievements.setOnClickListener {
            // Navigate to achievements
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            pomodoroViewModel.sessions.collect { sessions ->
                binding.tvSessionsCompleted.text = "Sessions today: ${sessions.size}"
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            achievementViewModel.totalPoints.collect { points ->
                binding.tvTotalPoints.text = "Total Points: $points"
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            achievementViewModel.unlockedAchievements.collect { achievements ->
                binding.tvAchievementsUnlocked.text = "Achievements: ${achievements.size}"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

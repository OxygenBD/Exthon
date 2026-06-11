package com.oxygenbd.exthon.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.oxygenbd.exthon.databinding.FragmentPomodoroBinding
import com.oxygenbd.exthon.ui.viewmodel.PomodoroViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PomodoroFragment : Fragment() {

    private var _binding: FragmentPomodoroBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PomodoroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPomodoroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        binding.btnStart.setOnClickListener {
            viewModel.startTimer(25 * 60 * 1000L)
        }

        binding.btnPause.setOnClickListener {
            viewModel.pauseTimer()
        }

        binding.btnResume.setOnClickListener {
            viewModel.resumeTimer()
        }

        binding.btnStop.setOnClickListener {
            viewModel.stopTimer()
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.timeRemaining.collect { time ->
                val minutes = time / 60000
                val seconds = (time % 60000) / 1000
                binding.tvTimer.text = String.format("%02d:%02d", minutes, seconds)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.timerRunning.collect { running ->
                binding.btnStart.isEnabled = !running
                binding.btnPause.isEnabled = running
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

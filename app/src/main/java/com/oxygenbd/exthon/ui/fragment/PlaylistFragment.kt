package com.oxygenbd.exthon.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.oxygenbd.exthon.databinding.FragmentPlaylistBinding
import com.oxygenbd.exthon.ui.adapter.PlaylistAdapter
import com.oxygenbd.exthon.ui.viewmodel.PlaylistViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    private var _binding: FragmentPlaylistBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlaylistViewModel by viewModels()
    private lateinit var playlistAdapter: PlaylistAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        playlistAdapter = PlaylistAdapter { playlist ->
            viewModel.selectPlaylist(playlist.id)
        }
        
        binding.rvPlaylists.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = playlistAdapter
        }

        binding.fabAddPlaylist.setOnClickListener {
            // Show dialog to add playlist
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.playlists.collect { playlists ->
                playlistAdapter.submitList(playlists)
                if (playlists.isEmpty()) {
                    binding.tvEmptyState.visibility = View.VISIBLE
                    binding.rvPlaylists.visibility = View.GONE
                } else {
                    binding.tvEmptyState.visibility = View.GONE
                    binding.rvPlaylists.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

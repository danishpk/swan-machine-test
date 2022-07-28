package com.swan.samplemovieapp.ui.moviesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.swan.samplemovieapp.databinding.FragmentMovieListBinding
import com.swan.samplemovieapp.ui.base.PagingLoadStateAdapter
import kotlinx.coroutines.flow.distinctUntilChangedBy

class MovieListFragment : Fragment() {

    private val viewModel: MovieListViewModel by viewModels()

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val adapter = MoviesListAdapter {
        findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(it.id))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        observeMovies()

        binding.apply {
            rvMovies.adapter = adapter.withLoadStateFooter(
                footer = PagingLoadStateAdapter{
                    adapter.retry()
                }
            )

            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .asLiveData().observe(viewLifecycleOwner) { loadStates ->
                    pbLoading.isVisible = loadStates.refresh is LoadState.Loading
                    emptyView.vEmpty.isVisible =
                        loadStates.refresh is LoadState.NotLoading && adapter.itemCount == 0
                    viewNetworkState.vNetworkState.isVisible = loadStates.refresh is LoadState.Error
                    rvMovies.isVisible = !binding.viewNetworkState.vNetworkState.isVisible
                    rvMovies.scrollToPosition(0)
                }


        }
    }

    private fun observeMovies() {
        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
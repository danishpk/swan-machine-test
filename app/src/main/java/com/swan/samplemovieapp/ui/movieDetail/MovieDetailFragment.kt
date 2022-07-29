package com.swan.samplemovieapp.ui.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.swan.samplemovieapp.R
import com.swan.samplemovieapp.data.models.Movie
import com.swan.samplemovieapp.data.models.response.Resource
import com.swan.samplemovieapp.databinding.FragmentMovieDetailBinding
import com.swan.samplemovieapp.databinding.IncludeNetworkStateBinding

class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailViewModel by viewModels()

    private var _binding: FragmentMovieDetailBinding? = null
    private var _bindingNetworkView: IncludeNetworkStateBinding? = null

    private val binding get() = _binding!!
    private val bindingNetworkView get() = _bindingNetworkView!!

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        _bindingNetworkView = IncludeNetworkStateBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        viewModel.movie.observe(viewLifecycleOwner) {
            bindingNetworkView.vLoading.isVisible = it is Resource.Loading
            bindingNetworkView.vError.isVisible = it is Resource.Error
            binding.clDetailView.isVisible = it is Resource.Success

            bindingMovieDetails(it.data)

        }
    }

    private fun bindingMovieDetails(movie: Movie?) {

        binding.apply {
            tvTitle.text = movie?.title
            tvTagline.text = movie?.tagline
            tvOverView.text = movie?.overview

            Glide.with(requireContext())
                .load(movie?.backdropImageUrl)
                .centerCrop()
                .placeholder(
                    ContextCompat.getDrawable(requireContext(),
                        R.drawable.img_placeholder))
                .error(R.drawable.img_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivPoster)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
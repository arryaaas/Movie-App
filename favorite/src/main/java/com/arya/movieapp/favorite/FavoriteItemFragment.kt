package com.arya.movieapp.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.arya.movieapp.core.ui.MovieAdapter
import com.arya.movieapp.core.utils.gone
import com.arya.movieapp.core.utils.visible
import com.arya.movieapp.favorite.databinding.FragmentFavoriteItemBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteItemFragment : Fragment() {

    private var _binding: FragmentFavoriteItemBinding? = null
    private val binding get() = _binding as FragmentFavoriteItemBinding

    private val viewModel: FavoriteItemViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteItemBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = {
            findNavController().navigate(
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailActivity(it)
            )
        }

        binding.rvFav.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        when (arguments?.getInt(ARG_SECTION_NUMBER, 0)) {
            1 -> {
                viewModel.getAllFavoriteMovie().observe(viewLifecycleOwner) { movie ->
                    movieAdapter.submitList(movie)
                    showNotify(movie.isEmpty())
                }
            }
            2 -> {
                viewModel.getAllFavoriteTv().observe(viewLifecycleOwner) { tv ->
                    movieAdapter.submitList(tv)
                    showNotify(tv.isEmpty())
                }
            }
        }

    }

    private fun showNotify(state: Boolean) {
        if (state) {
            binding.lottie.visible()
            binding.tvMessage.visible()
        } else {
            binding.lottie.gone()
            binding.tvMessage.gone()
        }
    }

    override fun onDestroyView() {
        binding.rvFav.adapter = null
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }
}
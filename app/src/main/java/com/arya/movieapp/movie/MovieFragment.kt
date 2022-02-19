package com.arya.movieapp.movie

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.arya.movieapp.R
import com.arya.movieapp.core.data.source.Resource
import com.arya.movieapp.core.domain.model.Movie
import com.arya.movieapp.core.ui.MovieAdapter
import com.arya.movieapp.core.utils.SortUtils.RATING
import com.arya.movieapp.core.utils.SortUtils.RELEASE_DATE
import com.arya.movieapp.core.utils.SortUtils.TITLE
import com.arya.movieapp.core.utils.gone
import com.arya.movieapp.core.utils.visible
import com.arya.movieapp.databinding.FragmentMovieBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding as FragmentMovieBinding

    private val viewModel: MovieViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    private var checkedItem = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = {
            findNavController().navigate(
                MovieFragmentDirections.actionMovieFragmentToDetailActivity(it)
            )
        }

        binding.rvMovie.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        selectSort(TITLE)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sort_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_sort) showDialog()
        return super.onOptionsItemSelected(item)
    }

    private fun showDialog() {
        val singleItems = arrayOf("Title", "Release Date", "Rating")

        MaterialAlertDialogBuilder(requireActivity())
            .setTitle(R.string.sort_by)
            .setSingleChoiceItems(singleItems, checkedItem) { dialog, which ->
                checkedItem = which
                when (which) {
                    0 -> selectSort(TITLE)
                    1 -> selectSort(RELEASE_DATE)
                    2 -> selectSort(RATING)
                }
                dialog.dismiss()
            }
            .show()
    }

    private fun selectSort(sort: String) {
        viewModel.getAllMovie(sort).observe(viewLifecycleOwner, movieObserver)
    }

    private val movieObserver = Observer<Resource<List<Movie>>> { movie ->
        if (movie != null) {
            when (movie) {
                is Resource.Loading -> {
                    binding.shimmerLayout.visible()
                    binding.shimmerLayout.startShimmer()
                }
                is Resource.Success -> {
                    binding.shimmerLayout.gone()
                    binding.shimmerLayout.stopShimmer()
                    movieAdapter.submitList(movie.data) { binding.rvMovie.scrollToPosition(0) }
                }
                is Resource.Error -> {
                    binding.shimmerLayout.gone()
                    binding.shimmerLayout.stopShimmer()
                    Toast.makeText(context, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        binding.rvMovie.adapter = null
        super.onDestroyView()
        _binding = null
    }
}
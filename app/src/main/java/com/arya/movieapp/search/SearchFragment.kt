package com.arya.movieapp.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.arya.movieapp.R
import com.arya.movieapp.core.domain.model.Movie
import com.arya.movieapp.core.ui.MovieAdapter
import com.arya.movieapp.core.utils.gone
import com.arya.movieapp.core.utils.visible
import com.arya.movieapp.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding as FragmentSearchBinding

    private val viewModel: SearchViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToDetailActivity(it)
            )
        }

        binding.rvSearch.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.queryHint = getString(R.string.query_hint)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean = true

            override fun onQueryTextChange(newText: String): Boolean {
                newText.let {
                    if (it == "" || it.isEmpty()) {
                        movieAdapter.submitList(emptyList())
                        showNotify(true)
                    } else {
                        viewModel.getSearchData(it).observe(viewLifecycleOwner, movieObserver)
                    }
                }
                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private val movieObserver = Observer<List<Movie>> { movie ->
        movieAdapter.submitList(movie) { binding.rvSearch.scrollToPosition(0) }
        showNotify(movie.isEmpty())
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
        binding.rvSearch.adapter = null
        super.onDestroyView()
        _binding = null
    }

}
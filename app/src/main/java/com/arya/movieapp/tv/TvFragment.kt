package com.arya.movieapp.tv

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
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
import com.arya.movieapp.databinding.FragmentTvBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvFragment : Fragment() {

    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding as FragmentTvBinding

    private val viewModel: TvViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    private var checkedItem = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = {
            findNavController().navigate(
                TvFragmentDirections.actionTvFragmentToDetailActivity(it)
            )
        }

        binding.rvTv.apply {
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
        viewModel.getAllTv(sort).observe(viewLifecycleOwner, tvObserver)
    }

    private val tvObserver = Observer<Resource<List<Movie>>> { tv ->
        if (tv != null) {
            when (tv) {
                is Resource.Loading -> {
                    binding.shimmerLayout.visible()
                    binding.shimmerLayout.startShimmer()
                }
                is Resource.Success -> {
                    binding.shimmerLayout.gone()
                    binding.shimmerLayout.stopShimmer()
                    movieAdapter.submitList(tv.data) { binding.rvTv.scrollToPosition(0) }
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
        binding.rvTv.adapter = null
        super.onDestroyView()
        _binding = null
    }
}
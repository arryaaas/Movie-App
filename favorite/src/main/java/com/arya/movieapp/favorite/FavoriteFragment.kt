package com.arya.movieapp.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.arya.movieapp.MainActivity
import com.arya.movieapp.favorite.databinding.FragmentFavoriteBinding
import com.arya.movieapp.favorite.di.favoriteModule
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding as FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)

        binding.viewPager.apply {
            adapter = sectionsPagerAdapter
            getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
        }

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        (activity as MainActivity).supportActionBar?.elevation = 0F
    }

    override fun onDestroyView() {
        unloadKoinModules(favoriteModule)
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_title_movie,
            R.string.tab_title_tv
        )
    }
}
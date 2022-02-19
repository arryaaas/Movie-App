package com.arya.movieapp.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = FavoriteItemFragment()
        fragment.arguments = Bundle().apply {
            putInt(FavoriteItemFragment.ARG_SECTION_NUMBER, position + 1)
        }
        return fragment
    }

}
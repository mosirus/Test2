package com.dicoding.picodiploma.subfundamental.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.picodiploma.subfundamental.view.FollowFragment

class SectionPagerAdapter(activity: AppCompatActivity, private val username: String?) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return FollowFragment.newInstance(position + 1, username)
    }

    override fun getItemCount(): Int {
        return 2
    }

}
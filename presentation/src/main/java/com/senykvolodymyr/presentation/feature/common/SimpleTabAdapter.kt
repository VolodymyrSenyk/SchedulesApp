package com.senykvolodymyr.presentation.feature.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class SimpleTabAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    private val fragments: MutableList<Fragment> = mutableListOf()

    private val titles: MutableList<String> = mutableListOf()

    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getPageTitle(position: Int): String = titles[position]

    override fun getCount(): Int = fragments.size
}

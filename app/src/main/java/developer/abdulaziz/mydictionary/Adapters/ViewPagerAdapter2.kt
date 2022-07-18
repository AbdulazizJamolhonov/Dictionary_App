package developer.abdulaziz.mydictionary.Adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import developer.abdulaziz.mydictionary.Fragments.ItemFragments.ViewPagerItemFragment1
import developer.abdulaziz.mydictionary.Fragments.ItemFragments.ViewPagerItemFragment2

class ViewPagerAdapter2(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        if (position == 0) ViewPagerItemFragment1()
        else ViewPagerItemFragment2()
}
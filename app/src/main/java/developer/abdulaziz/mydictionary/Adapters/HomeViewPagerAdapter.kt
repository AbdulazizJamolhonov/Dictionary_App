package developer.abdulaziz.mydictionary.Adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import developer.abdulaziz.mydictionary.Fragments.ItemFragments.HomePagerFragment1
import developer.abdulaziz.mydictionary.Fragments.ItemFragments.HomePagerFragment2

class HomeViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        if (position == 0) HomePagerFragment1()
        else HomePagerFragment2()
}
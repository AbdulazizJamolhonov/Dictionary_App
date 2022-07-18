package developer.abdulaziz.mydictionary.Adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import developer.abdulaziz.mydictionary.Fragments.ItemFragments.HomeItemFragment

class ViewPagerAdapter(val list: ArrayList<String>, fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = list.size
    override fun createFragment(position: Int): Fragment = HomeItemFragment()
}
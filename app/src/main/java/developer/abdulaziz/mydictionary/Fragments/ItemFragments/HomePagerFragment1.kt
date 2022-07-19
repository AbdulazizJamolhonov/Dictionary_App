package developer.abdulaziz.mydictionary.Fragments.ItemFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import developer.abdulaziz.mydictionary.Object.MyObject
import developer.abdulaziz.mydictionary.Room.AppDatabase
import developer.abdulaziz.mydictionary.Room.Entity.MyRoom
import developer.abdulaziz.mydictionary.databinding.FragmentHomePager1Binding

class HomePagerFragment1 : Fragment() {
    private lateinit var binding: FragmentHomePager1Binding
    private lateinit var app: AppDatabase
    private lateinit var list: ArrayList<MyRoom>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePager1Binding.inflate(layoutInflater)
        binding.apply {
            app = AppDatabase.getInstance(root.context)
            list = ArrayList()
            list.addAll(app.dao().read())
            MyObject.init(root.context)
            listener(0)
            viewPager1.adapter = object : FragmentStateAdapter(this@HomePagerFragment1) {
                override fun getItemCount(): Int = MyObject.sharedList.size
                override fun createFragment(position: Int): Fragment = HomeItemFragment()
            }

            TabLayoutMediator(tabLayout1, viewPager1) { tab, pos ->
                tab.text = MyObject.sharedList[pos]
            }.attach()

            tabLayout1.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) = listener(tab?.position!!)
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })

            return root
        }
    }

    private fun listener(position: Int) {
        if (MyObject.list.isNotEmpty()) MyObject.list.clear()
        for (i in list) {
            if (MyObject.sharedList[position] == i.category) MyObject.list.add(i)
        }
    }
}
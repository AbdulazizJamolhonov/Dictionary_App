package developer.abdulaziz.mydictionary.Fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import developer.abdulaziz.mydictionary.Adapters.HomeViewPagerAdapter
import developer.abdulaziz.mydictionary.R
import developer.abdulaziz.mydictionary.databinding.FragmentHomeBinding
import developer.abdulaziz.mydictionary.databinding.ItemHomeTabBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.apply {

            val title = arrayOf("Asosiy", "Tanlangan")
            val icon = arrayOf(R.drawable.ic_home, R.drawable.ic_like)
            val icon2 = arrayOf(R.drawable.ic_home_yellow, R.drawable.ic_like_yellow)

            homeViewPager.adapter = HomeViewPagerAdapter(this@HomeFragment)
            TabLayoutMediator(homeTabLayout, homeViewPager) { tab, pos ->
                val tabView = ItemHomeTabBinding.inflate(layoutInflater)
                tab.customView = tabView.root
                tabView.icon.setImageResource(icon[pos])
                tabView.myTitle.text = title[pos]
                if (pos == 0) {
                    tabView.icon.setImageResource(icon2[pos])
                    tabView.myTitle.setTextColor(Color.parseColor("#FCB600"))
                } else {
                    tabView.icon.setImageResource(icon[pos])
                    tabView.myTitle.setTextColor(Color.parseColor("#000000"))
                }
            }.attach()
            listener()

            homeSettings.setOnClickListener { findNavController().navigate(R.id.settingsFragment) }

            return root
        }
    }

    private fun listener() {
        binding.homeTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val cv = tab?.customView
                if (tab?.position == 0) {
                    cv?.findViewById<ImageView>(R.id.icon)
                        ?.setImageResource(R.drawable.ic_home_yellow)
                    cv?.findViewById<TextView>(R.id.my_title)
                        ?.setTextColor(Color.parseColor("#FCB600"))
                } else {
                    cv?.findViewById<ImageView>(R.id.icon)
                        ?.setImageResource(R.drawable.ic_like_yellow)
                    cv?.findViewById<TextView>(R.id.my_title)
                        ?.setTextColor(Color.parseColor("#FCB600"))
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val cv = tab?.customView
                if (tab?.position == 0) {
                    cv?.findViewById<ImageView>(R.id.icon)
                        ?.setImageResource(R.drawable.ic_home)
                    cv?.findViewById<TextView>(R.id.my_title)
                        ?.setTextColor(Color.parseColor("#000000"))
                } else {
                    cv?.findViewById<ImageView>(R.id.icon)
                        ?.setImageResource(R.drawable.ic_like)
                    cv?.findViewById<TextView>(R.id.my_title)
                        ?.setTextColor(Color.parseColor("#000000"))
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}
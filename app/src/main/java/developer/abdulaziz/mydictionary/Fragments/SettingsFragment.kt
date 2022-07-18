package developer.abdulaziz.mydictionary.Fragments

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import developer.abdulaziz.mydictionary.Adapters.ViewPagerAdapter2
import developer.abdulaziz.mydictionary.Object.MyObject
import developer.abdulaziz.mydictionary.R
import developer.abdulaziz.mydictionary.Room.AppDatabase
import developer.abdulaziz.mydictionary.Room.Entity.MyRoom
import developer.abdulaziz.mydictionary.databinding.FragmentSettingsBinding
import developer.abdulaziz.mydictionary.databinding.ItemAddCategoryBinding
import developer.abdulaziz.mydictionary.databinding.ItemHomeTabBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        binding.apply {
            MyObject.init(root.context)
            back.setOnClickListener { findNavController().popBackStack() }
            val title = arrayOf("Kategoriya", "So'zlar")
            val icon = arrayOf(R.drawable.kategoriya_black, R.drawable.sozlar_black)
            val icon2 = arrayOf(R.drawable.kategoriya_yellow, R.drawable.sozlar_yellow)

            viewPager2.adapter = ViewPagerAdapter2(this@SettingsFragment)
            TabLayoutMediator(tabLayout2, viewPager2) { tab, pos ->
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
            add.setOnClickListener {
                val alertDialog = AlertDialog.Builder(root.context).create()
                val item = ItemAddCategoryBinding.inflate(layoutInflater).apply {
                    cancel.setOnClickListener { alertDialog.cancel() }
                    save.setOnClickListener {
                        if (kategoryName.text.toString().isNotEmpty()) {
                            val list = MyObject.sharedList
                            list.add(kategoryName.text.toString())
                            MyObject.sharedList = list
                            findNavController().popBackStack()
                            findNavController().navigate(R.id.settingsFragment)
                            alertDialog.cancel()
                        } else Toast.makeText(root.context, "Empty", Toast.LENGTH_SHORT).show()
                    }
                }
                alertDialog.setCancelable(false)
                alertDialog.setView(item.root)
                alertDialog.show()
            }

            return root
        }
    }

    private fun listener() {
        binding.tabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val cv = tab?.customView
                if (tab?.position == 0) {
                    cv?.findViewById<ImageView>(R.id.icon)
                        ?.setImageResource(R.drawable.kategoriya_yellow)
                    cv?.findViewById<TextView>(R.id.my_title)
                        ?.setTextColor(Color.parseColor("#FCB600"))
                    binding.add.setOnClickListener {
                        val alertDialog = AlertDialog.Builder(binding.root.context).create()
                        val item = ItemAddCategoryBinding.inflate(layoutInflater)
                        item.cancel.setOnClickListener { alertDialog.cancel() }
                        item.save.setOnClickListener {
                            if (item.kategoryName.text.toString().isNotEmpty()) {
                                val list = MyObject.sharedList
                                list.add(item.kategoryName.text.toString())
                                MyObject.sharedList = list
                                alertDialog.cancel()
                            } else Toast.makeText(binding.root.context, "Empty", Toast.LENGTH_SHORT)
                                .show()
                        }
                        alertDialog.setCancelable(false)
                        alertDialog.setView(item.root)
                        alertDialog.show()
                    }
                } else {
                    cv?.findViewById<ImageView>(R.id.icon)
                        ?.setImageResource(R.drawable.sozlar_yellow)
                    cv?.findViewById<TextView>(R.id.my_title)
                        ?.setTextColor(Color.parseColor("#FCB600"))
                    binding.add.setOnClickListener { findNavController().navigate(R.id.addFragment) }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val cv = tab?.customView
                if (tab?.position == 0) {
                    cv?.findViewById<ImageView>(R.id.icon)
                        ?.setImageResource(R.drawable.kategoriya_black)
                    cv?.findViewById<TextView>(R.id.my_title)
                        ?.setTextColor(Color.parseColor("#000000"))
                } else {
                    cv?.findViewById<ImageView>(R.id.icon)
                        ?.setImageResource(R.drawable.sozlar_black)
                    cv?.findViewById<TextView>(R.id.my_title)
                        ?.setTextColor(Color.parseColor("#000000"))
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}
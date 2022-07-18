package developer.abdulaziz.mydictionary.Fragments.ItemFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.mydictionary.Adapters.MyRvAdapter
import developer.abdulaziz.mydictionary.R
import developer.abdulaziz.mydictionary.Room.AppDatabase
import developer.abdulaziz.mydictionary.Room.Entity.MyRoom
import developer.abdulaziz.mydictionary.databinding.FragmentHomePager2Binding

class HomePagerFragment2 : Fragment() {
    private lateinit var binding: FragmentHomePager2Binding
    private lateinit var appDatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePager2Binding.inflate(layoutInflater)
        binding.apply {
            appDatabase = AppDatabase.getInstance(root.context)

            val list = ArrayList<MyRoom>()
            for (i in appDatabase.dao().read()) {
                if (i.like!!) list.add(i)
            }
            rvItem2.adapter = MyRvAdapter(list, object : MyRvAdapter.MyClick {
                override fun onClick(my: MyRoom, pos: Int) =
                    findNavController().navigate(R.id.aboutFragment)
            })

            return root
        }
    }
}
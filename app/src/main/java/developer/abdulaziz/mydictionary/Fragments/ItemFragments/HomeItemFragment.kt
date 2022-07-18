package developer.abdulaziz.mydictionary.Fragments.ItemFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.mydictionary.Adapters.MyRvAdapter
import developer.abdulaziz.mydictionary.Object.MyObject
import developer.abdulaziz.mydictionary.R
import developer.abdulaziz.mydictionary.Room.AppDatabase
import developer.abdulaziz.mydictionary.Room.Entity.MyRoom
import developer.abdulaziz.mydictionary.databinding.FragmentHomeItemBinding

class HomeItemFragment : Fragment() {
    private lateinit var binding: FragmentHomeItemBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeItemBinding.inflate(layoutInflater)
        binding.apply {
            rvItem.adapter = MyRvAdapter(MyObject.list, object : MyRvAdapter.MyClick {
                override fun onClick(my: MyRoom, pos: Int) {
                    MyObject.myRoom = my
                    findNavController().navigate(R.id.aboutFragment)
                }
            })

            return root
        }
    }
}
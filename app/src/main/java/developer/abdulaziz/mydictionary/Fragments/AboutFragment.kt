package developer.abdulaziz.mydictionary.Fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.mydictionary.Object.MyObject
import developer.abdulaziz.mydictionary.R
import developer.abdulaziz.mydictionary.Room.AppDatabase
import developer.abdulaziz.mydictionary.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding
    private lateinit var appDatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(layoutInflater)
        binding.apply {
            appDatabase = AppDatabase.getInstance(root.context)
            back.setOnClickListener { findNavController().popBackStack() }
            actionBarTitle.text = MyObject.myRoom.english
            image.setImageURI(Uri.parse(MyObject.myRoom.image))
            tvEnglish.text = MyObject.myRoom.english
            tvUzbek.text = MyObject.myRoom.uzbek
            if (MyObject.myRoom.like!!) likeImage.setImageResource(R.drawable.ic_like_red)
            else likeImage.setImageResource(R.drawable.ic_like_white)
            like.setOnClickListener {
                if (MyObject.myRoom.like!!) {
                    likeImage.setImageResource(R.drawable.ic_like_white)
                    MyObject.myRoom.like = false
                } else {
                    likeImage.setImageResource(R.drawable.ic_like_red)
                    MyObject.myRoom.like = true
                }
                appDatabase.dao().update(MyObject.myRoom)
            }

            return root
        }
    }
}
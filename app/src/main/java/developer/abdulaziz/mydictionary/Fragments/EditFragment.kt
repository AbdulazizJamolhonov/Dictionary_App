package developer.abdulaziz.mydictionary.Fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.mydictionary.Object.MyObject
import developer.abdulaziz.mydictionary.Room.AppDatabase
import developer.abdulaziz.mydictionary.databinding.FragmentEditBinding
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private var imagePath: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(layoutInflater)
        binding.apply {
            MyObject.init(root.context)
            val app = AppDatabase.getInstance(root.context)
            image.setImageURI(Uri.parse(MyObject.myRoom.image))
            spinnerCategory.adapter = ArrayAdapter(
                root.context,
                android.R.layout.simple_expandable_list_item_1,
                MyObject.sharedList
            )
            for (i in MyObject.sharedList) {
                if (MyObject.myRoom.category == i) {
                    spinnerCategory.setSelection(MyObject.sharedList.indexOf(i))
                    break
                }
            }
            english.setText(MyObject.myRoom.english)
            uzbek.setText(MyObject.myRoom.uzbek)
            image.setOnClickListener { getImageContent.launch("image/*") }
            back.setOnClickListener { findNavController().popBackStack() }
            save.setOnClickListener {
                val c = MyObject.sharedList[spinnerCategory.selectedItemPosition]
                val e = english.text.toString()
                val u = uzbek.text.toString()
                if (imagePath != null && MyObject.sharedList.isNotEmpty() && e.isNotEmpty() && u.isNotEmpty()) {
                    MyObject.myRoom.image = imagePath
                    MyObject.myRoom.english = e
                    MyObject.myRoom.uzbek = u
                    MyObject.myRoom.category = c
                    app.dao().update(MyObject.myRoom)
                    findNavController().popBackStack()
                } else Toast.makeText(root.context, "Empty", Toast.LENGTH_SHORT).show()
            }
            return root
        }
    }

    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            it ?: return@registerForActivityResult
            binding.image.setImageURI(it)
            binding.image.background = null
            binding.myText.visibility = View.GONE
            val imageName =
                SimpleDateFormat("ddMMyyyy_hh:mm:ss", Locale.getDefault()).format(Date())
            val file = File(requireActivity().filesDir, "$imageName.jpg")
            val inputStream = requireActivity().contentResolver?.openInputStream(it)
            val fileOutputStream = FileOutputStream(file)
            inputStream?.copyTo(fileOutputStream)
            inputStream?.close()
            fileOutputStream.close()
            imagePath = file.absolutePath
        }
}
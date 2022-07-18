package developer.abdulaziz.mydictionary.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.mydictionary.Object.MyObject
import developer.abdulaziz.mydictionary.Room.AppDatabase
import developer.abdulaziz.mydictionary.Room.Entity.MyRoom
import developer.abdulaziz.mydictionary.databinding.FragmentAddBinding
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private var imagePath: String? = null
    private lateinit var appDatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater)
        binding.apply {
            appDatabase = AppDatabase.getInstance(root.context)
            MyObject.init(root.context)
            val listTitle = MyObject.sharedList
            back.setOnClickListener { findNavController().popBackStack() }
            cancel.setOnClickListener { findNavController().popBackStack() }
            image.setOnClickListener { getImageContent.launch("image/*") }
            spinnerCategory.adapter =
                ArrayAdapter(
                    root.context,
                    android.R.layout.simple_expandable_list_item_1,
                    listTitle
                )
            save.setOnClickListener {
                val c = listTitle[spinnerCategory.selectedItemPosition]
                val e = english.text.toString()
                val u = uzbek.text.toString()
                if (imagePath != null && listTitle.isNotEmpty() && e.isNotEmpty() && u.isNotEmpty()) {
                    val myRoom = MyRoom(imagePath, e, u, c, false)
                    appDatabase.dao().create(myRoom)
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
            val name = SimpleDateFormat("ddMMyyyy_hh:mm:ss", Locale.getDefault()).format(Date())
            val file = File(requireActivity().filesDir, "$name.jpg")
            val inputStream = requireActivity().contentResolver?.openInputStream(it)
            val fileOutputStream = FileOutputStream(file)
            inputStream?.copyTo(fileOutputStream)
            inputStream?.close()
            fileOutputStream.close()
            imagePath = file.absolutePath
        }
}
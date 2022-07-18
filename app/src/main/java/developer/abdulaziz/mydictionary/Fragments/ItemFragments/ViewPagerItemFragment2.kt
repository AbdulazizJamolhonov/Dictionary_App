package developer.abdulaziz.mydictionary.Fragments.ItemFragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.mydictionary.Adapters.MyRvAdapter2
import developer.abdulaziz.mydictionary.Object.MyObject
import developer.abdulaziz.mydictionary.R
import developer.abdulaziz.mydictionary.Room.AppDatabase
import developer.abdulaziz.mydictionary.Room.Entity.MyRoom
import developer.abdulaziz.mydictionary.databinding.FragmentViewPagerItem2Binding
import developer.abdulaziz.mydictionary.databinding.ItemDeleteBinding

class ViewPagerItemFragment2 : Fragment() {
    private lateinit var binding: FragmentViewPagerItem2Binding
    private lateinit var myRvAdapter2: MyRvAdapter2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerItem2Binding.inflate(layoutInflater)
        binding.apply {
            val app = AppDatabase.getInstance(root.context)
            val list = ArrayList<MyRoom>()
            list.addAll(app.dao().read())
            myRvAdapter2 = MyRvAdapter2(list, object : MyRvAdapter2.MyPopup {
                override fun onClick(view: View, user: MyRoom, position: Int) {
                    val popupMenu = PopupMenu(root.context, view)
                    popupMenu.inflate(R.menu.my_popup_menu)
                    popupMenu.setOnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.edit -> {
                                MyObject.myRoom = user
                                findNavController().navigate(R.id.editFragment)
                            }
                            R.id.delete -> {
                                val alertDialog = AlertDialog.Builder(root.context).create()
                                val item = ItemDeleteBinding.inflate(layoutInflater)
                                item.cancel.setOnClickListener { alertDialog.cancel() }
                                item.save.setOnClickListener {
                                    app.dao().delete(user)
                                    list.remove(user)
                                    myRvAdapter2.notifyItemRemoved(position)
                                    alertDialog.cancel()
                                }
                                alertDialog.setCancelable(false)
                                alertDialog.setView(item.root)
                                alertDialog.show()
                            }
                        }
                        true
                    }
                    popupMenu.show()
                }
            })

            rv2.adapter = myRvAdapter2

            return root
        }
    }
}
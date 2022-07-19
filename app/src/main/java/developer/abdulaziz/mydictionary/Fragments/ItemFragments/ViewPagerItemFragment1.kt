package developer.abdulaziz.mydictionary.Fragments.ItemFragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import developer.abdulaziz.mydictionary.Adapters.MyRvAdapter1
import developer.abdulaziz.mydictionary.Object.MyObject
import developer.abdulaziz.mydictionary.R
import developer.abdulaziz.mydictionary.Room.AppDatabase
import developer.abdulaziz.mydictionary.Room.Entity.MyRoom
import developer.abdulaziz.mydictionary.databinding.FragmentViewPagerItem1Binding
import developer.abdulaziz.mydictionary.databinding.ItemAddCategoryBinding
import developer.abdulaziz.mydictionary.databinding.ItemDeleteBinding

class ViewPagerItemFragment1 : Fragment() {
    private lateinit var binding: FragmentViewPagerItem1Binding
    private lateinit var myRvAdapter1: MyRvAdapter1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerItem1Binding.inflate(layoutInflater)
        binding.apply {
            MyObject.init(root.context)
            val list = MyObject.sharedList
            myRvAdapter1 = MyRvAdapter1(list, object : MyRvAdapter1.MyPopup {
                override fun onClick(view: View, user: String, position: Int) {
                    val popupMenu = PopupMenu(root.context, view)
                    popupMenu.inflate(R.menu.my_popup_menu)
                    popupMenu.setOnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.edit -> {
                                val alertDialog = AlertDialog.Builder(root.context).create()
                                val item = ItemAddCategoryBinding.inflate(layoutInflater)
                                item.kategoryName.setText(user)
                                item.cancel.setOnClickListener { alertDialog.cancel() }
                                item.save.setOnClickListener {
                                    if (item.kategoryName.text.toString().isNotEmpty()) {
                                        list[position] = item.kategoryName.text.toString()
                                        MyObject.sharedList = list
                                        myRvAdapter1.notifyItemChanged(position)
                                        alertDialog.cancel()
                                    } else Toast.makeText(root.context, "Empty", Toast.LENGTH_SHORT)
                                        .show()
                                }
                                alertDialog.setCancelable(false)
                                alertDialog.setView(item.root)
                                alertDialog.show()
                            }
                            R.id.delete -> {
                                val alertDialog = AlertDialog.Builder(root.context).create()
                                val item = ItemDeleteBinding.inflate(layoutInflater)
                                item.cancel.setOnClickListener { alertDialog.cancel() }
                                item.save.setOnClickListener {
                                    val a = AppDatabase.getInstance(root.context)
                                    for (i in a.dao().read()) {
                                        if (i.category == user) a.dao().delete(i)
                                    }
                                    list.remove(user)
                                    MyObject.sharedList = list
                                    myRvAdapter1.notifyItemRemoved(position)
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

            rv1.adapter = myRvAdapter1

            return root
        }
    }
}
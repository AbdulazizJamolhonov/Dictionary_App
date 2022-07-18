package developer.abdulaziz.mydictionary.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdulaziz.mydictionary.Room.Entity.MyRoom
import developer.abdulaziz.mydictionary.databinding.ItemRv2Binding

class MyRvAdapter2(
    private val list: ArrayList<MyRoom>,
    private val myPopup: MyPopup
) :
    RecyclerView.Adapter<MyRvAdapter2.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRv2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(user: MyRoom, position: Int) {
            binding.name.text = user.english
            binding.about.text = user.uzbek
            binding.popup2.visibility = View.VISIBLE
            binding.popup2.setOnClickListener { myPopup.onClick(it, user, position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemRv2Binding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(hol: ViewHolder, pos: Int) = hol.onBind(list[pos], pos)
    override fun getItemCount(): Int = list.size
    interface MyPopup {
        fun onClick(view: View, user: MyRoom, position: Int)
    }
}